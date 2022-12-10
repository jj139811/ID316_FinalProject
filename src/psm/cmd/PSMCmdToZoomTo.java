package psm.cmd;

import X.XApp;
import X.XLoggableCmd;
import java.awt.Point;
import java.awt.geom.Point2D;
import psm.PSM;
import psm.PSMGestureMgr;
import psm.PSMScreenMgr;


public class PSMCmdToZoomTo extends XLoggableCmd{
    private static final float MIN_STARTING_D = 10.0f;
    //field
    Point mScreenPt = null;
    //private constructor
    //private JSICmdToDoSomething (XApp app, ...) {
    private PSMCmdToZoomTo(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    //public static boolean execute(XApp app ,Point pt) {
    public static boolean execute(XApp app, Point pt) {
        PSMCmdToZoomTo cmd = new PSMCmdToZoomTo(app, pt);
        return cmd.execute();
    }
    
    
    @Override
    protected boolean defineCmd() {
        PSM psm = (PSM) this.mApp;
        PSMGestureMgr gestureMgr = PSMGestureMgr.getSingleton();
        PSMScreenMgr screenMgr = PSMScreenMgr.getSingleton();
        
        Point2D.Float startingCamScale = gestureMgr.getStartingCameraScale();
        Point startingPt = gestureMgr.getStartingPt();
        
        float startingD = (float)startingPt.distance(new Point(0, 0));
        
        if (startingD < MIN_STARTING_D) {
            startingD = MIN_STARTING_D;
        }

        float d = (float)(this.mScreenPt.distance(new Point(0, 0)) /
            startingD);
        
        screenMgr.getCamera().setScale(startingCamScale.x * d,
            startingCamScale.y * d);
        
        
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
//        sb.append(this.mScreenPt).append("\t");
//        sb.append(this.mWorldPt);
        return sb.toString();
        
    }
    
}
