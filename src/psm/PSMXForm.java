package psm;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class PSMXForm {    
    //constants
    public static final Point PIVOT_PT =new Point(100, 100);
    public static final double MIN_START_ARM_LENGTH_FOR_SCALING = 100.0;
    
    //fields
    private AffineTransform mStartXformFromWorldToScreen = null;
    
    private AffineTransform mCurXformFromWorldToScreen = null;
    public AffineTransform getCurXformFromWorldToScreen() {
        return this.mCurXformFromWorldToScreen;
    }
    
    private AffineTransform mCurXformFromScreenToWorld = null;
    public AffineTransform getCurXformFromScreenToWorld() {
        return this.mCurXformFromScreenToWorld;
    }
    
    private Point mStartScreenPt = null;
    public void setStartScreenPt(Point pt) {
        this.mStartScreenPt = pt;
        this.mStartXformFromWorldToScreen.setTransform(
            this.mCurXformFromWorldToScreen);
    }
    
    //constructor
    public PSMXForm() {
        this.mStartXformFromWorldToScreen = new AffineTransform();
        this.mCurXformFromWorldToScreen = new AffineTransform();
        this.mCurXformFromScreenToWorld = new AffineTransform();
    }
    
    public void updateCurXformFromScreenToWorld() {
        try {
            this.mCurXformFromScreenToWorld =
                    this.mCurXformFromWorldToScreen.createInverse();
        } catch(NoninvertibleTransformException ex) {
            System.out.println("NoninvertibleTransformException");
        }  
    }
    
    public Point calcPtFromWorldToScreen(Point2D.Double worldPt) {
        Point screenPt = new Point();
        this.mCurXformFromWorldToScreen.transform(worldPt, screenPt);
        return screenPt;
    }
    
    public Point2D.Double calcPtFromScreenToWorld(Point screenPt) {
        Point2D.Double worldPt = new Point2D.Double();
        this.mCurXformFromScreenToWorld.transform(screenPt, worldPt);
        return worldPt;
    }
    
    public boolean translateTo(Point pt) {
        if(this.mStartScreenPt == null) {
            return false;
        }       
        
        this.mCurXformFromWorldToScreen.setTransform(
            this.mStartXformFromWorldToScreen);
        this.updateCurXformFromScreenToWorld();
        
        Point2D.Double worldPt0 = 
            this.calcPtFromScreenToWorld(this.mStartScreenPt); 
        Point2D.Double worldPt1 = this.calcPtFromScreenToWorld(pt); 
        double dx = worldPt1.x - worldPt0.x;
        double dy = worldPt1.y - worldPt0.y;
        
        this.mCurXformFromWorldToScreen.translate(dx, dy);
        this.updateCurXformFromScreenToWorld();
        return true;  
    }   
    
        public boolean zoomTo(Point pt) {
        if(this.mStartScreenPt == null) {
            return false;
        }       
        
        this.mCurXformFromWorldToScreen.setTransform(
                this.mStartXformFromWorldToScreen);
        this.updateCurXformFromScreenToWorld();
        
        //calculate scaling factor
        double d0 = PSMXForm.PIVOT_PT.distance(this.mStartScreenPt);
        if (d0 < PSMXForm.MIN_START_ARM_LENGTH_FOR_SCALING){
            return false;
        }
        double d1 = PSMXForm.PIVOT_PT.distance(pt);
        double s = d1/d0;
        
        //step1: translate the canvas by worldPivotPt
        Point2D.Double worldPivotPt = this.calcPtFromScreenToWorld((PSMXForm.PIVOT_PT));
        this.mCurXformFromWorldToScreen.translate(worldPivotPt.x, worldPivotPt.y);
        
        //step3: scale the canvas by s
        this.mCurXformFromWorldToScreen.scale(s, s);
        
        //step4: translate the canvas by -worldPivotPt
        this.mCurXformFromWorldToScreen.translate(-worldPivotPt.x, -worldPivotPt.y);
        
        this.updateCurXformFromScreenToWorld();
        return true;  
    }   

    public void home() {
        this.mCurXformFromWorldToScreen.setToIdentity();
        this.updateCurXformFromScreenToWorld();
    }
}

  
