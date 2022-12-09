package psm;

import java.awt.Point;

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
    public Point getStartingPt() {
        return this.mStartingPt;
    }
    
    public void setStartingPt(Point pt) {
        this.mStartingPt = pt;
    }
    
    //constructor
    public PSMGestureMgr() {
        this.mStartingPt = new Point();
    }
    
    
}
