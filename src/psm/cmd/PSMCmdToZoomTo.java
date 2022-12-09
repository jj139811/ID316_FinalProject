package psm.cmd;

import X.XApp;
import X.XLoggableCmd;
import java.awt.Point;
import java.awt.geom.Point2D;
import psm.PSM;
import psm.PSMGestureMgr;
import psm.PSMScreenMgr;


public class PSMCmdToZoomTo extends XLoggableCmd{
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
        //pivot 중심으로 recent point만큼 떨어진 거리의 인버스로 Camera scale 조절 
        float screenPtX = this.mScreenPt.x;
        float screenPtY = this.mScreenPt.y;
        PSMGestureMgr gestureMgr = PSMGestureMgr.getSingleton();
        PSMScreenMgr screenMgr = PSMScreenMgr.getSingleton();
        
        
        //calculate scaling factor
        float d0 = (float) PSMGestureMgr.PIVOT_PT.distance(gestureMgr.getStartingPt());
        if (d0 < PSMGestureMgr.MIN_START_ARM_LENGTH_FOR_SCALING){
            return false;
        }
        float d1 = (float) PSMGestureMgr.PIVOT_PT.distance(this.mScreenPt);
        float s = d1/d0;
        
        //scale camera by scaling factor 
        screenMgr.getCamera().setScale(s, s);
        
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
