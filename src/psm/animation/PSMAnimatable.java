package psm.animation;

public abstract class PSMAnimatable {
    public PSMAnimatable() {
        PSMAnimationMgr animationMgr = PSMAnimationMgr.getSingleton();
        animationMgr.add(this);
    }
    public final void destroy() {
        PSMAnimationMgr animationMgr = PSMAnimationMgr.getSingleton();
        animationMgr.remove(this);
    }
    public abstract void update(long t);
}
