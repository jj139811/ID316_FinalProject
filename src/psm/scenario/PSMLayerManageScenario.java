package psm.scenario;

import X.XApp;
import X.XCmdToChangeScene;
import X.XScenario;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import psm.PSM;
import psm.PSMBgLayer;
import psm.PSMCamera;
import psm.PSMGestureMgr;
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
        this.addScene(PSMLayerManageScenario.LayerManageReadyScene.createSingleton(this));
        this.addScene(PSMLayerManageScenario.SlidingScene.createSingleton(this));
        this.addScene(PSMLayerManageScenario.CharLayerHoldingScene.createSingleton(this));
        this.addScene(PSMLayerManageScenario.HighlightedLayerHoldingScene.createSingleton(this));
        this.addScene(PSMLayerManageScenario.NewLayerHoldingScene.createSingleton(this));
        
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
            //dragged enough?
            //update HandlePos
            PSM psm = (PSM) this.mScenario.getApp();
            Point pt = e.getPoint();
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            PSMGuiMgr guiMgr = PSMGuiMgr.getSingleton();
            boolean isDraggedEnough = true;
            if (!isDraggedEnough) {
                
            } else {
                layerMgr.arrangeLayersToListFormat(cam);
                guiMgr.arrangeUisToListFormat(true);
                XCmdToChangeScene.execute(psm, 
                PSMLayerManageScenario.LayerManageReadyScene.getSingleton(), null);
            }
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            PSM psm = (PSM) this.mScenario.getApp();
            //reset handle pos
            XCmdToChangeScene.execute(psm, 
                PSMDefaultScenario.ReadyScene.getSingleton(), null);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            PSMLayer focusedLayer = layerMgr.getFocusedLayer();
            int code = e.getKeyCode();
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
    
    public static class   LayerManageReadyScene extends PSMScene {
        private static LayerManageReadyScene mSingleton = null;
        public static LayerManageReadyScene createSingleton(XScenario scenario) {
            assert (LayerManageReadyScene.mSingleton == null); //false: stop
            LayerManageReadyScene.mSingleton = new LayerManageReadyScene(scenario);
            return LayerManageReadyScene.mSingleton;
        }
    
        public static LayerManageReadyScene getSingleton() {
            assert (LayerManageReadyScene.mSingleton != null);
            return LayerManageReadyScene.mSingleton;
        }
        
        private LayerManageReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            PSM psm = (PSM) this.mScenario.getApp();
            Point pt = e.getPoint();
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            PSMLayer focusedLayer = layerMgr.getFocusedLayer();
            PSMGuiMgr guiMgr = PSMGuiMgr.getSingleton();
            if (!PSMGuiMgr.getSingleton().getNewLayer().isOn(pt)) {
                if (!PSMGuiMgr.getSingleton().getCharLayer().isOn(pt)) {
                    if (!PSMGuiMgr.getSingleton().getHighlightedLayer().isOn(pt)) {
                        XCmdToChangeScene.execute(psm, 
                            PSMLayerManageScenario.SlidingScene.getSingleton(), this);
                    } else {
                        if (!PSMGestureMgr.getSingleton().isTap()) {
                            if (!PSMGestureMgr.getSingleton().isDrag()) {
                                XCmdToChangeScene.execute(psm, 
                                    PSMLayerManageScenario.HighlightedLayerHoldingScene.getSingleton(), this);
                            } else {
                                XCmdToChangeScene.execute(psm, 
                                    PSMLayerManageScenario.SlidingScene.getSingleton(), this);
                            }
                        } else {
                            //setFocusedLayer(Highlighted layer)
                            layerMgr.setFocusedIndex(layerMgr.getFocusedIndex());
                            //다시 focused view로
                            layerMgr.arrangeLayersToViewFormat(cam);
                            guiMgr.arrangeUisToViewFormat(true);
                            XCmdToChangeScene.execute(psm, 
                                PSMDefaultScenario.ReadyScene.getSingleton(), this);
                        }
                    }
                } else {
                    XCmdToChangeScene.execute(psm, 
                        PSMLayerManageScenario.CharLayerHoldingScene.getSingleton(), null);
                }
            } else {
                XCmdToChangeScene.execute(psm, 
                    PSMLayerManageScenario.NewLayerHoldingScene.getSingleton(), this);
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
            int code = e.getKeyCode();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            PSMBgLayer focusedLayer = (PSMBgLayer)layerMgr.getFocusedLayer();
            float increment = PSMLayerMgr.FACTOR_INCREMENT;
            switch (code) {
                case KeyEvent.VK_UP: 
                    focusedLayer.setFactor(focusedLayer.getFactor() + increment);
                    System.out.println("factor increased by " + increment);
                    break;
                case KeyEvent.VK_DOWN: 
                    focusedLayer.setFactor(focusedLayer.getFactor() - increment);
                    System.out.println("factor decreased by " + increment);
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
    
    public static class SlidingScene extends PSMScene {
        private static SlidingScene mSingleton = null;
        public static SlidingScene createSingleton(XScenario scenario) {
            assert (SlidingScene.mSingleton == null); //false: stop
            SlidingScene.mSingleton = new SlidingScene(scenario);
            return SlidingScene.mSingleton;
        }
    
        public static SlidingScene getSingleton() {
            assert (SlidingScene.mSingleton != null);
            return SlidingScene.mSingleton;
        }
        
        private SlidingScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            //update highlighted layer 
            Point pt = e.getPoint();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            PSMGuiMgr guiMgr = PSMGuiMgr.getSingleton();
            guiMgr.
            
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            PSM psm = (PSM) this.mScenario.getApp();
            XCmdToChangeScene.execute(psm, 
                PSMLayerManageScenario.LayerManageReadyScene.getSingleton(), null);
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
    
    public static class CharLayerHoldingScene extends PSMScene {
        private static CharLayerHoldingScene mSingleton = null;
        public static CharLayerHoldingScene createSingleton(XScenario scenario) {
            assert (CharLayerHoldingScene.mSingleton == null); //false: stop
            CharLayerHoldingScene.mSingleton = new CharLayerHoldingScene(scenario);
            return CharLayerHoldingScene.mSingleton;
        }
    
        public static CharLayerHoldingScene getSingleton() {
            assert (CharLayerHoldingScene.mSingleton != null);
            return CharLayerHoldingScene.mSingleton;
        }
        
        private CharLayerHoldingScene(XScenario scenario) {
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
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            Point pt = e.getPoint();
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMGuiMgr guiMgr = PSMGuiMgr.getSingleton();
            PSMLayer charLayer = layerMgr.getCharLayer();

            if (!PSMGuiMgr.getSingleton().getCharLayer().isOn(pt)) {
                XCmdToChangeScene.execute(psm, 
                    PSMLayerManageScenario.LayerManageReadyScene.getSingleton(), null);
            } else {
                //set focused layer(char layer)
                PSMLayerMgr.getSingleton().setFocusedLayer(charLayer);
                System.out.println("character layer focused");
                //다시 view로 돌려주기
                layerMgr.arrangeLayersToViewFormat(cam);
                guiMgr.arrangeUisToViewFormat(true);
                XCmdToChangeScene.execute(psm, 
                    PSMDefaultScenario.ReadyScene.getSingleton(), null);
            }
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
    
    public static class HighlightedLayerHoldingScene extends PSMScene {
        private static HighlightedLayerHoldingScene mSingleton = null;
        public static HighlightedLayerHoldingScene createSingleton(XScenario scenario) {
            assert (HighlightedLayerHoldingScene.mSingleton == null); //false: stop
            HighlightedLayerHoldingScene.mSingleton = new HighlightedLayerHoldingScene(scenario);
            return HighlightedLayerHoldingScene.mSingleton;
        }
    
        public static HighlightedLayerHoldingScene getSingleton() {
            assert (HighlightedLayerHoldingScene.mSingleton != null);
            return HighlightedLayerHoldingScene.mSingleton;
        }
        
        private HighlightedLayerHoldingScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            //update highlighted layer pos
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            PSM psm = (PSM) this.mScenario.getApp();
            Point pt = e.getPoint();
            if (!PSMGuiMgr.getSingleton().getTrashcan().isOn(pt)) {
                //update layer order
                XCmdToChangeScene.execute(psm, 
                    PSMLayerManageScenario.LayerManageReadyScene.getSingleton(), null);
            } else {
                //remove highlighted layer
                System.out.println("layer removed");
                //set highlighted layer(Next Layer)
                XCmdToChangeScene.execute(psm, 
                    PSMLayerManageScenario.LayerManageReadyScene.getSingleton(), null);
            }
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
    
    public static class NewLayerHoldingScene extends PSMScene {
        private static NewLayerHoldingScene mSingleton = null;
        public static NewLayerHoldingScene createSingleton(XScenario scenario) {
            assert (NewLayerHoldingScene.mSingleton == null); //false: stop
            NewLayerHoldingScene.mSingleton = new NewLayerHoldingScene(scenario);
            return NewLayerHoldingScene.mSingleton;
        }
    
        public static NewLayerHoldingScene getSingleton() {
            assert (NewLayerHoldingScene.mSingleton != null);
            return NewLayerHoldingScene.mSingleton;
        }
        
        private NewLayerHoldingScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            //update new layer position
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            PSM psm = (PSM) this.mScenario.getApp();
            PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
            PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
            PSMGuiMgr guiMgr = PSMGuiMgr.getSingleton();
            //check new layer position 
            //추가 영역 안일 시 add new layer 후 pre layer manage scene으로 
            //아닐 시 reset new layer pos 후 pre layer manage scene으로 
            if (!(PSMGuiMgr.getSingleton().getNewLayer().getY() > 600 && true)) {
                //add new layer
                System.out.println("new layer added");
                XCmdToChangeScene.execute(psm, 
                    PSMLayerManageScenario.LayerManageReadyScene.getSingleton(), null);
            } else {
                //reset new layer pos
                System.out.println("layer pos reset");
                XCmdToChangeScene.execute(psm, 
                    PSMLayerManageScenario.LayerManageReadyScene.getSingleton(), null);
            }
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
