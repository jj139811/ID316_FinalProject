package psm;

import java.awt.image.BufferedImage;

public class PSMBgLayer extends PSMLayer{
    @Override
    public void setFactor(float factor) {
        float prevFactor = this.getFactor();
        super.clipFactor(factor);
        if (prevFactor != this.getFactor()) {
            this.initImage();
        }
    }
    
    public PSMBgLayer(float factor) {
        super(0, 0,
            (int)(PSM.CANVAS_WIDTH + (1.0f - factor) * PSM.CAMERA_BOUND_X),
            (int)(PSM.CANVAS_HEIGHT + (1.0f - factor) * PSM.CAMERA_BOUND_Y),
            factor);
    }
    
    @Override
    protected BufferedImage createImage() {
        float factor = this.getFactor();
        int width =
            (int)(PSM.CANVAS_WIDTH + (1.0f - factor) * PSM.CAMERA_BOUND_X);
        int height = 
            (int)(PSM.CANVAS_HEIGHT + (1.0f - factor) * PSM.CAMERA_BOUND_Y);
        
        BufferedImage img = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_ARGB);
        
        return img;
    }
}
