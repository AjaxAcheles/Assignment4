package grail.controllers.classes;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;

import grail.compositeShapes.interfaces.AvatarInterface;
import grail.compositeShapes.interfaces.BridgeSceneInterface;
import grail.controllers.interfaces.BridgeSceneControllerInterface;
import main.Factory;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({Comp301Tags.BRIDGE_SCENE_CONTROLLER})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Say", "Passed", "Failed", "Approach"})
@EditablePropertyNames({})
public class BridgeSceneController implements BridgeSceneControllerInterface {
    private static final int DEFAULT_CLICK_X = 0;
    private static final int DEFAULT_CLICK_Y = 0;
    private static final String SAY_LABEL = "Say";
    private static final String PASSED_LABEL = "Passed";
    private static final String FAILED_LABEL = "Failed";
    private static final String APPROACH_LABEL = "Approach";
    private static final String SAY_TEXT = "What is your quest?";
    private static final String PRECONDITION_PROPERTY = "this";
    private static final String SAY_METHOD = "say";
    private static final String PASSED_METHOD = "passed";
    private static final String FAILED_METHOD = "failed";
    private static final String APPROACH_METHOD = "approach";

    private final BridgeSceneInterface bridgeScene;
    private final Point arthurStart;
    private final Point lancelotStart;
    private final Point robinStart;
    private final Point galahadStart;
    private final Point guardStart;
    private final JButton say;
    private final JButton passed;
    private final JButton failed;
    private final JButton approach;
    private Point lastClickPoint;

    public BridgeSceneController(Component component) {
        this.bridgeScene = Factory.bridgeSceneFactoryMethod();
        this.lastClickPoint = new Point(DEFAULT_CLICK_X, DEFAULT_CLICK_Y);
        this.arthurStart = this.currentPoint(this.bridgeScene.getArthur());
        this.lancelotStart = this.currentPoint(this.bridgeScene.getLancelot());
        this.robinStart = this.currentPoint(this.bridgeScene.getRobin());
        this.galahadStart = this.currentPoint(this.bridgeScene.getGalahad());
        this.guardStart = this.currentPoint(this.bridgeScene.getGuard());
        this.say = new JButton(SAY_LABEL);
        this.passed = new JButton(PASSED_LABEL);
        this.failed = new JButton(FAILED_LABEL);
        this.approach = new JButton(APPROACH_LABEL);
        this.say.addActionListener(this);
        this.passed.addActionListener(this);
        this.failed.addActionListener(this);
        this.approach.addActionListener(this);
        this.updateButtonStates();
        this.bridgeScene.addPropertyChangeListener(this);
        component.addMouseListener(this);
        component.addKeyListener(this);
        component.setFocusable(true);
    }

    @Override
    public JButton getSay() {
        return this.say;
    }

    @Override
    public JButton getPassed() {
        return this.passed;
    }

    @Override
    public JButton getFailed() {
        return this.failed;
    }

    @Override
    public JButton getApproach() {
        return this.approach;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == this.say) {
            this.bridgeScene.say(SAY_TEXT);
        } else if (source == this.passed) {
            this.bridgeScene.passed();
        } else if (source == this.failed) {
            this.bridgeScene.failed();
        } else if (source == this.approach) {
            this.bridgeScene.approach(this.bridgeScene.getArthur());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (!PRECONDITION_PROPERTY.equals(event.getPropertyName())) {
            return;
        }
        String methodName = String.valueOf(event.getOldValue());
        boolean enabled = Boolean.TRUE.equals(event.getNewValue());
        if (SAY_METHOD.equals(methodName)) {
            this.say.setEnabled(enabled);
        } else if (PASSED_METHOD.equals(methodName)) {
            this.passed.setEnabled(enabled);
        } else if (FAILED_METHOD.equals(methodName)) {
            this.failed.setEnabled(enabled);
        } else if (APPROACH_METHOD.equals(methodName)) {
            this.approach.setEnabled(enabled);
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        this.lastClickPoint = event.getPoint();
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void keyTyped(KeyEvent event) {
        char keyChar = event.getKeyChar();
        if (keyChar == 'a') {
            this.moveAvatarToLastClick(this.bridgeScene.getArthur());
        } else if (keyChar == 'g') {
            this.moveAvatarToLastClick(this.bridgeScene.getGalahad());
        } else if (keyChar == 'l') {
            this.moveAvatarToLastClick(this.bridgeScene.getLancelot());
        } else if (keyChar == 'r') {
            this.moveAvatarToLastClick(this.bridgeScene.getRobin());
        } else if (keyChar == 'o') {
            this.returnAvatarsToOriginalPositions();
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    private Point currentPoint(AvatarInterface avatar) {
        return new Point(avatar.getX(), avatar.getY());
    }

    private void moveAvatarToLastClick(AvatarInterface avatar) {
        this.moveAvatarToPoint(avatar, this.lastClickPoint);
    }

    private void moveAvatarToPoint(AvatarInterface avatar, Point point) {
        avatar.move(point.x - avatar.getX(), point.y - avatar.getY());
    }

    private void returnAvatarsToOriginalPositions() {
        this.moveAvatarToPoint(this.bridgeScene.getArthur(), this.arthurStart);
        this.moveAvatarToPoint(this.bridgeScene.getLancelot(), this.lancelotStart);
        this.moveAvatarToPoint(this.bridgeScene.getRobin(), this.robinStart);
        this.moveAvatarToPoint(this.bridgeScene.getGalahad(), this.galahadStart);
        this.moveAvatarToPoint(this.bridgeScene.getGuard(), this.guardStart);
    }

    private void updateButtonStates() {
        this.say.setEnabled(this.bridgeScene.preSay());
        this.passed.setEnabled(this.bridgeScene.prePassed());
        this.failed.setEnabled(this.bridgeScene.preFailed());
        this.approach.setEnabled(this.bridgeScene.preApproach());
    }
}
