package psm;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PSMLayerMgr {
    //constant
    public static final int PANEL_WIDTH = 400;
    public static final int PANEL_HEIGHT = 300;
    
    public static final int CHAR_WIDTH = 100;
    public static final int CHAR_HEIGHT = 150;
    //singleton
    private static PSMLayerMgr instance = null;
    public static PSMLayerMgr getSingleton() {
        if (instance == null) {
            instance = new PSMLayerMgr();
        }
        return instance;
    }
    
    //field
    private ArrayList<PSMLayer> mLayers = null;
    private PSMLayer mFocusedLayer;
    public PSMLayer getFocusedLayer() {
        return this.mFocusedLayer;
    }
    public void setFocusedLayer(PSMLayer layer) {
        assert(this.mLayers.contains(layer) || layer == this.mCharLayer);
        this.mFocusedLayer = layer;
        this.mFocusedIndex = this.mLayers.indexOf(layer);
    }
    
    private int mFocusedIndex;
    public int getFocusedIndex() {
        return this.mFocusedIndex;
    }
    public void setFocusedIndex(int index) {
        int i = index;
        if (i < 0) {
            this.mFocusedLayer = this.mCharLayer;
            this.mFocusedIndex = -1;
            return;
        }
        if (i >= this.mLayers.size()) {
            i = this.mLayers.size() - 1;
        }
        this.mFocusedLayer = this.mLayers.get(i);
        this.mFocusedIndex = i;
    }
    
    private PSMCharLayer mCharLayer = null;
    public PSMCharLayer getCharLayer() {
        return this.mCharLayer;
    }
    
    //constructor
    private PSMLayerMgr() {
        this.mLayers = new ArrayList<>();
        this.mCharLayer = new PSMCharLayer();
    }
    
    //method
    public void syncLayerPositionsWithCamera(PSMCamera camera) {
        for (PSMLayer layer : this.mLayers) {
            layer.syncPosition(camera, false);
        }
    }
    
    public void addLayer(PSMLayer target) {
        this.mLayers.add(target);
    }
    public void addLayerToIndex(PSMLayer target, int index) {
        this.mLayers.add(index, target);
    }
    public void removeLayer(PSMLayer target) {
        this.mLayers.remove(target);
    }
    
    public void arrangeLayersToListFormat(PSMCamera camera) {
        PSMScreenMgr screenMgr = PSMScreenMgr.getSingleton();
        float scaleX = camera.getScaleX();
        float scaleY = camera.getScaleY();
        
        int i = 0;
        for (; i < this.mFocusedIndex && i < this.mLayers.size(); i++) {
            PSMLayer layer = this.mLayers.get(i);
            Point2D.Float pt = screenMgr.screenPtToWorldPt(new Point(
                -PSM.CANVAS_WIDTH / 2 - PANEL_WIDTH / 2,
                PSM.CANVAS_HEIGHT / 2));
            layer.setPosition(pt.x, pt.y, true);
            layer.setSize(
                (int)(PANEL_WIDTH / scaleX),
                (int)(PANEL_HEIGHT / scaleY), true);
        }
        for (; i < this.mLayers.size(); i++) {
            int offset = (i - this.mFocusedIndex) * 50;
            PSMLayer layer = this.mLayers.get(i);
            Point2D.Float pt = screenMgr.screenPtToWorldPt(
                new Point(
                    PSM.CANVAS_WIDTH / 2 + offset,
                    PSM.CANVAS_HEIGHT / 2 - offset));
            layer.setPosition(pt.x, pt.y, true);
            layer.setSize(
                (int)(PANEL_WIDTH / scaleX),
                (int)(PANEL_HEIGHT / scaleY), true);
        }
        
        if (this.mFocusedLayer == this.mCharLayer) {
            Point2D.Float charLayerPt = screenMgr.screenPtToWorldPt(
                new Point(PSM.CANVAS_WIDTH / 2, PSM.CANVAS_HEIGHT / 2));
            this.mCharLayer.setPosition(charLayerPt.x, charLayerPt.y, true);
            this.mCharLayer.setSize(
                (int)(CHAR_WIDTH / scaleX),
                (int)(CHAR_HEIGHT / scaleY), true);
        } else {
            Point2D.Float charLayerPt = screenMgr.screenPtToWorldPt(
                new Point(CHAR_WIDTH / 2, PSM.CANVAS_HEIGHT - CHAR_HEIGHT / 2));
            this.mCharLayer.setPosition(charLayerPt.x, charLayerPt.y, true);
            this.mCharLayer.setSize(
                (int)(CHAR_WIDTH / scaleX),
                (int)(CHAR_HEIGHT / scaleY), true);
        }
    }
    public void arrangeLayersToViewFormat(PSMCamera camera) {
        for (PSMLayer layer : this.mLayers) {
            layer.syncPosition(camera, true);
            layer.setSize(layer.getImgWidth(), layer.getImgHeight(), true);
        }
        this.mCharLayer.syncPosition(camera, true);
        this.mCharLayer.setSize(CHAR_WIDTH, CHAR_HEIGHT, true);
    }
    
    public void drawLayers(Graphics2D g) {
        for (PSMLayer layer : this.mLayers) {
            layer.render(g);
        }
        this.mCharLayer.render(g);
    }
}
