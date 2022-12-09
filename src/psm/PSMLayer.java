package psm;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import psm.animation.PSMAnimatableObject;

public class PSMLayer extends PSMAnimatableObject{
    //constant
    public static final float MAX_FACTOR = 1.0f;
    public static final float MIN_FACTOR = -2.0f;
    //field
    private BufferedImage mContent = null;
    private Graphics2D mGraphics = null;
    public Graphics2D getGraphics() {
        return this.mGraphics;
    }
    private int mImgWidth, mImgHeight;
    public int getImgWidth() {
        return this.mImgWidth;
    }
    public int getImgHeight() {
        return this.mImgHeight;
    }
    
    private float mFactor = -9999.0f;
    public float getFactor() {
        return this.mFactor;
    }
    public final void setFactor(float factor) {
        float value = factor;
        if (value > MAX_FACTOR) {
            value = MAX_FACTOR;
        } else if (value < MIN_FACTOR) {
            value = MIN_FACTOR;
        }
        if (this.mFactor != value) {
            this.mFactor = value;
            this.initImage(value);
        }
    }
    
    //constructor
    public PSMLayer(float factor) {
        super(0, 0,
            (int)(PSM.CANVAS_WIDTH + (1.0f - factor) * PSM.CAMERA_BOUND_X),
            (int)(PSM.CANVAS_HEIGHT + (1.0f - factor) * PSM.CAMERA_BOUND_Y));
        this.setFactor(factor);
    }
    
    //method
    protected void initImage(float factor) {
        int width =
            (int)(PSM.CANVAS_WIDTH + (1.0f - factor) * PSM.CAMERA_BOUND_X);
        int height = 
            (int)(PSM.CANVAS_HEIGHT + (1.0f - factor) * PSM.CAMERA_BOUND_Y);
        
        BufferedImage img = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        
        if (this.mContent != null) {
            g.drawImage(this.mContent,
                (int)((width - this.mImgWidth) / 2),
                (int)((height - this.mImgHeight) / 2), null);
        }
        
        this.mContent = img;
        this.mGraphics = g;
        this.mImgWidth = width;
        this.mImgHeight = height;
    }
    
    public void syncPosition(PSMCamera camera, boolean enableAnimation) {
        
        this.setPosition(this.mFactor * camera.getX(),
            this.mFactor * camera.getY(), enableAnimation);
    }
    
    public Point2D.Float worldPtToLayerLocalPt(Point2D.Float worldPt) {
        return new Point2D.Float(
            worldPt.x - this.getX() + this.mImgWidth / 2,
            worldPt.y - this.getY() + this.mImgHeight / 2);
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
