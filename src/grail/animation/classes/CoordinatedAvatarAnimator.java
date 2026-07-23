package grail.animation.classes;

import grail.animation.interfaces.CoordinatedAvatarAnimatorInterface;
import grail.concurrency.interfaces.BroadcastingClearanceManager;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags(Comp301Tags.COORDINATED_ANIMATOR)
public class CoordinatedAvatarAnimator extends AbstractAvatarAnimator
        implements CoordinatedAvatarAnimatorInterface {
    public CoordinatedAvatarAnimator(BroadcastingClearanceManager manager) {
        super(manager, true, false);
    }
}
