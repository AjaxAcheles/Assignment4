package grail.animation.classes;

import grail.animation.interfaces.AvatarAnimatorInterface;
import grail.compositeShapes.interfaces.AvatarInterface;
import grail.concurrency.interfaces.BroadcastingClearanceManager;
import grail.exceptions.ImpossibleAngle;
import util.misc.ThreadSupport;

public abstract class AbstractAvatarAnimator implements AvatarAnimatorInterface {
    private static final int ANIMATION_PAUSE = 300;
    private static final int ANIMATION_STEPS = 4;
    private static final int WALK_DISTANCE = 10;
    private static final int ARM_ROTATION = 10;
    private final BroadcastingClearanceManager clearanceManager;
    private final boolean waitsForProceed;
    private final boolean broadcastsProceed;

    protected AbstractAvatarAnimator() {
        this(null, false, false);
    }

    protected AbstractAvatarAnimator(BroadcastingClearanceManager initialClearanceManager,
            boolean shouldWaitForProceed, boolean shouldBroadcastProceed) {
        this.clearanceManager = initialClearanceManager;
        this.waitsForProceed = shouldWaitForProceed;
        this.broadcastsProceed = shouldBroadcastProceed;
    }

    @Override
    public synchronized void animateAvatar(AvatarInterface avatar) {
        int step = 0;
        while (step < ANIMATION_STEPS) {
            if (this.waitsForProceed) {
                this.animationPause();
            }
            int distance = step % 2 == 0 ? WALK_DISTANCE : -WALK_DISTANCE;
            avatar.move(distance, 0);
            if (!this.waitsForProceed) {
                this.animationPause();
            }
            step++;
        }
    }

    @Override
    public synchronized void animateGuard(AvatarInterface avatar) {
        int step = 0;
        while (step < ANIMATION_STEPS) {
            int rotation = step % 2 == 0 ? ARM_ROTATION : -ARM_ROTATION;
            try {
                avatar.getArms().rotate(rotation);
            } catch (ImpossibleAngle exception) {
                exception.printStackTrace();
                return;
            }
            this.animationPause();
            step++;
        }
    }

    private void animationPause() {
        if (this.waitsForProceed) {
            this.clearanceManager.waitForProceed();
        } else if (this.broadcastsProceed) {
            this.clearanceManager.proceedAll();
            ThreadSupport.sleep(ANIMATION_PAUSE);
        } else {
            ThreadSupport.sleep(ANIMATION_PAUSE);
        }
    }
}
