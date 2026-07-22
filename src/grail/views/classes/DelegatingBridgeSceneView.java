package grail.views.classes;

import java.awt.Graphics2D;

import grail.compositeShapes.interfaces.BridgeSceneInterface;
import grail.views.interfaces.DelegatingBridgeSceneViewInterface;
import grail.views.interfaces.ObservableBridgeScenePainterInterface;
import grail.views.interfaces.PaintListener;
import main.Factory;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags(Comp301Tags.DELEGATING_BRIDGE_SCENE_VIEW)
public class DelegatingBridgeSceneView implements DelegatingBridgeSceneViewInterface {
    private final BridgeSceneInterface bridgeScene;
    private final ObservableBridgeScenePainterInterface observablePainter;

    public DelegatingBridgeSceneView() {
        this.bridgeScene = Factory.bridgeSceneFactoryMethod();
        this.observablePainter = Factory.observableBridgeScenePainterFactoryMethod();
        this.observablePainter.addPaintListener(this);
        SceneViewSupport.registerScene(this.bridgeScene, this);
    }

    @Override
    public void paint(Graphics2D graphics) {
        SceneViewSupport.drawScene(graphics, this.bridgeScene,
                (ObservableBridgeScenePainter) this.observablePainter);
    }

    @Override
    public void repaint() {
        ((ObservableBridgeScenePainter) this.observablePainter).repaint();
    }
}