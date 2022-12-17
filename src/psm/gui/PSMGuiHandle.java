package psm.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import psm.PSM;

public class PSMGuiHandle extends PSMFollowingGui{
    public PSMGuiHandle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    protected void renderUi(Graphics2D g, int x, int y, int width, int height) {
//        Rectangle frame = new Rectangle(0, y, x + width, PSM.CANVAS_HEIGHT - y);
//        Rectangle rect = new Rectangle(x, y, width, height);
//        g.setColor(Color.gray);
//        g.fill(rect);
        int xpoints[] = {x, x, PSM.CANVAS_WIDTH};
        int ypoints[] = {0, y+ height, y+ height};
        int npoints = 3;
        g.setColor(Color.gray);
        g.fillPolygon(xpoints, ypoints, npoints);
        //g.draw(frame);
    }
}