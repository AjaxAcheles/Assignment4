package grail.animation.classes;

import grail.animation.interfaces.CoordinatingAvatarAnimatorInterface;
import grail.concurrency.interfaces.BroadcastingClearanceManager;
import tags301.Comp301Tags;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags(Comp301Tags.COORDINATING_ANIMATOR)
public class CoordinatingAvatarAnimator extends AvatarAnimator
        implements CoordinatingAvatarAnimatorInterface {
    private final BroadcastingClearanceManager clearanceManager;

    public CoordinatingAvatarAnimator(BroadcastingClearanceManager manager) {
        this.clearanceManager = manager;
    }

    @Override
    protected void animationPause() {
        ThreadSupport.sleep(ANIMATION_PAUSE);
        this.clearanceManager.proceedAll();
    }
}
