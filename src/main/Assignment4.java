package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import bus.uigen.ObjectEditor;
import grail.compositeShapes.interfaces.BridgeSceneInterface;
import grail.concurrency.interfaces.BroadcastingClearanceManager;
import grail.controllers.interfaces.BridgeSceneControllerInterface;
import grail.exceptions.ImpossibleAngle;
import util.misc.ThreadSupport;

public class Assignment4 {
    private static final String FRAME_TITLE = "Bridge Scene";
    private static final int FRAME_WIDTH = 1100;
    private static final int FRAME_HEIGHT = 600;
    private static final int EDITOR_WIDTH = 1050;
    private static final int EDITOR_HEIGHT = 550;
    private static final int DEMO_PAUSE = 1000;
    private static final double ILLEGAL_LEFT_LEG_ANGLE = 0.0;
    private static final String GUARD_QUESTION = "What is your quest?";
    private static final String KNIGHT_ANSWER = "To seek the Holy Grail.";

    public static void main(String[] args) {
        BridgeSceneInterface bridgeScene = Factory.bridgeSceneFactoryMethod();
        Factory.consoleSceneViewFactoryMethod();
        Factory.inheritingBridgeScenePainterFactoryMethod();
        Component painter = (Component) Factory.observableBridgeScenePainterFactoryMethod();
        Factory.delegatingBridgeSceneViewFactoryMethod();
        BridgeSceneControllerInterface controller =
                Factory.bridgeSceneControllerFactoryMethod();
        BroadcastingClearanceManager clearanceManager =
                Factory.broadcastingClearanceManagerFactoryMethod();

        displayPainter(painter, controller);
        ObjectEditor.edit(bridgeScene).setSize(EDITOR_WIDTH, EDITOR_HEIGHT);
        ObjectEditor.edit(clearanceManager);

        demonstrateAssertions(bridgeScene);
        demonstrateImpossibleAngle(bridgeScene);
        demonstrateAnimations(bridgeScene);
    }

    private static void displayPainter(Component painter,
            BridgeSceneControllerInterface controller) {
        Frame frame = new Frame(FRAME_TITLE);
        Panel buttonPanel = new Panel();
        buttonPanel.add(controller.getApproach());
        buttonPanel.add(controller.getSay());
        buttonPanel.add(controller.getPassed());
        buttonPanel.add(controller.getFailed());
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(painter, BorderLayout.CENTER);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                frame.dispose();
            }
        });
        frame.setVisible(true);
        painter.requestFocus();
    }

    private static void demonstrateAssertions(BridgeSceneInterface bridgeScene) {
        bridgeScene.approach(bridgeScene.getArthur());
        bridgeScene.say(GUARD_QUESTION);
        bridgeScene.say(KNIGHT_ANSWER);
        bridgeScene.passed();
        bridgeScene.approach(bridgeScene.getLancelot());
        bridgeScene.failed();
    }

    private static void demonstrateImpossibleAngle(BridgeSceneInterface bridgeScene) {
        try {
            bridgeScene.getArthur().getLegs().getLeftLine()
                    .setAngle(ILLEGAL_LEFT_LEG_ANGLE);
        } catch (ImpossibleAngle exception) {
            exception.printStackTrace();
        }
    }

    private static void demonstrateAnimations(BridgeSceneInterface bridgeScene) {
        bridgeScene.asynchronousArthur();
        bridgeScene.asynchronousGalahad();
        bridgeScene.asynchronousLancelot();
        bridgeScene.asynchronousLancelot();
        bridgeScene.asynchronousRobin();
        bridgeScene.asynchronousGuard();
        ThreadSupport.sleep(DEMO_PAUSE);

        bridgeScene.lockstepArthur();
        bridgeScene.lockstepGalahad();
        bridgeScene.lockstepLancelot();
        bridgeScene.lockstepRobin();
        bridgeScene.lockstepGuard();
        ThreadSupport.sleep(DEMO_PAUSE);

        bridgeScene.waitingArthur();
        bridgeScene.waitingGalahad();
        bridgeScene.waitingLancelot();
        bridgeScene.waitingRobin();
    }
}
