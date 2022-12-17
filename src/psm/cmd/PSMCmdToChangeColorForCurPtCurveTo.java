package psm.cmd;

import X.XApp;
import X.XLoggableCmd;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import psm.PSM;
import psm.PSMBrushMgr;


public class PSMCmdToChangeColorForCurPtCurveTo extends XLoggableCmd{
    //field
    Color mColor = null;
    
    //private constructor
    //private JSICmdToDoSomething (XApp app, ...) {
    private PSMCmdToChangeColorForCurPtCurveTo(XApp app, Color color) {
        super(app);
        this.mColor = color;
//        this.mScreenPt = pt;
    }
    
    //public static boolean execute(XApp app ,Point pt) {
    public static boolean execute(XApp app, Color color) {
        PSMCmdToChangeColorForCurPtCurveTo cmd = new PSMCmdToChangeColorForCurPtCurveTo(app, color);
        return cmd.execute();
        
    }
    
    
    @Override
    protected boolean defineCmd() {
        PSM psm = (PSM) this.mApp;    
        if (this.mColor == null){
            return false;
        }
        PSMBrushMgr.getSingleton().setColor(this.mColor);
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
