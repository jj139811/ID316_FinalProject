package psm.gui;

import java.awt.Graphics2D;

public class PSMGuiHighlightedLayer extends PSMGui{
    public PSMGuiHighlightedLayer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    protected void renderUi(Graphics2D g, int x, int y, int width, int height) {
        // draw nothing
    }
}
