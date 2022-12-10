package psm.scenario;

import X.XApp;
import X.XCmdToChangeScene;
import X.XScenario;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import psm.PSM;
import psm.PSMCamera;
import psm.PSMLayer;
import psm.PSMLayerMgr;
import psm.PSMScene;
import psm.PSMScreenMgr;
import psm.gui.PSMGuiMgr;


public class PSMLayerManageScenario extends XScenario {
    //singleton
    private static PSMLayerManageScenario mSingleton = null;
    
    public static PSMLayerManageScenario createSingleton(XApp app) {
        assert (PSMLayerManageScenario.mSingleton == null);
        PSMLayerManageScenario.mSingleton = new PSMLayerManageScenario(app);
        return PSMLayerManageScenario.mSingleton;
    }
    
    public static PSMLayerManageScenario getSingleton() {
        assert (PSMLayerManageScenario.mSingleton != null);
        return PSMLayerManageScenario.mSingleton;
    }
        
    private PSMLayerManageScenario (XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(PSMLayerManageScenario.PreLayerManageReadyScene.createSingleton(this));
    }

    public static class PreLayerManageReadyScene extends PSMScene {
        private static PreLayerManageReadyScene mSingleton = null;
        public static PreLayerManageReadyScene createSingleton(XScenario scenario) {
            assert (PreLayerManageReadyScene.mSingleton == null); //false: stop
            PreLayerManageReadyScene.mSingleton = new PreLayerManageReadyScene(scenario);
            return PreLayerManageReadyScene.mSingleton;
        }
    
        public static PreLayerManageReadyScene getSingleton() {
            assert (PreLayerManageReadyScene.mSingleton != null);
            return PreLayerManageReadyScene.mSingleton;
        }
        
        private PreLayerManageReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            
        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            PSMLayer focusedLayer = layerMgr.getFocusedLayer();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_A:
                    layerMgr.setFocusedIndex(layerMgr.getFocusedIndex() - 1);
                    break;
                case KeyEvent.VK_D:
                    layerMgr.setFocusedIndex(layerMgr.getFocusedIndex() + 1);
                    break;
                case KeyEvent.VK_W:
                    focusedLayer.setFactor(focusedLayer.getFactor() + 0.1f);
                    break;
                case KeyEvent.VK_S:
                    focusedLayer.setFactor(focusedLayer.getFactor() - 0.1f);
                    break;
            }
            layerMgr.arrangeLayersToListFormat(cam);
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            PSM psm = (PSM) this.mScenario.getApp();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMGuiMgr guiMgr = PSMGuiMgr.getSingleton();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_SPACE:
                    guiMgr.arrangeUisToViewFormat(true);
                    layerMgr.arrangeLayersToViewFormat(cam);
                    XCmdToChangeScene.execute(psm,
                        PSMDefaultScenario.ReadyScene.getSingleton(),
                        null);
                    break;
            }
        }

        @Override
        public void updateSupportObjects() {
            
        }

        @Override
        public void renderWorldObjects(Graphics2D g2) {
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            layerMgr.drawLayers(g2);
        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {
            
        }

        @Override
        public void getReady() {
            
        }

        @Override
        public void wrapUp() {
            
        }
    }
    
}
