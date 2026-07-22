package grail.animation.classes;

import grail.animation.interfaces.CoordinatedAvatarAnimatorInterface;
import grail.concurrency.interfaces.BroadcastingClearanceManager;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags(Comp301Tags.COORDINATED_ANIMATOR)
public class CoordinatedAvatarAnimator extends AvatarAnimator
        implements CoordinatedAvatarAnimatorInterface {
    private final BroadcastingClearanceManager clearanceManager;

    public CoordinatedAvatarAnimator(BroadcastingClearanceManager manager) {
        this.clearanceManager = manager;
    }

    @Override
    protected void animationPause() {
        this.clearanceManager.waitForProceed();
    }
}
