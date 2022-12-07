package psm.scenario;

import X.XApp;
import X.XScenario;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import psm.PSMScene;


public class PSMEmptyScenario extends XScenario {
    //singleton
    private static PSMEmptyScenario mSingleton = null;
    
    public static PSMEmptyScenario createSingleton(XApp app) {
        assert (PSMEmptyScenario.mSingleton == null);
        PSMEmptyScenario.mSingleton = new PSMEmptyScenario(app);
        return PSMEmptyScenario.mSingleton;
    }
    
    public static PSMEmptyScenario getSingleton() {
        assert (PSMEmptyScenario.mSingleton != null);
        return PSMEmptyScenario.mSingleton;
    }
        
    private PSMEmptyScenario (XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(PSMEmptyScenario.EmptyScene.createSingleton(this));
    }

    public static class EmptyScene extends PSMScene {
        private static EmptyScene mSingleton = null;
        public static EmptyScene createSingleton(XScenario scenario) {
            assert (EmptyScene.mSingleton == null); //false: stop
            EmptyScene.mSingleton = new EmptyScene(scenario);
            return EmptyScene.mSingleton;
        }
    
        public static EmptyScene getSingleton() {
            assert (EmptyScene.mSingleton != null);
            return EmptyScene.mSingleton;
        }
        
        private EmptyScene(XScenario scenario) {
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

        }

        @Override
        public void updateSupportObjects() {
            
        }

        @Override
        public void renderWorldObjects(Graphics2D g2) {
            
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
