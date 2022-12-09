package psm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PSMEventListener implements MouseListener, MouseMotionListener, 
    KeyListener{
    //singleton
    private static PSMEventListener instance;
    public static PSMEventListener getSingleton() {
        if (instance == null) {
            instance = new PSMEventListener();
        }
        return instance;
    }
    //constructor

    @Override
    public void mouseClicked(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        PSMScene curScene = (PSMScene)PSM.getSingleton().
            getScenarioMgr().getCurScene();
        curScene.handleMousePress(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PSMScene curScene = (PSMScene)PSM.getSingleton().
            getScenarioMgr().getCurScene();
        curScene.handleMouseRelease(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        PSMScene curScene = (PSMScene)PSM.getSingleton().
            getScenarioMgr().getCurScene();
        curScene.handleMouseDrag(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        PSMScene curScene = (PSMScene)PSM.getSingleton().
            getScenarioMgr().getCurScene();
        curScene.handleKeyDown(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PSMScene curScene = (PSMScene)PSM.getSingleton().
            getScenarioMgr().getCurScene();
        curScene.handleKeyUp(e);
    }
}
