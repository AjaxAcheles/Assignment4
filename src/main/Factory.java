package main;

import java.awt.Component;

import grail.collections.classes.Table;
import grail.collections.interfaces.TableInterface;
import grail.concurrency.classes.ABroadcastingClearanceManager;
import grail.concurrency.interfaces.BroadcastingClearanceManager;
import grail.views.classes.DelegatingBridgeSceneView;
import grail.views.classes.InheritingBridgeScenePainter;
import grail.views.classes.ObservableBridgeScenePainter;
import grail.views.interfaces.DelegatingBridgeSceneViewInterface;
import grail.views.interfaces.InheritingBridgeScenePainterInterface;
import grail.views.interfaces.ObservableBridgeScenePainterInterface;

import grail.compositeShapes.classes.BridgeScene;
import grail.compositeShapes.classes.Legs;
import grail.compositeShapes.interfaces.AngleInterface;
import grail.compositeShapes.interfaces.AvatarInterface;
import grail.compositeShapes.interfaces.BridgeSceneInterface;
import grail.controllers.classes.BridgeSceneController;
import grail.controllers.interfaces.BridgeSceneControllerInterface;
import grail.views.classes.ConsoleSceneView;
import grail.views.interfaces.ConsoleSceneViewInterface;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.FACTORY_CLASS})
public class Factory {
    private static BridgeSceneInterface bridgeScene;
    private static ConsoleSceneViewInterface consoleSceneView;
    private static TableInterface<AvatarInterface> avatarTable;
    private static InheritingBridgeScenePainterInterface inheritingBridgeScenePainter;
    private static ObservableBridgeScenePainterInterface observableBridgeScenePainter;
    private static DelegatingBridgeSceneViewInterface delegatingBridgeSceneView;
    private static BridgeSceneControllerInterface bridgeSceneController;
    private static BroadcastingClearanceManager broadcastingClearanceManager;



    public static synchronized BridgeSceneInterface bridgeSceneFactoryMethod() {
        if (bridgeScene == null) {
            bridgeScene = new BridgeScene();
        }
        return bridgeScene;
    }

    public static synchronized ConsoleSceneViewInterface consoleSceneViewFactoryMethod() {
        if (consoleSceneView == null) {
            consoleSceneView = new ConsoleSceneView();
        }
        return consoleSceneView;
    }

    public static synchronized AngleInterface legsFactoryMethod(int initialX, int initialY,
            double radius,
            double splitAngleRadians, double downDirectionRadians) {
        return new Legs(initialX, initialY, radius, splitAngleRadians, downDirectionRadians);
    }

    public static synchronized TableInterface<AvatarInterface> avatarTableFactoryMethod() {
        if (avatarTable == null) {
            avatarTable = new Table<AvatarInterface>();
        }
        return avatarTable;
    }

    public static synchronized InheritingBridgeScenePainterInterface
            inheritingBridgeScenePainterFactoryMethod() {
        if (inheritingBridgeScenePainter == null) {
            inheritingBridgeScenePainter = new InheritingBridgeScenePainter();
        }
        return inheritingBridgeScenePainter;
    }

    public static synchronized ObservableBridgeScenePainterInterface
            observableBridgeScenePainterFactoryMethod() {
        if (observableBridgeScenePainter == null) {
            observableBridgeScenePainter = new ObservableBridgeScenePainter();
        }
        return observableBridgeScenePainter;
    }

    public static synchronized DelegatingBridgeSceneViewInterface
            delegatingBridgeSceneViewFactoryMethod() {
        if (delegatingBridgeSceneView == null) {
            delegatingBridgeSceneView = new DelegatingBridgeSceneView();
        }
        return delegatingBridgeSceneView;
    }

    public static synchronized BridgeSceneControllerInterface bridgeSceneControllerFactoryMethod() {
        if (bridgeSceneController == null) {
            Component component = (Component) observableBridgeScenePainterFactoryMethod();
            bridgeSceneController = new BridgeSceneController(component);
        }
        return bridgeSceneController;
    }

    public static synchronized BroadcastingClearanceManager
            broadcastingClearanceManagerFactoryMethod() {
        if (broadcastingClearanceManager == null) {
            broadcastingClearanceManager = new ABroadcastingClearanceManager();
        }
        return broadcastingClearanceManager;
    }

}
