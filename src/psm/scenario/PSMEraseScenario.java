package psm.scenario;

import X.XApp;
import X.XCmdToChangeScene;
import X.XScenario;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import psm.PSM;
import psm.PSMBrushMgr;
import psm.PSMLayerMgr;
import psm.PSMScene;


public class PSMEraseScenario extends XScenario {
    //singleton
    private static PSMEraseScenario mSingleton = null;
    
    public static PSMEraseScenario createSingleton(XApp app) {
        assert (PSMEraseScenario.mSingleton == null);
        PSMEraseScenario.mSingleton = new PSMEraseScenario(app);
        return PSMEraseScenario.mSingleton;
    }
    
    public static PSMEraseScenario getSingleton() {
        assert (PSMEraseScenario.mSingleton != null);
        return PSMEraseScenario.mSingleton;
    }
        
    private PSMEraseScenario (XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(PSMEraseScenario.EraseReadyScene.createSingleton(this));
        this.addScene(PSMEraseScenario.EraseScene.createSingleton(this));
    }
    public static class EraseReadyScene extends PSMScene {
        private static EraseReadyScene mSingleton = null;
        public static EraseReadyScene createSingleton(XScenario scenario) {
            assert (EraseReadyScene.mSingleton == null); //false: stop
            EraseReadyScene.mSingleton = new EraseReadyScene(scenario);
            return EraseReadyScene.mSingleton;
        }
    
        public static EraseReadyScene getSingleton() {
            assert (EraseReadyScene.mSingleton != null);
            return EraseReadyScene.mSingleton;
        }
        
        private EraseReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            XCmdToChangeScene.execute(PSM.getSingleton(),
                PSMEraseScenario.EraseScene.getSingleton(),
                this.mReturnScene);
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
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_E:
                    XCmdToChangeScene.execute(PSM.getSingleton(),
                        this.mReturnScene,
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
    public static class EraseScene extends PSMScene {
        private static EraseScene mSingleton = null;
        public static EraseScene createSingleton(XScenario scenario) {
            assert (EraseScene.mSingleton == null); //false: stop
            EraseScene.mSingleton = new EraseScene(scenario);
            return EraseScene.mSingleton;
        }
    
        public static EraseScene getSingleton() {
            assert (EraseScene.mSingleton != null);
            return EraseScene.mSingleton;
        }
        
        private EraseScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            PSM psm = (PSM) this.mScenario.getApp();
            Point pt = e.getPoint();
            PSMBrushMgr brushMgr = PSMBrushMgr.getSingleton();
            brushMgr.addPt(e.getPoint());
            //brushMgr.drawCurLine();
            
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            PSM psm = (PSM) this.mScenario.getApp();
            PSMBrushMgr brushMgr = PSMBrushMgr.getSingleton();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            brushMgr.eraseLayerWithCurLine(layerMgr.getFocusedLayer());
            XCmdToChangeScene.execute(psm, 
                PSMDefaultScenario.ReadyScene.getSingleton(), null);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {

        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_E:
                    XCmdToChangeScene.execute(PSM.getSingleton(),
                        this.mReturnScene,
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
            g2.setComposite(AlphaComposite.Clear);
            PSMBrushMgr.getSingleton().drawCurLine(g2);
            g2.setComposite(AlphaComposite.SrcOver);
        }

        @Override
        public void getReady() {
            
        }

        @Override
        public void wrapUp() {
            
        }
    }
    
}
