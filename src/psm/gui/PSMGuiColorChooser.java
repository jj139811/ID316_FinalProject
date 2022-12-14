package psm.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import psm.PSM;
import java.awt.*;
import javax.swing.*;

public class PSMGuiColorChooser extends PSMGui{
    public PSMGuiColorChooser(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    @Override
    protected void renderUi(Graphics2D g, int x, int y, int width, int height) {
        int w = width;
        int h = height;
        int radius = Math.min(w, h) / 2;
        int cx = x + width / 2;
        int cy = y + height / 2;
        float[] dist = {0.7F, 1.0F};
        for (int angle = 0; angle < 360; ++angle) {
            Color color = hsvToRgb(angle, 1.0, 1.0);
            Color[] colors = {color, color};
            g.setPaint(color);
            BasicStroke bs = new BasicStroke(5f);
            g.setStroke(bs);
            g.drawArc(cx - radius, cy - radius, radius*2, radius*2,
                angle, 1);

        }
    }
    
    private static Color hsvToRgb(int h, double s, double v) {
        double hp = h/60.0;
        double c = s * v;
        double x = c * (1 - Math.abs(hp % 2.0 - 1));
        double m = v - c;
        double r = 0, g = 0, b = 0;
        if (hp <= 1) {
            r = c;
            g = x;
        } else if (hp <= 2) {
            r = x;
            g = c;
        } else if (hp <= 3) {
            g = c;
            b = x;
        } else if (hp <= 4) {
            g = x;
            b = c;
        } else if (hp <= 5) {
            r = x;
            b = c;
        } else {
            r = c;
            b = x;
        }
        r += m;
        g += m;
        b += m;
        return new Color((int)(r * 255), (int)(g * 255), (int)(b * 255));
    }
}