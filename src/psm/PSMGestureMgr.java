package psm;

import java.awt.Point;
import java.awt.geom.Point2D;

public class PSMGestureMgr {
    //singleton
    private static PSMGestureMgr instance = null;
    public static PSMGestureMgr getSingleton() {
        if (instance == null) {
            instance = new PSMGestureMgr();
        }
        return instance;
    }
    
    //constants
    public static final double MIN_START_ARM_LENGTH_FOR_SCALING = 100.0;
    public static final Point PIVOT_PT =new Point(100, 100);
    
    //field
    private Point mStartingPt = null;
    private Point2D.Float mStartingWorldPt = null;
    private Point2D.Float mStartingCameraPos = null;
    public Point getStartingPt() {
        return this.mStartingPt;
    }
    public Point2D.Float getStartingWorldPt() {
        return this.mStartingWorldPt;
    }
    public Point2D.Float getStartingCameraPos() {
        return this.mStartingCameraPos;
    }
    
    public void setStartingPt(Point pt) {
        PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
        this.mStartingPt = pt;
        this.mStartingWorldPt = PSMScreenMgr.getSingleton().
            screenPtToWorldPt(pt);
        this.mStartingCameraPos = new Point2D.Float(
            cam.getX(), cam.getY());
    }
    
    //constructor
    public PSMGestureMgr() {
        this.mStartingPt = new Point();
    }
    
    
}
