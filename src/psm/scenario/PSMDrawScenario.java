package psm.scenario;

import X.XApp;
import X.XCmdToChangeScene;
import X.XScenario;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import psm.PSM;
import psm.PSMBrushMgr;
import psm.PSMLayerMgr;
import psm.PSMScene;


public class PSMDrawScenario extends XScenario {
    //singleton
    private static PSMDrawScenario mSingleton = null;
    
    public static PSMDrawScenario createSingleton(XApp app) {
        assert (PSMDrawScenario.mSingleton == null);
        PSMDrawScenario.mSingleton = new PSMDrawScenario(app);
        return PSMDrawScenario.mSingleton;
    }
    
    public static PSMDrawScenario getSingleton() {
        assert (PSMDrawScenario.mSingleton != null);
        return PSMDrawScenario.mSingleton;
    }
        
    private PSMDrawScenario (XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(PSMDrawScenario.DrawScene.createSingleton(this));
    }

    public static class DrawScene extends PSMScene {
        private static DrawScene mSingleton = null;
        public static DrawScene createSingleton(XScenario scenario) {
            assert (DrawScene.mSingleton == null); //false: stop
            DrawScene.mSingleton = new DrawScene(scenario);
            return DrawScene.mSingleton;
        }
    
        public static DrawScene getSingleton() {
            assert (DrawScene.mSingleton != null);
            return DrawScene.mSingleton;
        }
        
        private DrawScene(XScenario scenario) {
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
            brushMgr.applyCurLineToLayer(layerMgr.getFocusedLayer());
            XCmdToChangeScene.execute(psm, 
                PSMDefaultScenario.ReadyScene.getSingleton(), null);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {

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
