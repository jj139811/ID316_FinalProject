package psm;

import X.XApp;
import X.XScenarioMgr;
<<<<<<< HEAD
=======
import psm.scenario.PSMDefaultScenario;
import psm.scenario.PSMDrawScenario;
import psm.scenario.PSMLayerManageScenario;
import psm.scenario.PSMNavigateScenario;
>>>>>>> f36cfa8 (temp)
import psm.scenario.PSMTestScenario;

public class PSMScenarioMgr extends XScenarioMgr{
    public PSMScenarioMgr(XApp app) {
        super(app);
    }
    @Override
    protected void addScenarios() {
<<<<<<< HEAD
        this.addScenario(PSMTestScenario.createSingleton(this.mApp));
=======
        this.addScenario(PSMDefaultScenario.createSingleton(this.mApp));
        this.addScenario(PSMDrawScenario.createSingleton(this.mApp));
        this.addScenario(PSMLayerManageScenario.createSingleton(this.mApp));
        this.addScenario(PSMNavigateScenario.createSingleton(this.mApp));
        
>>>>>>> f36cfa8 (temp)
    }

    @Override
    protected void setInitCurScene() {
<<<<<<< HEAD
        this.setCurScene(PSMTestScenario.TestScene.getSingleton());
=======
        //this.setCurScene(PSMTestScenario.TestScene.getSingleton());
        this.setCurScene(PSMDefaultScenario.ReadyScene.getSingleton());
>>>>>>> f36cfa8 (temp)
    }
}