package psm;

import X.XApp;
import X.XScenarioMgr;
import psm.scenario.PSMTestScenario;

public class PSMScenarioMgr extends XScenarioMgr{
    public PSMScenarioMgr(XApp app) {
        super(app);
    }
    @Override
    protected void addScenarios() {
        this.addScenario(PSMTestScenario.createSingleton(this.mApp));
    }

    @Override
    protected void setInitCurScene() {
        this.setCurScene(PSMTestScenario.TestScene.getSingleton());
    }
}