package psm.scenario;

import X.XApp;
import X.XCmdToChangeScene;
import X.XScenario;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import psm.PSM;
import psm.PSMBrushMgr;
import psm.PSMCamera;
import psm.PSMLayer;
import psm.PSMLayerMgr;
import psm.PSMScene;
import psm.PSMScreenMgr;


public class PSMTestScenario extends XScenario {
    //singleton
    private static PSMTestScenario mSingleton = null;
    
    public static PSMTestScenario createSingleton(XApp app) {
        assert (PSMTestScenario.mSingleton == null);
        PSMTestScenario.mSingleton = new PSMTestScenario(app);
        return PSMTestScenario.mSingleton;
    }
    
    public static PSMTestScenario getSingleton() {
        assert (PSMTestScenario.mSingleton != null);
        return PSMTestScenario.mSingleton;
    }
        
    private PSMTestScenario (XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(PSMTestScenario.TestScene.createSingleton(this));
        this.addScene(PSMTestScenario.TestListViewScene.createSingleton(this));
    }

    public static class TestScene extends PSMScene {
        private static TestScene mSingleton = null;
        public static TestScene createSingleton(XScenario scenario) {
            assert (TestScene.mSingleton == null); //false: stop
            TestScene.mSingleton = new TestScene(scenario);
            return TestScene.mSingleton;
        }
    
        public static TestScene getSingleton() {
            assert (TestScene.mSingleton != null);
            return TestScene.mSingleton;
        }
        
        private TestScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            PSMBrushMgr brushMgr = PSMBrushMgr.getSingleton();
            brushMgr.addPt(e.getPoint());
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            PSMBrushMgr brushMgr = PSMBrushMgr.getSingleton();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            brushMgr.applyCurLineToLayer(layerMgr.getFocusedLayer());
        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_W:
                    cam.move(0, -50);
                    break;
                case KeyEvent.VK_A:
                    cam.move(-50, 0);
                    break;
                case KeyEvent.VK_S:
                    cam.move(0, 50);
                    break;
                case KeyEvent.VK_D:
                    cam.move(50, 0);
                    break;
                case KeyEvent.VK_SPACE:
                    layerMgr.arrangeLayersToListFormat(cam);
                    XCmdToChangeScene.execute(PSM.getSingleton(),
                        PSMTestScenario.TestListViewScene.getSingleton(),
                        this.mReturnScene);
                    break;
            }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            
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
    public static class TestListViewScene extends PSMScene {
        private static TestListViewScene mSingleton = null;
        public static TestListViewScene createSingleton(XScenario scenario) {
            assert (TestListViewScene.mSingleton == null); //false: stop
            TestListViewScene.mSingleton = new TestListViewScene(scenario);
            return TestListViewScene.mSingleton;
        }
    
        public static TestListViewScene getSingleton() {
            assert (TestListViewScene.mSingleton != null);
            return TestListViewScene.mSingleton;
        }
        
        private TestListViewScene(XScenario scenario) {
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
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_SPACE:
                    layerMgr.arrangeLayersToViewFormat(cam);
                    XCmdToChangeScene.execute(PSM.getSingleton(),
                        PSMTestScenario.TestScene.getSingleton(),
                        this.mReturnScene);
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
