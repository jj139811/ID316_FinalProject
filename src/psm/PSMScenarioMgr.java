package psm;

import X.XApp;
import X.XScenarioMgr;
import psm.scenario.PSMDefaultScenario;
import psm.scenario.PSMDrawScenario;
import psm.scenario.PSMEraseScenario;
import psm.scenario.PSMLayerManageScenario;
import psm.scenario.PSMNavigateScenario;
import psm.scenario.PSMTestScenario;

public class PSMScenarioMgr extends XScenarioMgr{
    public PSMScenarioMgr(XApp app) {
        super(app);
    }
    @Override
    protected void addScenarios() {
        this.addScenario(PSMTestScenario.createSingleton(this.mApp));
        this.addScenario(PSMDefaultScenario.createSingleton(this.mApp));
        this.addScenario(PSMDrawScenario.createSingleton(this.mApp));
        this.addScenario(PSMLayerManageScenario.createSingleton(this.mApp));
        this.addScenario(PSMNavigateScenario.createSingleton(this.mApp));
        this.addScenario(PSMEraseScenario.createSingleton(this.mApp));
    }

    @Override
    protected void setInitCurScene() {
        //this.setCurScene(PSMTestScenario.TestScene.getSingleton());
        //this.setCurScene(PSMTestScenario.TestScene.getSingleton());
        this.setCurScene(PSMDefaultScenario.ReadyScene.getSingleton());
    }
}