package grail.animation.classes;

import grail.animation.interfaces.AvatarAnimatorInterface;
import grail.compositeShapes.interfaces.AvatarInterface;
import grail.exceptions.ImpossibleAngle;
import tags301.Comp301Tags;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags(Comp301Tags.ANIMATOR)
public class AvatarAnimator implements AvatarAnimatorInterface {
    protected static final int ANIMATION_PAUSE = 100;
    private static final int ANIMATION_STEPS = 4;
    private static final int WALK_DISTANCE = 10;
    private static final int ARM_ROTATION = 10;

    @Override
    public synchronized void animateAvatar(AvatarInterface avatar) {
        int step = 0;
        while (step < ANIMATION_STEPS) {
            int distance = step % 2 == 0 ? WALK_DISTANCE : -WALK_DISTANCE;
            avatar.move(distance, 0);
            this.animationPause();
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

    protected void animationPause() {
        ThreadSupport.sleep(ANIMATION_PAUSE);
    }
}
