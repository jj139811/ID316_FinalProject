package psm;

import java.awt.Graphics2D;

public class PSMCamera {
    //field
    private Graphics2D mGraphics;
    public Graphics2D getGraphics() {
        return this.mGraphics;
    }
    
    private float x, y, scaleX, scaleY;
    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }
    public float getScaleX() {
        return this.scaleX;
    }
    public float getScaleY() {
        return this.scaleY;
    }
    public void setPosition(float x, float y) {
        float prevX = this.x;
        float prevY = this.y;
        this.x = x;
        this.y = y;
        if (this.x > PSM.CAMERA_BOUND_X / 2) {
            this.x = PSM.CAMERA_BOUND_X / 2;
        }
        if (this.y > PSM.CAMERA_BOUND_Y / 2) {
            this.y = PSM.CAMERA_BOUND_Y / 2;
        }
        if (this.x < -PSM.CAMERA_BOUND_X / 2) {
            this.x = -PSM.CAMERA_BOUND_X / 2;
        }
        if (this.y < -PSM.CAMERA_BOUND_Y / 2) {
            this.y = -PSM.CAMERA_BOUND_Y / 2;
        }
        if (prevX != this.x || prevY != this.y) {
            PSMLayerMgr.getSingleton().syncLayerPositionsWithCamera(this);
        }
    }
    public void setScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    
    private float stackX, stackY, stackScaleX, stackScaleY;
    
    //constructor
    public PSMCamera() {
        this.x = 0;
        this.y = 0;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
    }
    //method
    public void push() {
        this.stackX = this.x;
        this.stackY = this.y;
        this.stackScaleX = this.scaleX;
        this.stackScaleY = this.scaleY;
    }
    public void pop() {
        this.x = this.stackX;
        this.y = this.stackY;
        this.scaleX = this.stackScaleX;
        this.scaleY = this.stackScaleY;
    }
    public void move(int dx, int dy) {
        this.setPosition(this.x + dx, this.y + dy);
    }
}
