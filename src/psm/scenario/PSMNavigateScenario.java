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
import psm.cmd.PSMCmdToZoomTo;


public class PSMNavigateScenario extends XScenario {
    //singleton
    private static PSMNavigateScenario mSingleton = null;
    
    public static PSMNavigateScenario createSingleton(XApp app) {
        assert (PSMNavigateScenario.mSingleton == null);
        PSMNavigateScenario.mSingleton = new PSMNavigateScenario(app);
        return PSMNavigateScenario.mSingleton;
    }
    
    public static PSMNavigateScenario getSingleton() {
        assert (PSMNavigateScenario.mSingleton != null);
        return PSMNavigateScenario.mSingleton;
    }
        
    private PSMNavigateScenario (XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(PSMNavigateScenario.PanReadyScene.createSingleton(this));
        this.addScene(PSMNavigateScenario.PanScene.createSingleton(this));
        this.addScene(PSMNavigateScenario.ZoomReadyScene.createSingleton(this));
        this.addScene(PSMNavigateScenario.ZoomScene.createSingleton(this));
    }

    public static class PanReadyScene extends PSMScene {
        private static PanReadyScene mSingleton = null;
        public static PanReadyScene createSingleton(XScenario scenario) {
            assert (PanReadyScene.mSingleton == null); //false: stop
            PanReadyScene.mSingleton = new PanReadyScene(scenario);
            return PanReadyScene.mSingleton;
        }
    
        public static PanReadyScene getSingleton() {
            assert (PanReadyScene.mSingleton != null);
            return PanReadyScene.mSingleton;
        }
        
        private PanReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            PSM psm = PSM.getSingleton();
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            Point pt = e.getPoint();
            XCmdToChangeScene.execute(psm,
                PSMNavigateScenario.PanScene.getSingleton(),
                this.mReturnScene);
            //set starting point method
            
            

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            
        }

        @Override
        public void handleKeyDown(KeyEvent e) {

        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            PSM psm = PSM.getSingleton();
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_CONTROL:
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
    
    public static class PanScene extends PSMScene {
        private static PanScene mSingleton = null;
        public static PanScene createSingleton(XScenario scenario) {
            assert (PanScene.mSingleton == null); //false: stop
            PanScene.mSingleton = new PanScene(scenario);
            return PanScene.mSingleton;
        }
    
        public static PanScene getSingleton() {
            assert (PanScene.mSingleton != null);
            return PanScene.mSingleton;
        }
        
        private PanScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            //Translate camera to method
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            PSM psm = PSM.getSingleton();
            Point pt = e.getPoint();
            XCmdToChangeScene.execute(psm,
                PSMNavigateScenario.PanReadyScene.getSingleton(),
                this.mReturnScene);
            //set starting point method
        }

        @Override
        public void handleKeyDown(KeyEvent e) {

        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            PSM psm = PSM.getSingleton();
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_CONTROL:
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
    
    public static class ZoomReadyScene extends PSMScene {
        private static ZoomReadyScene mSingleton = null;
        public static ZoomReadyScene createSingleton(XScenario scenario) {
            assert (ZoomReadyScene.mSingleton == null); //false: stop
            ZoomReadyScene.mSingleton = new ZoomReadyScene(scenario);
            return ZoomReadyScene.mSingleton;
        }
    
        public static ZoomReadyScene getSingleton() {
            assert (ZoomReadyScene.mSingleton != null);
            return ZoomReadyScene.mSingleton;
        }
        
        private ZoomReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            PSM psm = PSM.getSingleton();
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            Point pt = e.getPoint();
            XCmdToChangeScene.execute(psm,
                PSMNavigateScenario.ZoomScene.getSingleton(),
                this.mReturnScene);
            //set starting point method
            psm.getXForm().setStartScreenPt(pt);
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            
        }

        @Override
        public void handleKeyDown(KeyEvent e) {

        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            PSM psm = PSM.getSingleton();
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_ALT:
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
    
    public static class ZoomScene extends PSMScene {
        private static ZoomScene mSingleton = null;
        public static ZoomScene createSingleton(XScenario scenario) {
            assert (ZoomScene.mSingleton == null); //false: stop
            ZoomScene.mSingleton = new ZoomScene(scenario);
            return ZoomScene.mSingleton;
        }
    
        public static ZoomScene getSingleton() {
            assert (ZoomScene.mSingleton != null);
            return ZoomScene.mSingleton;
        }
        
        private ZoomScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            PSM psm = PSM.getSingleton();
            Point pt = e.getPoint();
            PSMCmdToZoomTo.execute(psm, pt);
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            PSM psm = PSM.getSingleton();
            XCmdToChangeScene.execute(psm,
                PSMNavigateScenario.ZoomReadyScene.getSingleton(),
                this.mReturnScene);
            //set starting point method
            psm.getXForm().setStartScreenPt(null);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {

        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            PSM psm = PSM.getSingleton();
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_ALT:
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
