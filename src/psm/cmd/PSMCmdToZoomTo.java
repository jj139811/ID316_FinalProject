package psm.cmd;

import X.XApp;
import X.XLoggableCmd;
import java.awt.Point;
import java.awt.geom.Point2D;
import psm.PSM;


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
        psm.getXForm().zoomTo(mScreenPt);
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
