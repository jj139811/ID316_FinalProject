package psm;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import psm.animation.PSMAnimatableObject;

public abstract class PSMLayer extends PSMAnimatableObject{
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
    public abstract void setFactor(float factor);
    protected final void clipFactor(float factor) {
        float value = factor;
        if (value > MAX_FACTOR) {
            value = MAX_FACTOR;
        } else if (value < MIN_FACTOR) {
            value = MIN_FACTOR;
        }
        this.mFactor = value;
    }
    
    //constructor
    public PSMLayer(float x, float y, float width, float height, float factor) {
        super(x, y, width, height);
        this.clipFactor(factor);
        this.initImage();
    }
    
    //method
    protected void initImage() {
        BufferedImage newImage = this.createImage();
        Graphics2D g = newImage.createGraphics();
        int width = newImage.getWidth();
        int height = newImage.getHeight();
        
        if (this.mContent != null) {
            g.drawImage(this.mContent,
                (int)((width - this.mImgWidth) / 2),
                (int)((height - this.mImgHeight) / 2), null);
        }
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
        this.mContent = newImage;
        this.mGraphics = g;
        this.mImgWidth = width;
        this.mImgHeight = height;
    }
    protected abstract BufferedImage createImage();
    
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
