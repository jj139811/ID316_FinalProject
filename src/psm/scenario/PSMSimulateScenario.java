package psm.scenario;

import X.XApp;
import X.XCmdToChangeScene;
import X.XScenario;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import psm.PSM;
import psm.PSMCamera;
import psm.PSMGestureMgr;
import psm.PSMLayerMgr;
import psm.PSMScene;
import psm.PSMScreenMgr;
import psm.gui.PSMGuiMgr;


public class PSMSimulateScenario extends XScenario {
    //singleton
    private static PSMSimulateScenario mSingleton = null;
    
    public static PSMSimulateScenario createSingleton(XApp app) {
        assert (PSMSimulateScenario.mSingleton == null);
        PSMSimulateScenario.mSingleton = new PSMSimulateScenario(app);
        return PSMSimulateScenario.mSingleton;
    }
    
    public static PSMSimulateScenario getSingleton() {
        assert (PSMSimulateScenario.mSingleton != null);
        return PSMSimulateScenario.mSingleton;
    }
        
    private PSMSimulateScenario (XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(PSMSimulateScenario.SimulateScene.createSingleton(this));
        this.addScene(PSMSimulateScenario.CharacterMoveScene.createSingleton(this));
    }

    public static class SimulateScene extends PSMScene {
        private static SimulateScene mSingleton = null;
        public static SimulateScene createSingleton(XScenario scenario) {
            assert (SimulateScene.mSingleton == null); //false: stop
            SimulateScene.mSingleton = new SimulateScene(scenario);
            return SimulateScene.mSingleton;
        }
    
        public static SimulateScene getSingleton() {
            assert (SimulateScene.mSingleton != null);
            return SimulateScene.mSingleton;
        }
        
        private SimulateScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            PSM psm = (PSM) this.mScenario.getApp();
            Point pt = e.getPoint();
            XCmdToChangeScene.execute(psm,
                    PSMSimulateScenario.CharacterMoveScene.getSingleton(), 
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
            PSM psm = (PSM) this.mScenario.getApp();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMGuiMgr guiMgr = PSMGuiMgr.getSingleton();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_SPACE:
                    //setCameraFocus(null)
                    layerMgr.getCharLayer().syncPosition(cam, true);
                    layerMgr.arrangeLayersToViewFormat(cam);
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
    
    public static class CharacterMoveScene extends PSMScene {
        private static CharacterMoveScene mSingleton = null;
        public static CharacterMoveScene createSingleton(XScenario scenario) {
            assert (CharacterMoveScene.mSingleton == null); //false: stop
            CharacterMoveScene.mSingleton = new CharacterMoveScene(scenario);
            return CharacterMoveScene.mSingleton;
        }
    
        public static CharacterMoveScene getSingleton() {
            assert (CharacterMoveScene.mSingleton != null);
            return CharacterMoveScene.mSingleton;
        }
        
        private CharacterMoveScene(XScenario scenario) {
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
            PSM psm = (PSM) this.mScenario.getApp();
            XCmdToChangeScene.execute(psm,
                PSMSimulateScenario.SimulateScene.getSingleton(),
                this.mReturnScene);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {
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
                    layerMgr.getCharLayer().syncPosition(cam, true);
                    layerMgr.arrangeLayersToViewFormat(cam);
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
            PSMGestureMgr gestureMgr = PSMGestureMgr.getSingleton();
            Point startingPt = gestureMgr.getStartingPt();
            Point currentPt = gestureMgr.getCurrentPt();
            assert (startingPt != null && currentPt != null);
            Ellipse2D pivot = new Ellipse2D.Float(
                startingPt.x - 25, startingPt.y - 25, 50, 50);
            Line2D handle = new Line2D.Float(
                startingPt.x, startingPt.y, currentPt.x, currentPt.y);
            g2.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
            g2.setStroke(new BasicStroke(2.0f));
            g2.fill(pivot);
            g2.draw(handle);
        }

        @Override
        public void getReady() {
            
        }

        @Override
        public void wrapUp() {
            
        }
    }
    
}
