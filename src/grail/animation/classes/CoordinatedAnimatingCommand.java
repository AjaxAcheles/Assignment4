package grail.animation.classes;

import grail.animation.interfaces.CoordinatedAvatarAnimatorInterface;
import grail.compositeShapes.interfaces.AvatarInterface;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags(Comp301Tags.COORDINATED_ANIMATING_COMMAND)
public class CoordinatedAnimatingCommand extends AnimatingCommand {
    public CoordinatedAnimatingCommand(CoordinatedAvatarAnimatorInterface animator,
            AvatarInterface avatar) {
        super(animator, avatar);
    }
}
