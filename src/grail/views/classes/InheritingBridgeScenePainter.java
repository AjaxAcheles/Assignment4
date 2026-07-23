package grail.views.classes;

import java.awt.Component;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;

import grail.compositeShapes.interfaces.BridgeSceneInterface;
import grail.views.interfaces.InheritingBridgeScenePainterInterface;
import main.Factory;
import tags301.Comp301Tags;
import util.annotations.Tags;

@SuppressWarnings("serial")
@Tags({Comp301Tags.INHERITING_BRIDGE_SCENE_PAINTER})
public class InheritingBridgeScenePainter extends Component
        implements InheritingBridgeScenePainterInterface {
    private final BridgeSceneInterface bridgeScene;

    public InheritingBridgeScenePainter() {
        this.bridgeScene = Factory.bridgeSceneFactoryMethod();
        this.setFocusable(true);
        SceneViewSupport.registerScene(this.bridgeScene, this);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        SceneViewSupport.drawScene(graphics, this.bridgeScene, this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        repaint();
    }

}
