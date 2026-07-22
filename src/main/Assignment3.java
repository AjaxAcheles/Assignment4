package main;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import bus.uigen.ObjectEditor;
import grail.collections.classes.Table;
import grail.collections.interfaces.TableInterface;
import grail.compositeShapes.interfaces.AvatarInterface;
import grail.compositeShapes.interfaces.BridgeSceneInterface;
import util.misc.ThreadSupport;

public class Assignment3 {
    private static final String FRAME_TITLE = "Bridge Scene";
    private static final int FRAME_WIDTH = 1100;
    private static final int FRAME_HEIGHT = 600;
    private static final int EDITOR_WIDTH = 1050;
    private static final int EDITOR_HEIGHT = 550;
    private static final int ANIMATION_PAUSE = 300;
    private static final int DEMO_MOVE_X = 50;
    private static final int DEMO_MOVE_Y = 0;
    private static final String DEMO_SPEECH = "Assignment 3 demo";
    private static final String NO_SPEECH = "";

    public static void main(String[] args) {
        BridgeSceneInterface bridgeScene = Factory.bridgeSceneFactoryMethod();
        Factory.inheritingBridgeScenePainterFactoryMethod();
        Component painter = (Component) Factory.observableBridgeScenePainterFactoryMethod();
        Factory.delegatingBridgeSceneViewFactoryMethod();

        displayPainter(painter);
        Factory.bridgeSceneControllerFactoryMethod();
        ObjectEditor.edit(bridgeScene).setSize(EDITOR_WIDTH, EDITOR_HEIGHT);

        demoTables(bridgeScene);
        runAnimation(bridgeScene);
    }

    private static void displayPainter(Component painter) {
        Frame frame = new Frame(FRAME_TITLE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(painter);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                frame.dispose();
            }
        });
        frame.setVisible(true);
        painter.requestFocus();
    }

    private static void demoTables(BridgeSceneInterface bridgeScene) {
        demoStringTable();
        demoAvatarTable(bridgeScene);
    }

    private static void demoStringTable() {
        TableInterface<String> table = new Table<String>();
        table.put("greeting", "hello");
        System.out.println("Inserted greeting -> hello");
        table.put("farewell", "goodbye");
        System.out.println("Inserted farewell -> goodbye");
        table.put("greeting", "replacement hello");
        System.out.println("Replaced greeting -> replacement hello");
        System.out.println("Retrieved greeting -> " + table.get("greeting"));
        System.out.println("Retrieved farewell -> " + table.get("farewell"));
    }

    private static void demoAvatarTable(BridgeSceneInterface bridgeScene) {
        TableInterface<AvatarInterface> table = Factory.avatarTableFactoryMethod();
        table.put("DemoArthur", bridgeScene.getArthur());
        System.out.println("Inserted DemoArthur -> " + bridgeScene.getArthur());
        table.put("DemoRobin", bridgeScene.getRobin());
        System.out.println("Inserted DemoRobin -> " + bridgeScene.getRobin());
        table.put("DemoArthur", bridgeScene.getGalahad());
        System.out.println("Replaced DemoArthur -> " + bridgeScene.getGalahad());
        System.out.println("Retrieved DemoArthur -> " + table.get("DemoArthur"));
        System.out.println("Retrieved DemoRobin -> " + table.get("DemoRobin"));
    }

    private static void runAnimation(BridgeSceneInterface bridgeScene) {
        ThreadSupport.sleep(ANIMATION_PAUSE);
        bridgeScene.getGalahad().move(DEMO_MOVE_X, DEMO_MOVE_Y);
        ThreadSupport.sleep(ANIMATION_PAUSE);
        bridgeScene.getGalahad().getStringShape().setText(DEMO_SPEECH);
        ThreadSupport.sleep(ANIMATION_PAUSE);
        bridgeScene.getGalahad().move(-DEMO_MOVE_X, -DEMO_MOVE_Y);
        ThreadSupport.sleep(ANIMATION_PAUSE);
        bridgeScene.getGalahad().getStringShape().setText(NO_SPEECH);
    }
}