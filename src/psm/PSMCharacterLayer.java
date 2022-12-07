package psm;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import psm.animation.PSMAnimatableObject;

public class PSMCharacterLayer extends PSMAnimatableObject{
    //constant
    public static final int CHAR_LAYER_WIDTH = 100;
    public static final int CHAR_LAYER_HEIGHT = 150;
    //field
    private BufferedImage mContent = null;
    private Graphics2D mGraphics = null;
    public Graphics2D getGraphics() {
        return this.mGraphics;
    }
    
    //constructor
    public PSMCharacterLayer(float factor) {
        super(0, 0, CHAR_LAYER_WIDTH, CHAR_LAYER_HEIGHT);
    }
    
    //method
    protected void initImage(float factor) {
        BufferedImage img = new BufferedImage(CHAR_LAYER_WIDTH,
            CHAR_LAYER_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        
        if (this.mContent != null) {
            g.drawImage(this.mContent, 0, 0, null);
        }
        
        this.mContent = img;
        this.mGraphics = g;
    }
    
    public Point2D.Float worldPtToLayerLocalPt(Point2D.Float worldPt) {
        return new Point2D.Float(
            worldPt.x - this.getX(),
            worldPt.y - this.getY());
    }
    
    @Override
    protected void renderObject(Graphics2D g,
        int x, int y, int width, int height) {
        if (this.mContent == null) {
            return;
        }
        g.drawImage(this.mContent, x, y, width, height, null);
    }
}
