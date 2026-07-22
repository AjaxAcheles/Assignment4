package grail.views.classes;

import java.beans.PropertyChangeEvent;

import grail.compositeShapes.interfaces.BridgeSceneInterface;
import grail.views.interfaces.ConsoleSceneViewInterface;
import main.Factory;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.CONSOLE_SCENE_VIEW})
public class ConsoleSceneView implements ConsoleSceneViewInterface {
    public ConsoleSceneView() {
        BridgeSceneInterface bridgeScene = Factory.bridgeSceneFactoryMethod();
        SceneViewSupport.registerScene(bridgeScene, this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        System.out.println(event);
    }
}
