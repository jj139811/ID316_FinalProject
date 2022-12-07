package psm;

import X.XScenario;
import X.XScene;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class PSMScene extends XScene {
    //constructor
    protected PSMScene (XScenario scenario) {
        super(scenario);
    }
    
    //event handling abstract methods
    public abstract void handleMousePress(MouseEvent e); 
    public abstract void handleMouseDrag(MouseEvent e); 
    public abstract void handleMouseRelease(MouseEvent e); 
    public abstract void handleKeyDown(KeyEvent e); 
    public abstract void handleKeyUp(KeyEvent e); 
    
    //other abstract method
    public abstract void updateSupportObjects();
    public abstract void renderWorldObjects(Graphics2D g2);
    public abstract void renderScreenObjects(Graphics2D g2);
        
}