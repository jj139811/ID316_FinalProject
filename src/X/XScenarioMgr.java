package X;

import java.util.ArrayList;

public abstract class XScenarioMgr {
    //field
    protected XApp mApp = null;
    protected ArrayList<XScenario> mScenarios = null;
    protected XScene mCurScene = null;
    public XScene getCurScene() {
        return this.mCurScene;
    }
    public void setCurScene(XScene scene) {
        if (this.mCurScene != null) {
            this.mCurScene.wrapUp();
        }
        scene.getReady();
        this.mCurScene = scene;
    }
    
    //protected constructor
    protected XScenarioMgr(XApp app) {
        this.mApp = app;
        this.mScenarios = new ArrayList<XScenario>();
        this.addScenarios();
        this.setInitCurScene();
    }
    
    //abstract method
    protected abstract void addScenarios();
    protected abstract void setInitCurScene();
    
    //concrete method
    protected void addScenario(XScenario scenario) {
        this.mScenarios.add(scenario);
    }
    
    
}
