package grail.animation.classes;

import grail.animation.interfaces.CoordinatingAvatarAnimatorInterface;
import grail.concurrency.interfaces.BroadcastingClearanceManager;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags(Comp301Tags.COORDINATING_ANIMATOR)
public class CoordinatingAvatarAnimator extends AbstractAvatarAnimator
        implements CoordinatingAvatarAnimatorInterface {
    public CoordinatingAvatarAnimator(BroadcastingClearanceManager manager) {
        super(manager, false, true);
    }
}
