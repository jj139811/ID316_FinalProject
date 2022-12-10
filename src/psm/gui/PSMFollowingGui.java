package psm.gui;

import java.awt.Point;

public abstract class PSMFollowingGui extends PSMGui{
    public PSMFollowingGui(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public final void followCursor(Point pt) {
        this.setPosition(
            pt.x - this.getWidth() / 2,
            pt.y - this.getHeight() / 2, false);
    }
}
