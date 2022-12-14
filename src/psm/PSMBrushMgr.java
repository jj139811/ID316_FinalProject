package psm;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PSMBrushMgr {
    //singleton
    private static PSMBrushMgr instance = null;
    public static PSMBrushMgr getSingleton() {
        if (instance == null) {
            instance = new PSMBrushMgr();
        }
        return instance;
    }
    
    //constant
    public static final float INCREMENT_FOR_CUR_STROKE_WIDTH = 1f;
    public static final float MIN_STROKE_WIDTH = 3f;
    
    //field
    private ArrayList<Point> mCurLine = null;
    private Color mCurColor = Color.BLACK;
    public Color getColor() {
        return this.mCurColor;
    }
    public void setColor(Color color) {
        this.mCurColor = color;
    }
    
    private float mCurStrokeWidth = 5f;
    private Stroke mCurStroke = null;
    public Stroke getStroke() {
        return this.mCurStroke;
    }
    public void setStrokeWidth(float width) {
        this.mCurStrokeWidth = width;
        this.mCurStroke = new BasicStroke(width);
    }
    
    private int mAppliedIndex = 0;
    
    //constructor
    private PSMBrushMgr() {
        this.mCurLine = new ArrayList<>();
        this.mCurStroke = new BasicStroke(this.mCurStrokeWidth);
    }
    
    //method
    public void addPt(Point pt) {
        this.mCurLine.add(pt);
    }
    public void drawCurLine(Graphics2D g) {
        if (this.mCurLine.isEmpty()) {
            return;
        }
        
        Path2D.Float path  = new Path2D.Float();
        
        ArrayList<Point> pts = this.mCurLine;
        
        Point pt0 = pts.get(0);
        path.moveTo((float)pt0.x, (float)pt0.y);
        for (int i = 1 ; i < pts.size() ; i++) {
            Point pt = pts.get(i);
            path.lineTo((float)pt.x, (float)pt.y);
        }
        
        g.setColor(this.mCurColor);
        g.setStroke(new BasicStroke(this.mCurStrokeWidth *
            PSMScreenMgr.getSingleton().getCamera().getScaleX()));
        g.draw(path);
    }
    public void drawPen(Graphics2D g, Point pt) {
        PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
        float rx = this.mCurStrokeWidth * cam.getScaleX();
        float ry = this.mCurStrokeWidth * cam.getScaleY();
        Ellipse2D circle = new Ellipse2D.Float(
            (float)pt.x - rx / 2, (float)pt.y - ry / 2,
            rx, ry);
        g.setColor(this.mCurColor);
        g.draw(circle);
    }
    public void applyCurLineToLayer(PSMLayer targetLayer) {
        Graphics2D g = targetLayer.getGraphics();
        if (g == null) {
            return;
        }
        if (this.mCurLine.isEmpty()) {
            return;
        }
        PSMScreenMgr screenMgr = PSMScreenMgr.getSingleton();
        
        Path2D.Float path  = new Path2D.Float();
        
        ArrayList<Point> pts = this.mCurLine;
        
        Point2D.Float pt0 = targetLayer.worldPtToLayerLocalPt(
            screenMgr.screenPtToWorldPt(pts.get(this.mAppliedIndex)));
        path.moveTo(pt0.x, pt0.y);
        for (int i = this.mAppliedIndex + 1 ; i < pts.size() ; i++) {
            Point2D.Float pt = targetLayer.worldPtToLayerLocalPt(
                screenMgr.screenPtToWorldPt(pts.get(i)));
            path.lineTo(pt.x, pt.y);
            this.mAppliedIndex = i;
        }
        
        g.setColor(this.mCurColor);
        g.setStroke(this.mCurStroke);
        g.draw(path);
    }
    
    public void eraseLayerWithCurLine(PSMLayer targetLayer) {
        Graphics2D g = targetLayer.getGraphics();
        if (g == null) {
            return;
        }
        if (this.mCurLine.isEmpty()) {
            return;
        }
        PSMScreenMgr screenMgr = PSMScreenMgr.getSingleton();
        
        Path2D.Float path  = new Path2D.Float();
        
        ArrayList<Point> pts = this.mCurLine;
        
        Point2D.Float pt0 = targetLayer.worldPtToLayerLocalPt(
            screenMgr.screenPtToWorldPt(pts.get(this.mAppliedIndex)));
        path.moveTo(pt0.x, pt0.y);
        for (int i = this.mAppliedIndex + 1; i < pts.size() ; i++) {
            Point2D.Float pt = targetLayer.worldPtToLayerLocalPt(
                screenMgr.screenPtToWorldPt(pts.get(i)));
            path.lineTo(pt.x, pt.y);
            this.mAppliedIndex = i;
        }
        
        g.setComposite(AlphaComposite.Clear);
        
        g.setColor(this.mCurColor);
        g.setStroke(this.mCurStroke);
        g.draw(path);
        
        g.setComposite(AlphaComposite.SrcOver);
    }
    
    public void increaseStrokeWidthForCurPtCurve(float f) {
        BasicStroke bs = (BasicStroke) this.mCurStroke;
        float w = bs.getLineWidth();
        w += f ;
        if (w < MIN_STROKE_WIDTH) {
            w = MIN_STROKE_WIDTH;
        } 
        this.mCurStroke = new BasicStroke(w, bs.getEndCap(),
            bs.getLineJoin());
    }
    
    public void clear() {
        this.mAppliedIndex = 0;
        this.mCurLine.clear();
    }
}
