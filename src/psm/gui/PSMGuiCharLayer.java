package psm.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PSMGuiCharLayer extends PSMGui{
    public PSMGuiCharLayer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    protected void renderUi(Graphics2D g, int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        g.setColor(Color.red);
        g.fill(rect);
    }
}
