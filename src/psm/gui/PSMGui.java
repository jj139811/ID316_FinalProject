package psm.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import psm.animation.PSMAnimatable;

public abstract class PSMGui extends PSMAnimatable{
    //test
    private static final boolean DRAW_BOX = true;
    //constant
    private static final long DEFAULT_DURATION = 500;
    //field
    private long mDuration = DEFAULT_DURATION;
    public void setDuration(long duration) {
        assert(duration != 0);
        this.mDuration = duration;
    }
    
    private boolean isVisible = true;
    public void setVisible(boolean value) {
        this.isVisible = value;
    }
    
    private long mTimeElapsed = 0;
    private boolean mIsMoving = false;
    private float mStartX, mStartY, mStartW, mStartH;
    
    private int x, y, width, height;
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public void setPosition(int x, int y, boolean enableAnimation) {
        this.x = x;
        this.y = y;
        if (enableAnimation) {
            this.restart();
        } else {
            this.curX = x;
            this.curY = y;
        }
    }
    public void setSize(int width, int height, boolean enableAnimation) {
        this.width = width;
        this.height = height;
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
    public PSMGui(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.curX = (float)x;
        this.curY = (float)y;
        this.curW = (float)width;
        this.curH = (float)height;
        
        this.mStartX = (float)x;
        this.mStartY = (float)y;
        this.mStartW = (float)width;
        this.mStartH = (float)height;
        
        this.mTimeElapsed = 0;
        this.mIsMoving = false;
    }
    
    //method
    public boolean isOn(Point pt) {
        if (!this.isVisible) {
            return false;
        }
        
        if (pt.x < this.x) {
            return false;
        }
        if (pt.x > this.x + this.width) {
            return false;
        }
        if (pt.y < this.y) {
            return false;
        }
        if (pt.y > this.y + this.height) {
            return false;
        }
        return true;
    }
    
    private void restart() {
        this.mStartX = this.curX;
        this.mStartY = this.curY;
        this.mStartW = this.curW;
        this.mStartH = this.curH;
        
        this.mTimeElapsed = 0;
        
        this.mIsMoving = true;
    }
    @Override
    public final void update(long t) {
        if(!this.mIsMoving){
            return;
        }
        this.mTimeElapsed += t;
        if(this.mTimeElapsed >= this.mDuration) {
            this.curX = (float)this.x;
            this.curY = (float)this.y;
            this.curW = (float)this.width;
            this.curH = (float)this.height;
            this.mIsMoving = false;
            return;
        }
        float f = ((float)this.mTimeElapsed) / ((float)this.mDuration);
        this.curX = f * (float)this.x + (1 - f) * this.mStartX;
        this.curY = f * (float)this.y + (1 - f) * this.mStartY;
        this.curW = f * (float)this.width + (1 - f) * this.mStartW;
        this.curH = f * (float)this.height + (1 - f) * this.mStartH;
    }
    public final void render(Graphics2D g) {
        if (this.isVisible) {
            this.renderUi(g, (int)this.curX, (int)this.curY,
                (int)this.curW, (int)this.curH);
        
            if(DRAW_BOX) {
                Rectangle2D boundingBox = new Rectangle2D.Float(
                    (int)this.curX, (int)this.curY,
                    (int)this.curW, (int)this.curH);
                g.setColor(Color.blue);
                g.setStroke(new BasicStroke(2f));
                g.draw(boundingBox);
            }
        }
    }

    //abstract method
    protected abstract void renderUi(Graphics2D g,
        int x, int y, int width, int height);
}