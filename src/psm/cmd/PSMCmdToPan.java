package psm.cmd;

import X.XApp;
import X.XLoggableCmd;
import java.awt.Point;
import java.awt.geom.Point2D;
import psm.PSM;
import psm.PSMGestureMgr;
import psm.PSMScreenMgr;


public class PSMCmdToPan extends XLoggableCmd{
    //field
    Point mScreenPt = null;
    //private constructor
    //private JSICmdToDoSomething (XApp app, ...) {
    private PSMCmdToPan(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    //public static boolean execute(XApp app ,Point pt) {
    public static boolean execute(XApp app, Point pt) {
        PSMCmdToPan cmd = new PSMCmdToPan(app, pt);
        return cmd.execute();
    }
    
    
    @Override
    protected boolean defineCmd() {
        PSM psm = (PSM) this.mApp; 
        //pan camera comd(가장 recent한 point로 set position??)
        float screenPtX = this.mScreenPt.x;
        float screenPtY = this.mScreenPt.y;
        PSMGestureMgr gestureMgr = PSMGestureMgr.getSingleton();
        
        PSMScreenMgr screenMgr = PSMScreenMgr.getSingleton();
        screenMgr.getCamera().setPosition(screenPtX, screenPtY);
        
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
