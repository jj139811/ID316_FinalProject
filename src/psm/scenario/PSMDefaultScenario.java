package psm.scenario;

import X.XApp;
import X.XCmdToChangeScene;
import X.XScenario;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import psm.PSM;
import psm.PSMBrushMgr;
import psm.PSMCamera;
import psm.PSMLayerMgr;
import psm.PSMScene;
import psm.PSMScreenMgr;
import psm.gui.PSMGuiMgr;


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
        this.addScene(PSMDefaultScenario.HideOtherLayersScene.
            createSingleton(this));
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
            if(!PSMGuiMgr.getSingleton().getHandle().isOn(pt)) {
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
            PSMGuiMgr guiMgr = PSMGuiMgr.getSingleton();
            float increment = PSMBrushMgr.
                INCREMENT_FOR_CUR_STROKE_WIDTH;
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_E:
                    XCmdToChangeScene.execute(PSM.getSingleton(),
                        PSMEraseScenario.EraseReadyScene.getSingleton(),
                        this);
                    break;
                case KeyEvent.VK_C:
                    XCmdToChangeScene.execute(psm,
                        PSMColorScenario.ColorReadyScene.getSingleton(),
                        this);
                    break;
                case KeyEvent.VK_SPACE:
                    cam.setScale(1.0f, 1.0f);
                    XCmdToChangeScene.execute(psm,
                        PSMSimulateScenario.SimulateScene.getSingleton(),
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
                case KeyEvent.VK_ENTER:
                    XCmdToChangeScene.execute(psm,
                        PSMSimulateScenario.SimulateScene.getSingleton(),
                        this);
                    break;
                case KeyEvent.VK_SHIFT:
                    layerMgr.setHideOthers(true);
                    XCmdToChangeScene.execute(psm,
                        PSMDefaultScenario.HideOtherLayersScene.getSingleton(),
                        this);
                    break;
                case KeyEvent.VK_UP:
                    PSMBrushMgr.getSingleton().increaseStrokeWidthForCurPtCurve(
                        increment);
                    break;
                case KeyEvent.VK_DOWN:
                    PSMBrushMgr.getSingleton().increaseStrokeWidthForCurPtCurve(
                        -increment);
                    break;
                    //set camera focus(character)
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
            this.drawPenTip(g2);
        }

        @Override
        public void getReady() {
            
        }

        @Override
        public void wrapUp() {
            
        }
        
        private void drawPenTip(Graphics2D g2) {
            g2.setColor(PSMBrushMgr.getSingleton().getColor());
            g2.setStroke(PSMBrushMgr.getSingleton().getStroke());
            float r = 
                ((BasicStroke)PSMBrushMgr.getSingleton().getStroke()).getLineWidth() / 2;
            Ellipse2D penTip = new Ellipse2D.Double(80 - r , 400 -r ,
                2*r, 2*r);
            g2.fill(penTip);
        }
    }
    public static class HideOtherLayersScene extends PSMScene {
        private static HideOtherLayersScene mSingleton = null;
        public static HideOtherLayersScene createSingleton(XScenario scenario) {
            assert (HideOtherLayersScene.mSingleton == null); //false: stop
            HideOtherLayersScene.mSingleton =
                new HideOtherLayersScene(scenario);
            return HideOtherLayersScene.mSingleton;
        }
    
        public static HideOtherLayersScene getSingleton() {
            assert (HideOtherLayersScene.mSingleton != null);
            return HideOtherLayersScene.mSingleton;
        }
        
        private HideOtherLayersScene(XScenario scenario) {
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

        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            PSM psm = PSM.getSingleton();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_SHIFT:
                    layerMgr.setHideOthers(false);
                    XCmdToChangeScene.execute(psm,
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
}
