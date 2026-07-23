package grail.views.classes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;

import grail.atomicShapes.interfaces.PointInterface;
import grail.atomicShapes.interfaces.TextInterface;
import grail.compositeShapes.interfaces.AngleInterface;
import grail.compositeShapes.interfaces.AvatarInterface;
import grail.compositeShapes.interfaces.BridgeSceneInterface;
import grail.compositeShapes.interfaces.GorgeInterface;
import grail.compositeShapes.interfaces.StandingAreaInterface;
import grail.simpleShapes.interfaces.ImageInterface;
import grail.simpleShapes.interfaces.LineInterface;
import util.models.PropertyListenerRegisterer;

public final class SceneViewSupport {
    private SceneViewSupport() {
    }

    public static void registerScene(BridgeSceneInterface scene, PropertyChangeListener listener) {
        registerAvatar(scene.getArthur(), listener);
        registerAvatar(scene.getLancelot(), listener);
        registerAvatar(scene.getRobin(), listener);
        registerAvatar(scene.getGalahad(), listener);
        registerAvatar(scene.getGuard(), listener);
        registerGorge(scene.getGorge(), listener);
        registerStandingArea(scene.getKnightArea(), listener);
        registerStandingArea(scene.getGuardArea(), listener);
    }

    private static void registerAvatar(AvatarInterface avatar, PropertyChangeListener listener) {
        registerAtomicShape(avatar.getSpeechBubble(), listener);
        registerAtomicShape(avatar.getHead(), listener);
        registerLine(avatar.getBody(), listener);
        registerAngle(avatar.getArms(), listener);
        registerAngle(avatar.getLegs(), listener);
    }

    private static void registerAngle(AngleInterface angle, PropertyChangeListener listener) {
        registerLine(angle.getLeftLine(), listener);
        registerLine(angle.getRightLine(), listener);
    }

    private static void registerGorge(GorgeInterface gorge, PropertyChangeListener listener) {
        registerLine(gorge.getLeftWall(), listener);
        registerLine(gorge.getRightWall(), listener);
        registerLine(gorge.getBridgeTop(), listener);
        registerLine(gorge.getBridgeBottom(), listener);
    }

    private static void registerStandingArea(StandingAreaInterface standingArea,
            PropertyChangeListener listener) {
        registerLine(standingArea.getTopLine(), listener);
        registerLine(standingArea.getRightBoundary(), listener);
        registerLine(standingArea.getBottomLine(), listener);
        registerLine(standingArea.getLeftBoundary(), listener);
    }

    private static void registerLine(LineInterface line, PropertyChangeListener listener) {
        registerAtomicShape(line, listener);
        PointInterface end = line.getEnd();
        registerAtomicShape(end, listener);
    }

    private static void registerAtomicShape(PropertyListenerRegisterer shape,
            PropertyChangeListener listener) {
        shape.addPropertyChangeListener(listener);
    }

    static void drawScene(Graphics graphics, BridgeSceneInterface bridgeScene,
            Component imageObserver) {
        drawGorge(graphics, bridgeScene.getGorge());
        drawStandingArea(graphics, bridgeScene.getKnightArea());
        drawStandingArea(graphics, bridgeScene.getGuardArea());
        drawAvatar(graphics, bridgeScene.getArthur(), imageObserver);
        drawAvatar(graphics, bridgeScene.getLancelot(), imageObserver);
        drawAvatar(graphics, bridgeScene.getRobin(), imageObserver);
        drawAvatar(graphics, bridgeScene.getGalahad(), imageObserver);
        drawAvatar(graphics, bridgeScene.getGuard(), imageObserver);
    }

    private static void drawLine(Graphics graphics, LineInterface line) {
        graphics.drawLine(
            line.getX(),
            line.getY(),
            line.getX() + line.getWidth(),
            line.getY() + line.getHeight()
        );
    }

    private static void drawText(Graphics graphics, TextInterface text) {
        graphics.drawString(text.getText(), text.getX(), text.getY());
    }

    private static void drawImage(Graphics graphics, ImageInterface image,
            Component imageObserver) {
        java.awt.Image loadedImage = Toolkit.getDefaultToolkit().getImage(
                image.getImageFileName());
        graphics.drawImage(
            loadedImage,
            image.getX(),
            image.getY(),
            image.getWidth(),
            image.getHeight(),
            imageObserver
        );
    }

    private static void drawAngle(Graphics graphics, AngleInterface angle) {
        drawLine(graphics, angle.getLeftLine());
        drawLine(graphics, angle.getRightLine());
    }

    private static void drawAvatar(Graphics graphics, AvatarInterface avatar,
            Component imageObserver) {
        drawText(graphics, avatar.getStringShape());
        drawImage(graphics, avatar.getHead(), imageObserver);
        drawAngle(graphics, avatar.getArms());
        drawLine(graphics, avatar.getBody());
        drawAngle(graphics, avatar.getLegs());
    }

    private static void drawGorge(Graphics graphics, GorgeInterface gorge) {
        drawLine(graphics, gorge.getLeftWall());
        drawLine(graphics, gorge.getRightWall());
        drawLine(graphics, gorge.getBridgeTop());
        drawLine(graphics, gorge.getBridgeBottom());
    }

    private static void drawStandingArea(Graphics graphics, StandingAreaInterface standingArea) {
        drawLine(graphics, standingArea.getTopLine());
        drawLine(graphics, standingArea.getRightBoundary());
        drawLine(graphics, standingArea.getBottomLine());
        drawLine(graphics, standingArea.getLeftBoundary());
    }
}
