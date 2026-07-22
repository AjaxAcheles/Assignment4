package grail.controllers.classes;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import grail.compositeShapes.interfaces.AvatarInterface;
import grail.compositeShapes.interfaces.BridgeSceneInterface;
import grail.controllers.interfaces.BridgeSceneControllerInterface;
import main.Factory;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.BRIDGE_SCENE_CONTROLLER})
public class BridgeSceneController implements BridgeSceneControllerInterface {
    private static final int DEFAULT_CLICK_X = 0;
    private static final int DEFAULT_CLICK_Y = 0;

    private final BridgeSceneInterface bridgeScene;
    private final Point arthurStart;
    private final Point lancelotStart;
    private final Point robinStart;
    private final Point galahadStart;
    private final Point guardStart;
    private Point lastClickPoint;

    public BridgeSceneController(Component component) {
        this.bridgeScene = Factory.bridgeSceneFactoryMethod();
        this.lastClickPoint = new Point(DEFAULT_CLICK_X, DEFAULT_CLICK_Y);
        this.arthurStart = this.currentPoint(this.bridgeScene.getArthur());
        this.lancelotStart = this.currentPoint(this.bridgeScene.getLancelot());
        this.robinStart = this.currentPoint(this.bridgeScene.getRobin());
        this.galahadStart = this.currentPoint(this.bridgeScene.getGalahad());
        this.guardStart = this.currentPoint(this.bridgeScene.getGuard());
        component.addMouseListener(this);
        component.addKeyListener(this);
        component.setFocusable(true);
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
}