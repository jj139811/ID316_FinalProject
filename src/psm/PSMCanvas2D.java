package psm;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import psm.gui.PSMGuiMgr;

public class PSMCanvas2D extends JPanel{
    //constructor
    public PSMCanvas2D() {
        
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        PSM psm = PSM.getSingleton();
        PSMScene curScene = (PSMScene)psm.getScenarioMgr().getCurScene();
        curScene.renderWorldObjects(g2);
        curScene.renderScreenObjects(g2);
        PSMGuiMgr.getSingleton().renderGuis(g2);
    }
}
