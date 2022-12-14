package psm.animation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import psm.PSMCamera;
import psm.PSMScreenMgr;

public abstract class PSMAnimatableObject extends PSMAnimatable{
    //test
    private static final boolean DRAW_BOX = false;
    //constant
    private static final long DEFAULT_DURATION = 500;
    //field
    private long mDuration = PSMAnimatableObject.DEFAULT_DURATION;
    public void setDuration(long duration) {
        assert(duration != 0);
        this.mDuration = duration;
    }
    
    private long mTimeElapsed = 0;
    private boolean mIsMoving = false;
    private float mStartX, mStartY, mStartW, mStartH;
    
    private float x, y, w, h;
    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }
    public float getWidth() {
        return this.w;
    }
    public float getHeight() {
        return this.h;
    }
    public void setPosition(float x, float y, boolean enableAnimation) {
        this.x = x;
        this.y = y;
        if (enableAnimation) {
            this.restart();
        } else {
            this.curX = x;
            this.curY = y;
        }
    }
    public void setSize(float width, float height, boolean enableAnimation) {
        this.w = width;
        this.h = height;
        if (enableAnimation) {
            this.restart();
        } else {
            this.curW = width;
            this.curH = height;
        }
    }
    
    private float curX, curY, curW, curH;
    public float getCurX() {
        return this.curX;
    }
    public float getCurY() {
        return this.curY;
    }
    public float getCurWidth() {
        return this.curW;
    }
    public float getCurHeight() {
        return this.curH;
    }
    
    //constructor
    public PSMAnimatableObject(float x, float y, float width, float height) {
        super();
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        
        this.curX = x;
        this.curY = y;
        this.curW = width;
        this.curH = height;
        
        this.mStartX = x;
        this.mStartY = y;
        this.mStartW = width;
        this.mStartH = height;
        
        this.mTimeElapsed = 0;
        this.mIsMoving = false;
    }
    
    //method
    private void restart() {
        this.mStartX = this.curX;
        this.mStartY = this.curY;
        this.mStartW = this.curW;
        this.mStartH = this.curH;
        
        this.mTimeElapsed = 0;
        
        this.mIsMoving = true;
    }
    @Override
    public void update(long t) {
        if(!this.mIsMoving){
            return;
        }
        this.mTimeElapsed += t;
        if(this.mTimeElapsed >= this.mDuration) {
            this.curX = this.x;
            this.curY = this.y;
            this.curW = this.w;
            this.curH = this.h;
            this.mIsMoving = false;
            return;
        }
        float f = ((float)this.mTimeElapsed) / ((float)this.mDuration);
        this.curX = f * this.x + (1 - f) * this.mStartX;
        this.curY = f * this.y + (1 - f) * this.mStartY;
        this.curW = f * this.w + (1 - f) * this.mStartW;
        this.curH = f * this.h + (1 - f) * this.mStartH;
    }
    public final void render(Graphics2D g) {
        PSMScreenMgr screenMgr = PSMScreenMgr.getSingleton();
        PSMCamera camera = screenMgr.getCamera();
        float scaleX = camera.getScaleX();
        float scaleY = camera.getScaleY();
        
        Point screenPt = screenMgr.worldPtToScreenPt(new Point2D.Float(
            this.curX, this.curY));
        int width = (int)(this.curW * scaleX);
        int height = (int)(this.curH * scaleY);
        
        this.renderObject(g,
            screenPt.x - width / 2,
            screenPt.y - height / 2,
            width,
            height);
        
        if(PSMAnimatableObject.DRAW_BOX) {
            Rectangle2D boundingBox = new Rectangle2D.Float(
                screenPt.x - width / 2,
                screenPt.y - height / 2,
                width,
                height);
            g.setColor(Color.red);
            g.setStroke(new BasicStroke(0.5f));
            g.draw(boundingBox);
        }
    }

    //abstract method
    protected abstract void renderObject(Graphics2D g,
        int x, int y, int width, int height);
}
