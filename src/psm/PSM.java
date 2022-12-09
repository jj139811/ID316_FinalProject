package psm;

import X.XApp;
import X.XLogMgr;
import X.XScenarioMgr;
import javax.swing.JFrame;
import psm.animation.PSMAnimationMgr;

public class PSM extends XApp{
    //constant
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    
    public static final int CAMERA_BOUND_X = 1600;
    public static final int CAMERA_BOUND_Y = 1200;
    
    // singleton
    private static PSM instance = null;
    public static PSM getSingleton() {
        if (instance == null) {
            instance = new PSM();
        }
        return instance;
    }
    
    //override
    private XScenarioMgr mScenarioMgr = null;
    private XLogMgr mLogMgr = null;
    
    @Override
    public XScenarioMgr getScenarioMgr() {
        return this.mScenarioMgr;
    }

    @Override
    public XLogMgr getLogMgr() {
        return this.mLogMgr;
    }
    //field
    
    private JFrame mFrame = null;
    private PSMCanvas2D mCanvas2D = null;
    public PSMCanvas2D getCanvas2D() {
        return this.mCanvas2D;
    }
    
    private PSMLayerMgr mLayerMgr = null;
    private PSMAnimationMgr mAnimationMgr = null;
    public PSMAnimationMgr getAnimationMgr() {
        return this.mAnimationMgr;
    }
    private PSMScreenMgr mScreenMgr = null;
    private PSMEventListener mEventListener = null;
    private PSMBrushMgr mBrushMgr = null;
    public PSMBrushMgr getBrushMgr() {
        return this.mBrushMgr;
    }

    private PSMXForm mPSMXForm = null;
    public PSMXForm getXForm() {
        return this.mPSMXForm;
    }
    
    //private constructor
    private PSM() {
        this.mScenarioMgr = new PSMScenarioMgr(this);
        this.mLogMgr = new XLogMgr();
 
        this.mLayerMgr = PSMLayerMgr.getSingleton();
        this.mAnimationMgr = PSMAnimationMgr.getSingleton();
        this.mScreenMgr = PSMScreenMgr.getSingleton();
        this.mEventListener = PSMEventListener.getSingleton();
        this.mBrushMgr = PSMBrushMgr.getSingleton();
        
        this.mFrame = new JFrame("ParallaxSceneMaker");
        this.mCanvas2D = new PSMCanvas2D();

        this.mPSMXForm = new PSMXForm();

        
        //connect event listeners 
        this.mCanvas2D.addMouseListener(this.mEventListener);
        this.mCanvas2D.addMouseMotionListener(this.mEventListener);
        this.mCanvas2D.setFocusable(true);
        this.mCanvas2D.addKeyListener(this.mEventListener);
        
        //build and show visual components
        this.mFrame.add(this.mCanvas2D);
        this.mFrame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.mFrame.setResizable(false);
        this.mFrame.setVisible(true);
        this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        //LOG_ON
        this.mLogMgr.setPrintOn(true);
    }
    public static void main(String[] args) {
        PSM psmInstance = PSM.getSingleton();
        psmInstance.getAnimationMgr().start();
        
        
        // Test
        PSMLayerMgr layerMgr = PSMLayerMgr.getSingleton();
        PSMLayer focusedLayer = new PSMLayer(0.0f);
        layerMgr.addLayer(focusedLayer);
        layerMgr.addLayer(new PSMLayer(0.5f));
        layerMgr.addLayer(new PSMLayer(1.0f));
            
        layerMgr.setFocusedLayer(focusedLayer);
        PSMScreenMgr.getSingleton().getCamera().setScale(0.5f, 0.5f);
    }
    
}
