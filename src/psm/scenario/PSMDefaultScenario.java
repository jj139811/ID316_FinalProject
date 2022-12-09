package psm.scenario;

import X.XApp;
import X.XCmdToChangeScene;
import X.XScenario;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import psm.PSM;
import psm.PSMCamera;
import psm.PSMLayerMgr;
import psm.PSMScene;
import psm.PSMScreenMgr;


public class PSMDefaultScenario extends XScenario {
    //singleton
    private static PSMDefaultScenario mSingleton = null;
    
    public static PSMDefaultScenario createSingleton(XApp app) {
        assert (PSMDefaultScenario.mSingleton == null);
        PSMDefaultScenario.mSingleton = new PSMDefaultScenario(app);
        return PSMDefaultScenario.mSingleton;
    }
    
    public static PSMDefaultScenario getSingleton() {
        assert (PSMDefaultScenario.mSingleton != null);
        return PSMDefaultScenario.mSingleton;
    }
        
    private PSMDefaultScenario (XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(PSMDefaultScenario.ReadyScene.createSingleton(this));
    }

    public static class ReadyScene extends PSMScene {
        private static ReadyScene mSingleton = null;
        public static ReadyScene createSingleton(XScenario scenario) {
            assert (ReadyScene.mSingleton == null); //false: stop
            ReadyScene.mSingleton = new ReadyScene(scenario);
            return ReadyScene.mSingleton;
        }
    
        public static ReadyScene getSingleton() {
            assert (ReadyScene.mSingleton != null);
            return ReadyScene.mSingleton;
        }
        
        private ReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            PSM psm = (PSM) this.mScenario.getApp();
            Point pt = e.getPoint();
            //isOnHandle?
            boolean isOnHandle = false;
            if(!isOnHandle) {
                XCmdToChangeScene.execute(psm, 
                PSMDrawScenario.DrawScene.getSingleton(), this);
            } else {
                XCmdToChangeScene.execute(psm, 
                PSMLayerManageScenario.PreLayerManageReadyScene.getSingleton(), this);
            }
            
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            
        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            PSM psm = PSM.getSingleton();
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
                    XCmdToChangeScene.execute(psm,
                        PSMLayerManageScenario.PreLayerManageReadyScene.getSingleton(),
                        this);
                    break;
                case KeyEvent.VK_CONTROL:
                    XCmdToChangeScene.execute(psm,
                        PSMNavigateScenario.PanReadyScene.getSingleton(),
                        this);
                    break;
                    
                case KeyEvent.VK_ALT:
                    XCmdToChangeScene.execute(psm,
                        PSMNavigateScenario.ZoomReadyScene.getSingleton(),
                        this);
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
    
}
