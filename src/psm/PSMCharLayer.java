package psm;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class PSMCharLayer extends PSMLayer{
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
}
