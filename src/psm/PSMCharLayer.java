package psm;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import psm.scenario.PSMSimulateScenario;

public class PSMCharLayer extends PSMLayer{
    //constant
    private static final float SIMULATE_SPEED = 0.1f;
    @Override
    public void setFactor(float factor) {
        super.clipFactor(factor);
    }
    
    public PSMCharLayer() {
        super(0, 0, PSMLayerMgr.CHAR_WIDTH, PSMLayerMgr.CHAR_HEIGHT, 0.0f);
    }
    
    @Override
    protected BufferedImage createImage() {
        BufferedImage img = new BufferedImage(
            PSMLayerMgr.CHAR_WIDTH,
            PSMLayerMgr.CHAR_HEIGHT,
            BufferedImage.TYPE_INT_ARGB);
        
        return img;
    }
    
    public boolean isOnBoundingBox(Point pt) {
        Point2D.Float worldPt = PSMScreenMgr.getSingleton().
            screenPtToWorldPt(pt);
        float x = this.getX();
        float y = this.getY();
        float w = this.getWidth();
        float h = this.getHeight();
        
        if (worldPt.x < x - w / 2 ||
            worldPt.x > x + w / 2 ||
            worldPt.y < y - h / 2 ||
            worldPt.y > y + h / 2) {
            
            return false;
        } 
        return true;
    }
    @Override
    public final void update(long t) {
        super.update(t);
        PSMScene curScene = (PSMScene)PSM.getSingleton().getScenarioMgr().
            getCurScene();
        PSMCamera cam = PSMScreenMgr.getSingleton().getCamera();
        if (curScene == PSMSimulateScenario.CharacterMoveScene.getSingleton()) {
            PSMGestureMgr gestureMgr = PSMGestureMgr.getSingleton();
            Point startingPt = gestureMgr.getStartingPt();
            Point currentPt = gestureMgr.getCurrentPt();
            assert (startingPt != null && currentPt != null);
            float dx = ((float)(currentPt.x - startingPt.x));
            float dy = ((float)(currentPt.y - startingPt.y));
            float x = this.getX();
            float y = this.getY();
            
            float newX = x + dx * SIMULATE_SPEED;
            float newY = y + dy * SIMULATE_SPEED;
            
            float boundX = PSM.CAMERA_BOUND_X +
                PSM.CANVAS_WIDTH - PSMLayerMgr.CHAR_WIDTH;
            float boundY = PSM.CAMERA_BOUND_Y +
                PSM.CANVAS_HEIGHT - PSMLayerMgr.CHAR_HEIGHT;
            
            if (newX < -boundX / 2) {
                newX = -boundX / 2;
            }
            if (newX > boundX / 2) {
                newX = boundX / 2;
            }
            if (newY < -boundY / 2) {
                newY = -boundY / 2;
            }
            if (newY > boundY / 2) {
                newY = boundY / 2;
            }
            
            this.setPosition(newX, newY, false);
        } 
        
    }
}
