package X;

import java.util.Vector;

public class XLogMgr {
    //field
    private Vector<String> mLogs = null;
    public Vector<String> getLogs() {
        return this.mLogs;
    }
    
    private boolean mPrintOn = false;
    public boolean getPrintOn() {
        return this.mPrintOn;
    }
    public void setPrintOn(boolean isPrintOn) {
        this.mPrintOn = isPrintOn;
    }
    
    //constructor
    public XLogMgr(){
        this.mLogs = new Vector<String>();
    }
    
    //method
    public void addLog(String log) {
        this.mLogs.add(log);
        if(this.mPrintOn) {
            System.out.println(log);
        }
    }
}
