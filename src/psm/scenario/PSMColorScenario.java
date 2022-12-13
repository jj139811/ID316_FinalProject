package psm.scenario;

import X.XApp;
import X.XScenario;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import psm.PSMScene;


public class PSMColorScenario extends XScenario {
    //singleton
    private static PSMColorScenario mSingleton = null;
    
    public static PSMColorScenario createSingleton(XApp app) {
        assert (PSMColorScenario.mSingleton == null);
        PSMColorScenario.mSingleton = new PSMColorScenario(app);
        return PSMColorScenario.mSingleton;
    }
    
    public static PSMColorScenario getSingleton() {
        assert (PSMColorScenario.mSingleton != null);
        return PSMColorScenario.mSingleton;
    }
        
    private PSMColorScenario (XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(PSMColorScenario.ColorReadyScene.createSingleton(this));
        this.addScene(PSMColorScenario.ColorChangeScene.createSingleton(this));
    }

    public static class ColorReadyScene extends PSMScene {
        private static ColorReadyScene mSingleton = null;
        public static ColorReadyScene createSingleton(XScenario scenario) {
            assert (ColorReadyScene.mSingleton == null); //false: stop
            ColorReadyScene.mSingleton = new ColorReadyScene(scenario);
            return ColorReadyScene.mSingleton;
        }
    
        public static ColorReadyScene getSingleton() {
            assert (ColorReadyScene.mSingleton != null);
            return ColorReadyScene.mSingleton;
        }
        
        private ColorReadyScene(XScenario scenario) {
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
            // color wheel ui 그리기 
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
    
    public static class ColorChangeScene extends PSMScene {
        private static ColorChangeScene mSingleton = null;
        public static ColorChangeScene createSingleton(XScenario scenario) {
            assert (ColorChangeScene.mSingleton == null); //false: stop
            ColorChangeScene.mSingleton = new ColorChangeScene(scenario);
            return ColorChangeScene.mSingleton;
        }
    
        public static ColorChangeScene getSingleton() {
            assert (ColorChangeScene.mSingleton != null);
            return ColorChangeScene.mSingleton;
        }
        
        private ColorChangeScene(XScenario scenario) {
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
