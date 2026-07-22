package grail.animation.classes;

import grail.animation.interfaces.AvatarAnimatorInterface;
import grail.compositeShapes.interfaces.AvatarInterface;
import grail.concurrency.interfaces.BroadcastingClearanceManager;
import main.Factory;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags(Comp301Tags.ANIMATING_COMMAND)
public class AnimatingCommand implements Runnable {
    private final AvatarAnimatorInterface animator;
    private final AvatarInterface avatar;
    private final boolean guardAnimation;
    private final boolean waitForStart;

    public AnimatingCommand(AvatarAnimatorInterface initialAnimator,
            AvatarInterface initialAvatar) {
        this(initialAnimator, initialAvatar, false, false);
    }

    public AnimatingCommand(AvatarAnimatorInterface initialAnimator,
            AvatarInterface initialAvatar, boolean isGuardAnimation,
            boolean shouldWaitForStart) {
        this.animator = initialAnimator;
        this.avatar = initialAvatar;
        this.guardAnimation = isGuardAnimation;
        this.waitForStart = shouldWaitForStart;
    }

    @Override
    public void run() {
        if (this.waitForStart) {
            BroadcastingClearanceManager manager =
                    Factory.broadcastingClearanceManagerFactoryMethod();
            manager.waitForProceed();
        }
        if (this.guardAnimation) {
            this.animator.animateGuard(this.avatar);
        } else {
            this.animator.animateAvatar(this.avatar);
        }
    }
}
