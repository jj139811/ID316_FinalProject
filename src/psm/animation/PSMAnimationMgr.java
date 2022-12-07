package psm.animation;

import java.util.ArrayList;
import psm.PSM;

public class PSMAnimationMgr extends Thread{
    //constant
    private static final long FRAME_TIME_MILLIS = 17;
    //Singleton
    private static PSMAnimationMgr instance;
    public static PSMAnimationMgr getSingleton() {
        if (instance == null) {
            instance = new PSMAnimationMgr();
        }
        return instance;
    }
    
    //field
    private ArrayList<PSMAnimatableObject> mAnimatableObjects = null;
    
    //constructor
    private PSMAnimationMgr() {
        this.mAnimatableObjects = new ArrayList<>();
    }
    
    //method
    public void addObject(PSMAnimatableObject target) {
        this.mAnimatableObjects.add(target);
    }
    public void removeObject(PSMAnimatableObject target) {
        this.mAnimatableObjects.remove(target);
    }
    
    //thread
    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        long prevTime = currentTime;
        while(true) {
            currentTime = System.currentTimeMillis();
            long dt = currentTime - prevTime;
            if (dt >= FRAME_TIME_MILLIS) {
                for (PSMAnimatableObject obj : this.mAnimatableObjects) {
                    obj.update(dt);
                }
                prevTime = currentTime; 
                PSM.getSingleton().getCanvas2D().repaint();
            }
            Thread.yield();
        }
    }
}
