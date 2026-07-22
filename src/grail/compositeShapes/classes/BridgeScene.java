package grail.compositeShapes.classes;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import grail.animation.classes.AnimatingCommand;
import grail.animation.classes.AvatarAnimator;
import grail.animation.classes.CoordinatedAnimatingCommand;
import grail.animation.classes.CoordinatedAvatarAnimator;
import grail.animation.classes.CoordinatingAnimatingCommand;
import grail.animation.classes.CoordinatingAvatarAnimator;
import grail.animation.interfaces.AvatarAnimatorInterface;
import grail.animation.interfaces.CoordinatedAvatarAnimatorInterface;
import grail.animation.interfaces.CoordinatingAvatarAnimatorInterface;
import grail.compositeShapes.interfaces.AvatarInterface;
import grail.compositeShapes.interfaces.BridgeSceneInterface;
import grail.compositeShapes.interfaces.GorgeInterface;
import grail.compositeShapes.interfaces.StandingAreaInterface;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import grail.collections.interfaces.TableInterface;
import main.Factory;

@Tags(Comp301Tags.BRIDGE_SCENE)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Arthur", "Lancelot", "Robin", "Galahad", "Guard", "Gorge", "KnightArea", "GuardArea", "Occupied", "KnightTurn"})
@EditablePropertyNames({})
public class BridgeScene implements BridgeSceneInterface {
    private static final String NO_SPEECH = "";
    private static final int ARTHUR_X = 100;
    private static final int ARTHUR_Y = 230;
    private static final int LANCELOT_X = 230;
    private static final int LANCELOT_Y = 230;
    private static final int ROBIN_X = 360;
    private static final int ROBIN_Y = 230;
    private static final int GALAHAD_X = 490;
    private static final int GALAHAD_Y = 230;
    private static final int GUARD_X = 830;
    private static final int GUARD_Y = 230;
    private static final int GORGE_X = 660;
    private static final int GORGE_Y = 80;
    private static final int GORGE_WIDTH = 100;
    private static final int GORGE_HEIGHT = 330;
    private static final int BRIDGE_Y = 220;
    private static final int BRIDGE_HEIGHT = 55;
    private static final int KNIGHT_AREA_X = 575;
    private static final int KNIGHT_AREA_Y = 200;
    private static final int GUARD_AREA_X = 805;
    private static final int GUARD_AREA_Y = 200;
    private static final int AREA_WIDTH = 70;
    private static final int AREA_HEIGHT = 90;
    private static final int PASSED_X = 925;
    private static final int CENTER_DIVISOR = 2;
    private static final int FAILED_X = GORGE_X + GORGE_WIDTH / CENTER_DIVISOR;
    private static final int FAILED_Y = GORGE_Y + GORGE_HEIGHT - 30;
    private static final int GUARD_FAILED_X = FAILED_X;
    private static final int GUARD_FAILED_Y = FAILED_Y;
    private static final String ARTHUR_SPEECH = "I am Arthur";
    private static final String ARTHUR_IMAGE = "images/arthur.jpg";
    private static final String LANCELOT_SPEECH = "I am Lancelot";
    private static final String LANCELOT_IMAGE = "images/lancelot.jpg";
    private static final String ROBIN_SPEECH = "I am Robin";
    private static final String ROBIN_IMAGE = "images/robin.jpg";
    private static final String GALAHAD_SPEECH = "I am Galahad";
    private static final String GALAHAD_IMAGE = "images/galahad.jpg";
    private static final String GUARD_SPEECH = "I am random guard";
    private static final String GUARD_IMAGE = "images/guard.jpg";
    private static final String PRECONDITION_PROPERTY = "this";
    private static final String APPROACH_METHOD = "approach";
    private static final String SAY_METHOD = "say";
    private static final String PASSED_METHOD = "passed";
    private static final String FAILED_METHOD = "failed";
	
    private final AvatarInterface arthur;
    private final AvatarInterface lancelot;
    private final AvatarInterface robin;
    private final AvatarInterface galahad;
    private final AvatarInterface guard;
    private final GorgeInterface gorge;
    private final StandingAreaInterface knightArea;
    private final StandingAreaInterface guardArea;
    private final List<PropertyChangeListener> propertyChangeListeners;
    private final AvatarAnimatorInterface arthurAnimator;
    private final AvatarAnimatorInterface galahadAnimator;
    private final AvatarAnimatorInterface lancelotAnimator;
    private final AvatarAnimatorInterface robinAnimator;
    private final AvatarAnimatorInterface guardAnimator;
    private final CoordinatedAvatarAnimatorInterface coordinatedArthurAnimator;
    private final CoordinatedAvatarAnimatorInterface coordinatedGalahadAnimator;
    private final CoordinatedAvatarAnimatorInterface coordinatedLancelotAnimator;
    private final CoordinatedAvatarAnimatorInterface coordinatedRobinAnimator;
    private final CoordinatingAvatarAnimatorInterface coordinatingGuardAnimator;
    private boolean knightTurn;
    private AvatarInterface failureTarget;

    public BridgeScene() {
        // init objs
        this.arthur = new Avatar(ARTHUR_X, ARTHUR_Y, ARTHUR_SPEECH, ARTHUR_IMAGE);
        this.lancelot = new Avatar(LANCELOT_X, LANCELOT_Y, LANCELOT_SPEECH, LANCELOT_IMAGE);
        this.robin = new Avatar(ROBIN_X, ROBIN_Y, ROBIN_SPEECH, ROBIN_IMAGE);
        this.galahad = new Avatar(GALAHAD_X, GALAHAD_Y, GALAHAD_SPEECH, GALAHAD_IMAGE);
        this.guard = new Avatar(GUARD_X, GUARD_Y, GUARD_SPEECH, GUARD_IMAGE);
        this.gorge = new Gorge(GORGE_X, GORGE_Y, GORGE_WIDTH, GORGE_HEIGHT, BRIDGE_Y, BRIDGE_HEIGHT);
        this.knightArea = new StandingArea(KNIGHT_AREA_X, KNIGHT_AREA_Y, AREA_WIDTH, AREA_HEIGHT);
        this.guardArea = new StandingArea(GUARD_AREA_X, GUARD_AREA_Y, AREA_WIDTH, AREA_HEIGHT);
        this.propertyChangeListeners = new ArrayList<PropertyChangeListener>();
        this.arthurAnimator = new AvatarAnimator();
        this.galahadAnimator = new AvatarAnimator();
        this.lancelotAnimator = new AvatarAnimator();
        this.robinAnimator = new AvatarAnimator();
        this.guardAnimator = new AvatarAnimator();
        this.coordinatedArthurAnimator = new CoordinatedAvatarAnimator(
                Factory.broadcastingClearanceManagerFactoryMethod());
        this.coordinatedGalahadAnimator = new CoordinatedAvatarAnimator(
                Factory.broadcastingClearanceManagerFactoryMethod());
        this.coordinatedLancelotAnimator = new CoordinatedAvatarAnimator(
                Factory.broadcastingClearanceManagerFactoryMethod());
        this.coordinatedRobinAnimator = new CoordinatedAvatarAnimator(
                Factory.broadcastingClearanceManagerFactoryMethod());
        this.coordinatingGuardAnimator = new CoordinatingAvatarAnimator(
                Factory.broadcastingClearanceManagerFactoryMethod());
        // store objs in a table
        TableInterface<AvatarInterface> avatarTable = Factory.avatarTableFactoryMethod();
        avatarTable.put("Arthur", this.arthur);
        avatarTable.put("Lancelot", this.lancelot);
        avatarTable.put("Robin", this.robin);
        avatarTable.put("Galahad", this.galahad);
        avatarTable.put("Guard", this.guard);

        this.knightTurn = false;
        this.failureTarget = null;
        this.clearSpeech();
    }

    @Override
    public AvatarInterface getArthur() {
        return this.arthur;
    }

    @Override
    public AvatarInterface getLancelot() {
        return this.lancelot;
    }

    @Override
    public AvatarInterface getRobin() {
        return this.robin;
    }

    @Override
    public AvatarInterface getGalahad() {
        return this.galahad;
    }
    
    @Override
    public AvatarInterface getGuard() {
        return this.guard;
    }

    @Override
    public GorgeInterface getGorge() {
        return this.gorge;
    }

    @Override
    public StandingAreaInterface getKnightArea() {
        return this.knightArea;
    }

    @Override
    public StandingAreaInterface getGuardArea() {
        return this.guardArea;
    }

    @Override
    public boolean getOccupied() {
        return this.getOccupyingKnight() != null;
    }

    @Override
    public boolean getKnightTurn() {
        return this.knightTurn;
    }

    @Override
    public boolean preApproach() {
        return !this.getOccupied();
    }

    @Override
    public boolean preSay() {
        return this.getOccupied();
    }

    @Override
    public boolean prePassed() {
        return this.getOccupied() && !this.getKnightTurn();
    }

    @Override
    public boolean preFailed() {
        return this.getOccupied();
    }

    @Override
    @Tags(Comp301Tags.APPROACH)
    public void approach(AvatarInterface avatar) {
        assert this.preApproach();
        if (!this.preApproach() || !this.isKnight(avatar)) {
            return;
        }
        boolean oldApproach = this.preApproach();
        boolean oldSay = this.preSay();
        boolean oldPassed = this.prePassed();
        boolean oldFailed = this.preFailed();
        this.clearSpeech();
        this.moveTo(avatar, this.knightArea.getCenterX(), this.knightArea.getCenterY());
        this.knightTurn = false;
        this.failureTarget = null;
        this.notifyPreconditionChanges(oldApproach, oldSay, oldPassed, oldFailed);
    }

    @Override
    @Tags(Comp301Tags.APPROACH)
    public void approach(String avatarName) {
        assert this.preApproach();
        AvatarInterface avatar = Factory.avatarTableFactoryMethod().get(avatarName);
        this.approach(avatar);
    }

    @Override
    @Tags(Comp301Tags.SAY)
    public void say(String text) {
        assert this.preSay();
        if (!this.preSay()) {
            return;
        }
        boolean oldApproach = this.preApproach();
        boolean oldSay = this.preSay();
        boolean oldPassed = this.prePassed();
        boolean oldFailed = this.preFailed();
        if (this.knightTurn) {
            AvatarInterface knight = this.getOccupyingKnight();
            if (knight != null) {
                this.sayAs(knight, text);
                this.failureTarget = this.textAsksQuestion(text) ? this.guard : knight;
            }
        } else {
            this.sayAs(this.guard, text);
            this.failureTarget = this.getOccupyingKnight();
        }
        this.knightTurn = !this.knightTurn;
        this.notifyPreconditionChanges(oldApproach, oldSay, oldPassed, oldFailed);
    }

    @Override
    @Tags(Comp301Tags.PASSED)
    public void passed() {
        assert this.prePassed();
        if (!this.prePassed()) {
            return;
        }
        boolean oldApproach = this.preApproach();
        boolean oldSay = this.preSay();
        boolean oldPassed = this.prePassed();
        boolean oldFailed = this.preFailed();
        AvatarInterface knight = this.getOccupyingKnight();
        if (knight != null) {
            this.clearSpeech();
            this.moveTo(knight, PASSED_X, this.knightArea.getCenterY());
            this.knightTurn = false;
            this.failureTarget = null;
        }
        this.notifyPreconditionChanges(oldApproach, oldSay, oldPassed, oldFailed);
    }

    @Override
    @Tags(Comp301Tags.FAILED)
    public void failed() {
        assert this.preFailed();
        if (!this.preFailed()) {
            return;
        }
        boolean oldApproach = this.preApproach();
        boolean oldSay = this.preSay();
        boolean oldPassed = this.prePassed();
        boolean oldFailed = this.preFailed();
        AvatarInterface failedAvatar = this.failureTarget;
        if (failedAvatar == null) {
            failedAvatar = this.getOccupyingKnight();
        }

        if (failedAvatar == this.guard) {
            this.clearSpeech();
            this.moveTo(this.guard, GUARD_FAILED_X, GUARD_FAILED_Y);
            this.knightTurn = false;
            this.failureTarget = null;
        } else if (failedAvatar != null) {
            this.clearSpeech();
            this.moveTo(failedAvatar, FAILED_X, FAILED_Y);
            this.knightTurn = false;
            this.failureTarget = null;
        }
        this.notifyPreconditionChanges(oldApproach, oldSay, oldPassed, oldFailed);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeListeners.add(listener);
    }

    @Override
    public void scroll(int changeInX, int changeInY) {
        this.arthur.move(-changeInX, -changeInY);
        this.lancelot.move(-changeInX, -changeInY);
        this.robin.move(-changeInX, -changeInY);
        this.galahad.move(-changeInX, -changeInY);
        this.guard.move(-changeInX, -changeInY);
    }

    @Override
    public void asynchronousArthur() {
        this.startCommand(new AnimatingCommand(this.arthurAnimator, this.arthur));
    }

    @Override
    public void asynchronousGalahad() {
        this.startCommand(new AnimatingCommand(this.galahadAnimator, this.galahad));
    }

    @Override
    public void asynchronousLancelot() {
        this.startCommand(new AnimatingCommand(this.lancelotAnimator, this.lancelot));
    }

    @Override
    public void asynchronousRobin() {
        this.startCommand(new AnimatingCommand(this.robinAnimator, this.robin));
    }

    @Override
    public void asynchronousGuard() {
        this.startCommand(new AnimatingCommand(this.guardAnimator, this.guard, true, false));
    }

    @Override
    public void waitingArthur() {
        this.startCommand(new AnimatingCommand(this.arthurAnimator, this.arthur, false, true));
    }

    @Override
    public void waitingGalahad() {
        this.startCommand(new AnimatingCommand(this.galahadAnimator, this.galahad, false, true));
    }

    @Override
    public void waitingLancelot() {
        this.startCommand(new AnimatingCommand(this.lancelotAnimator, this.lancelot, false, true));
    }

    @Override
    public void waitingRobin() {
        this.startCommand(new AnimatingCommand(this.robinAnimator, this.robin, false, true));
    }

    @Override
    public void startAnimation() {
        Factory.broadcastingClearanceManagerFactoryMethod().proceedAll();
    }

    @Override
    public void lockstepArthur() {
        this.startCommand(new CoordinatedAnimatingCommand(
                this.coordinatedArthurAnimator, this.arthur));
    }

    @Override
    public void lockstepGalahad() {
        this.startCommand(new CoordinatedAnimatingCommand(
                this.coordinatedGalahadAnimator, this.galahad));
    }

    @Override
    public void lockstepLancelot() {
        this.startCommand(new CoordinatedAnimatingCommand(
                this.coordinatedLancelotAnimator, this.lancelot));
    }

    @Override
    public void lockstepRobin() {
        this.startCommand(new CoordinatedAnimatingCommand(
                this.coordinatedRobinAnimator, this.robin));
    }

    @Override
    public void lockstepGuard() {
        this.startCommand(new CoordinatingAnimatingCommand(
                this.coordinatingGuardAnimator, this.guard));
    }

    private void startCommand(Runnable command) {
        new Thread(command).start();
    }

    private AvatarInterface getOccupyingKnight() {
        if (this.knightArea.contains(this.arthur)) {
            return this.arthur;
        }
        if (this.knightArea.contains(this.lancelot)) {
            return this.lancelot;
        }
        if (this.knightArea.contains(this.robin)) {
            return this.robin;
        }
        if (this.knightArea.contains(this.galahad)) {
            return this.galahad;
        }
        return null;
    }

    private boolean isKnight(AvatarInterface avatar) {
        return avatar == this.arthur
                || avatar == this.lancelot
                || avatar == this.robin
                || avatar == this.galahad;
    }

    private void moveTo(AvatarInterface avatar, int targetX, int targetY) {
        avatar.move(targetX - avatar.getX(), targetY - avatar.getY());
    }

    private void sayAs(AvatarInterface avatar, String text) {
        this.clearSpeech();
        avatar.getStringShape().setText(text);
    }

    private void clearSpeech() {
        this.arthur.getStringShape().setText(NO_SPEECH);
        this.lancelot.getStringShape().setText(NO_SPEECH);
        this.robin.getStringShape().setText(NO_SPEECH);
        this.galahad.getStringShape().setText(NO_SPEECH);
        this.guard.getStringShape().setText(NO_SPEECH);
    }

    private boolean textAsksQuestion(String text) {
        return text.trim().endsWith("?");
    }

    private void notifyPreconditionChanges(boolean oldApproach, boolean oldSay,
            boolean oldPassed, boolean oldFailed) {
        this.notifyPreconditionChange(APPROACH_METHOD, oldApproach, this.preApproach());
        this.notifyPreconditionChange(SAY_METHOD, oldSay, this.preSay());
        this.notifyPreconditionChange(PASSED_METHOD, oldPassed, this.prePassed());
        this.notifyPreconditionChange(FAILED_METHOD, oldFailed, this.preFailed());
    }

    private void notifyPreconditionChange(String methodName, boolean oldValue,
            boolean newValue) {
        if (oldValue == newValue) {
            return;
        }
        PropertyChangeEvent event = new PropertyChangeEvent(
                this, PRECONDITION_PROPERTY, methodName, newValue);
        int listenerIndex = 0;
        while (listenerIndex < this.propertyChangeListeners.size()) {
            PropertyChangeListener listener = this.propertyChangeListeners.get(listenerIndex);
            listener.propertyChange(event);
            listenerIndex++;
        }
    }
}
