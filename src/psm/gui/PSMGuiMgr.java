package psm.gui;

import java.awt.Graphics2D;
import java.util.ArrayList;
import psm.PSM;
import psm.PSMLayer;
import psm.PSMLayerMgr;

public class PSMGuiMgr {
    // singleton
    private static PSMGuiMgr instance;
    public static PSMGuiMgr getSingleton() {
        if (instance == null) {
            instance = new PSMGuiMgr();
        }
        return instance;
    }
    
    //constant
    private static final int HANDLE_WIDTH = 50;
    private static final int HANDLE_HEIGHT = 50;
    
    //field
    private ArrayList<PSMGui> mGuis = null;
    public final void add(PSMGui ui) {
        this.mGuis.add(ui);
    }
    public final void remove(PSMGui ui) {
        this.mGuis.remove(ui);
    }
    
    //uis
    private PSMGuiTrashcan mTrashcan = null;
    public PSMGuiTrashcan getTrashcan() {
        return this.mTrashcan;
    }
    private PSMGuiHandle mHandle = null;
    public PSMGuiHandle getHandle() {
        return this.mHandle;
    }
    private PSMGuiNewLayer mNewLayer = null;
    public PSMGuiNewLayer getNewLayer() {
        return this.mNewLayer;
    }
    private PSMGuiCharLayer mCharLayer = null;
    public PSMGuiCharLayer getCharLayer() {
        return this.mCharLayer;
    }
    private PSMGuiHighlightedLayer mHighlightedLayer = null;
    public PSMGuiHighlightedLayer getHighlightedLayer() {
        return this.mHighlightedLayer;
    }
    
    //constructor
    private PSMGuiMgr() {
        this.mGuis = new ArrayList<>();
        this.mTrashcan = new PSMGuiTrashcan(
            (PSM.CANVAS_WIDTH - PSMLayerMgr.PANEL_WIDTH) / 2,
            PSM.CANVAS_HEIGHT,
            PSMLayerMgr.PANEL_WIDTH,
            PSMLayerMgr.PANEL_HEIGHT);
        this.mHandle = new PSMGuiHandle(
            PSM.CANVAS_WIDTH - HANDLE_WIDTH, 0,
            HANDLE_WIDTH, HANDLE_HEIGHT);
        this.mNewLayer = new PSMGuiNewLayer(
            (PSM.CANVAS_WIDTH - PSMLayerMgr.PANEL_WIDTH) / 2,
            PSM.CANVAS_HEIGHT,
            PSMLayerMgr.PANEL_WIDTH,
            PSMLayerMgr.PANEL_HEIGHT);
        this.mCharLayer = new PSMGuiCharLayer(
            0, PSM.CANVAS_HEIGHT,
            PSMLayerMgr.PANEL_WIDTH, PSMLayerMgr.PANEL_HEIGHT);
        this.mHighlightedLayer = new PSMGuiHighlightedLayer(
            (PSM.CANVAS_WIDTH - PSMLayerMgr.PANEL_WIDTH) / 2,
            (PSM.CANVAS_HEIGHT - PSMLayerMgr.PANEL_HEIGHT) / 2,
            PSMLayerMgr.PANEL_WIDTH,
            PSMLayerMgr.PANEL_HEIGHT);
        
        //add uis
        // in rendering order
        this.add(this.mHighlightedLayer);
        this.add(this.mTrashcan);
        this.add(this.mNewLayer);
        this.add(this.mCharLayer);
        this.add(this.mHandle);
        //init gui transform
        arrangeUisToViewFormat(false);
    }
    public final void arrangeUisToViewFormat(boolean enableAnimation) {
        this.mTrashcan.setPosition(PSM.CANVAS_WIDTH - HANDLE_WIDTH, 0,
            enableAnimation);
        this.mTrashcan.setSize(HANDLE_WIDTH, HANDLE_HEIGHT,
            enableAnimation);
        this.mTrashcan.setVisible(true);
        
        this.mHandle.setPosition(PSM.CANVAS_WIDTH - HANDLE_WIDTH, 0,
            enableAnimation);
        this.mHandle.setSize(HANDLE_WIDTH, HANDLE_HEIGHT,
            enableAnimation);
        this.mHandle.setVisible(true);
        
        this.mNewLayer.setPosition(
            (PSM.CANVAS_WIDTH - PSMLayerMgr.PANEL_WIDTH) / 2,
            PSM.CANVAS_HEIGHT,
            enableAnimation);
        this.mNewLayer.setSize(
            PSMLayerMgr.PANEL_WIDTH,
            PSMLayerMgr.PANEL_HEIGHT,
            enableAnimation);
        this.mNewLayer.setVisible(true);
        
        this.mCharLayer.setPosition(-PSMLayerMgr.CHAR_WIDTH,
            PSM.CANVAS_HEIGHT, enableAnimation);
        this.mCharLayer.setSize(PSMLayerMgr.CHAR_WIDTH,
            PSMLayerMgr.CHAR_HEIGHT,
            enableAnimation);
        this.mCharLayer.setVisible(true);
        
        this.mHighlightedLayer.setVisible(false);
    }
    public void arrangeUisToListFormat(boolean enableAnimation) {
        this.mTrashcan.setPosition(-HANDLE_WIDTH, PSM.CANVAS_HEIGHT,
            enableAnimation);
        this.mTrashcan.setSize(HANDLE_WIDTH, HANDLE_HEIGHT,
            enableAnimation);
        this.mTrashcan.setVisible(true);
        
        this.mHandle.setPosition(-HANDLE_WIDTH, PSM.CANVAS_HEIGHT,
            enableAnimation);
        this.mHandle.setSize(HANDLE_WIDTH, HANDLE_HEIGHT,
            enableAnimation);
        this.mHandle.setVisible(true);
        
        this.mNewLayer.setPosition(
            (PSM.CANVAS_WIDTH - PSMLayerMgr.PANEL_WIDTH) / 2,
            PSM.CANVAS_HEIGHT - PSMLayerMgr.PANEL_HEIGHT / 3,
            enableAnimation);
        this.mNewLayer.setSize(
            PSMLayerMgr.PANEL_WIDTH,
            PSMLayerMgr.PANEL_HEIGHT,
            enableAnimation);
        this.mNewLayer.setVisible(true);
        
        this.mCharLayer.setPosition(
            0,
            PSM.CANVAS_HEIGHT - PSMLayerMgr.CHAR_HEIGHT, enableAnimation);
        this.mCharLayer.setSize(PSMLayerMgr.CHAR_WIDTH,
            PSMLayerMgr.CHAR_HEIGHT,
            enableAnimation);
        this.mCharLayer.setVisible(true);
        
        this.mHighlightedLayer.setVisible(true);
    }
    //method
    public void renderGuis(Graphics2D g) {
        for (PSMGui ui : this.mGuis) {
            ui.render(g);
        }
    }
}
