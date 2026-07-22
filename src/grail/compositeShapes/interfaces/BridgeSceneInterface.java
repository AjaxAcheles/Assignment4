package grail.compositeShapes.interfaces;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags(Comp301Tags.BRIDGE_SCENE)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Arthur", "Lancelot", "Robin", "Galahad", "Guard", "Gorge", "KnightArea", "GuardArea", "Occupied", "KnightTurn"})
@EditablePropertyNames({})
public interface BridgeSceneInterface extends PropertyListenerRegisterer {
    AvatarInterface getArthur();
    AvatarInterface getLancelot();
    AvatarInterface getRobin();
    AvatarInterface getGalahad();
    AvatarInterface getGuard();
    GorgeInterface getGorge();
    StandingAreaInterface getKnightArea();
    StandingAreaInterface getGuardArea();
    boolean getOccupied();
    boolean getKnightTurn();
    boolean preApproach();
    boolean preSay();
    boolean prePassed();
    boolean preFailed();
    void approach(AvatarInterface avatar);
    void approach(String avatarName);
    void say(String text);
    void passed();
    void failed();
    void scroll(int changeInX, int changeInY);
    void asynchronousArthur();
    void asynchronousGalahad();
    void asynchronousLancelot();
    void asynchronousRobin();
    void asynchronousGuard();
    void waitingArthur();
    void waitingGalahad();
    void waitingLancelot();
    void waitingRobin();
    void startAnimation();
    void lockstepArthur();
    void lockstepGalahad();
    void lockstepLancelot();
    void lockstepRobin();
    void lockstepGuard();
}
