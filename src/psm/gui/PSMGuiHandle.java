package psm.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PSMGuiHandle extends PSMGui{
    public PSMGuiHandle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    protected void renderUi(Graphics2D g, int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        g.setColor(Color.gray);
        g.fill(rect);
    }
}