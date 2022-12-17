package psm.gui;

import X.XApp;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import psm.PSM;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class PSMColorChooser {
    //singleton
//    private static PSMColorChooser mSingleton = null;
//    
//    public static PSMColorChooser createSingleton(int x, int y, int width, int height) {
//        assert (PSMColorChooser.mSingleton == null);
//        PSMColorChooser.mSingleton = new PSMColorChooser(x, y, width, height);
//        return PSMColorChooser.mSingleton;
//    }
//    
//    public static PSMColorChooser getSingleton() {
//        assert (PSMColorChooser.mSingleton != null);
//        return PSMColorChooser.mSingleton;
//    }
    //constant
    private static final int CELL_NUM_H = 40;
    private static final int CELL_NUM_B = 10;
    private static final float SATURATION_DEFAULT = 1f;
    private static final float OPAQUENESS_DEFAULT = 1f;
    
    //variable
    private Color[][] mColors = null;
    private float mSaturation = Float.NaN;
    private float mOpaqueness = Float.NaN;
    
    //constructor
    public PSMColorChooser() {
        this.mColors = new Color[PSMColorChooser.CELL_NUM_B][];
        for (int i = 0 ; i < PSMColorChooser.CELL_NUM_B ; i++) {
            this.mColors[i] = new Color[PSMColorChooser.CELL_NUM_H];
        }
        this.mSaturation = PSMColorChooser.SATURATION_DEFAULT;
        this.mOpaqueness = PSMColorChooser.OPAQUENESS_DEFAULT;
        
        this.calcCellColors();
    }

    private void calcCellColors() {
        float db = 1f / (float)(PSMColorChooser.CELL_NUM_B - 1);
        float dh = 1f / (float)(PSMColorChooser.CELL_NUM_H - 1);
        
        for (int i = 0 ; i < PSMColorChooser.CELL_NUM_B ; i++) {
            float b = db * (float)i;
            for (int j = 0 ; j < PSMColorChooser.CELL_NUM_H ; j++){
                float h = dh * (float)j;
                Color hsb = Color.getHSBColor(h, SATURATION_DEFAULT, b);
                this.mColors[i][j] = new Color(hsb.getRed(), hsb.getGreen(), 
                    hsb.getBlue(), (int)(this.mOpaqueness * 255f));
                
            }
        }
    }
    
    
    public Color calcColor(Point pt, int w, int h) {
        double ys = (double)h / 3.0 * 1.0;
        double ye = (double)h / 3.0 * 2.0;
        double dx = (double)w / (double)PSMColorChooser.CELL_NUM_H;
        double dy = (ye - ys) / (double)PSMColorChooser.CELL_NUM_B;
        
        int i = (int) (((double)pt.y - ys) / dy);
        int j = (int) ((double)pt.x / dx);
        if (i < 0 || i >= PSMColorChooser.CELL_NUM_B) {
            return null;
        } else {
            return this.mColors[i][j];
        }
    }

    public void drawCells(Graphics2D g2, int w, int h) {
        double ys = (double)h / 3.0 * 1.0;
        double ye = (double)h / 3.0 * 2.0;
        double dx = (double)w / (double)PSMColorChooser.CELL_NUM_H;
        double dy = (ye - ys) / (double)PSMColorChooser.CELL_NUM_B;
        for (int i = 0 ; i < PSMColorChooser.CELL_NUM_B ; i++) {
            double y = ys + dy * (double)i;
            for (int j = 0 ; j < PSMColorChooser.CELL_NUM_H ; j++) {
                double x = dx * (double)j;
                Rectangle2D rect = new Rectangle2D.Double(x, y, dx, dy);
                g2.setColor(this.mColors[i][j]);
                g2.fill(rect);
            }
        }
    }
    
}
