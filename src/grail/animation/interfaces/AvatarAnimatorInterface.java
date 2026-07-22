package grail.animation.interfaces;

import grail.compositeShapes.interfaces.AvatarInterface;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags(Comp301Tags.ANIMATOR)
public interface AvatarAnimatorInterface {
    void animateAvatar(AvatarInterface avatar);
    void animateGuard(AvatarInterface avatar);
}
