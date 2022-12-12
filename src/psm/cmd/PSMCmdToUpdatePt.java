package psm.cmd;

import X.XApp;
import X.XLoggableCmd;
import java.awt.Point;
import psm.PSM;
import psm.PSMGestureMgr;


public class PSMCmdToUpdatePt extends XLoggableCmd{
    //field
    Point mScreenPt = null;
    //private constructor
    private PSMCmdToUpdatePt(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    public static boolean execute(XApp app, Point pt) {
        PSMCmdToUpdatePt cmd = new PSMCmdToUpdatePt(app, pt);
        return cmd.execute();
    }
    
    
    @Override
    protected boolean defineCmd() {
        PSM psm = (PSM) this.mApp; 
        PSMGestureMgr gestureMgr = PSMGestureMgr.getSingleton();
        gestureMgr.updatePt(mScreenPt);
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
