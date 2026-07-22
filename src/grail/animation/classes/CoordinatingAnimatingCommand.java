package grail.animation.classes;

import grail.animation.interfaces.CoordinatingAvatarAnimatorInterface;
import grail.compositeShapes.interfaces.AvatarInterface;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags(Comp301Tags.COORDINATING_ANIMATING_COMMAND)
public class CoordinatingAnimatingCommand extends AnimatingCommand {
    public CoordinatingAnimatingCommand(CoordinatingAvatarAnimatorInterface animator,
            AvatarInterface avatar) {
        super(animator, avatar, true, false);
    }
}
