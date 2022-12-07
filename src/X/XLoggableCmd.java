package X;

public abstract class XLoggableCmd implements XExecutable {
    
    //field
    protected XApp mApp = null;
    
    //constructor
    protected XLoggableCmd (XApp app) {
        this.mApp = app;
    }
    
    @Override
    final public boolean execute() {
        if(this.defineCmd()) {
            this.mApp.getLogMgr().addLog(this.createLog());
            return true;
        } else {
            return false;
        }
        
    }
    
    //abstract method
    protected abstract boolean defineCmd();
    protected abstract String createLog();
    
}
