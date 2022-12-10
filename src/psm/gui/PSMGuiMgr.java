package psm.gui;

import java.awt.Graphics2D;
import java.util.ArrayList;
import psm.PSM;
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
    public void add(PSMGui ui) {
        this.mGuis.add(ui);
    }
    public void remove(PSMGui ui) {
        this.mGuis.remove(ui);
    }
    
    //uis
    private PSMGuiHandle mHandle = null;
    private PSMGuiNewLayer mNewLayer = null;
    
    //constructor
    private PSMGuiMgr() {
        this.mGuis = new ArrayList<>();
        this.mHandle = new PSMGuiHandle(
            PSM.CANVAS_WIDTH - HANDLE_WIDTH, 0,
            HANDLE_WIDTH, HANDLE_HEIGHT);
        this.mNewLayer = new PSMGuiNewLayer(
            (PSM.CANVAS_WIDTH - PSMLayerMgr.PANEL_WIDTH) / 2,
            PSM.CANVAS_HEIGHT,
            PSMLayerMgr.PANEL_WIDTH,
            PSMLayerMgr.PANEL_HEIGHT);
        this.add(this.mHandle);
        this.add(this.mNewLayer);
        //init gui transform
        arrangeUisToViewFormat(false);
    }
    public void arrangeUisToViewFormat(boolean enableAnimation) {
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
    }
    public void arrangeUisToListFormat(boolean enableAnimation) {
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
    }
    //method
    public void renderGuis(Graphics2D g) {
        for (PSMGui ui : this.mGuis) {
            ui.render(g);
        }
    }
}
