package psm;

import java.awt.Point;
import java.awt.geom.Point2D;

public class PSMScreenMgr {
    //singleton
    private static PSMScreenMgr instance = null;
    public static PSMScreenMgr getSingleton() {
        if (instance == null) {
            instance = new PSMScreenMgr();
        }
        return instance;
    }
    
    //field
    private PSMCamera mCamera = null;
    public PSMCamera getCamera() {
        return this.mCamera;
    }
    
    //constructor
    private PSMScreenMgr() {
        this.mCamera = new PSMCamera();
    }
    
    //method
    public Point2D.Float screenPtToWorldPt(Point pt) {
        int x = pt.x;
        int y = pt.y;
        PSMCamera camera = this.mCamera;
        float offsetX = camera.getX();
        float offsetY = camera.getY();
        float scaleX = camera.getScaleX();
        float scaleY = camera.getScaleY();
        return new Point2D.Float(
            (x - PSM.CANVAS_WIDTH / 2) / scaleX + offsetX,
            (y - PSM.CANVAS_HEIGHT / 2) / scaleY + offsetY);
    }
    public Point worldPtToScreenPt(Point2D.Float pt) {
        float x = pt.x;
        float y = pt.y;
        PSMCamera camera = this.mCamera;
        float offsetX = camera.getX();
        float offsetY = camera.getY();
        float scaleX = camera.getScaleX();
        float scaleY = camera.getScaleY();
        return new Point(
            (int)((x - offsetX) * scaleX) + PSM.CANVAS_WIDTH / 2,
            (int)((y - offsetY) * scaleY) + PSM.CANVAS_HEIGHT / 2);
    }
}
