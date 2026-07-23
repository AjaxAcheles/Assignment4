package grail.views.classes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import grail.views.interfaces.ObservableBridgeScenePainterInterface;
import grail.views.interfaces.PaintListener;
import tags301.Comp301Tags;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@SuppressWarnings("serial")
@Tags(Comp301Tags.OBSERVABLE_BRIDGE_SCENE_PAINTER)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"PaintListeners"})
public class ObservableBridgeScenePainter extends Component
        implements ObservableBridgeScenePainterInterface {
    private final List<PaintListener> paintListeners;

    public ObservableBridgeScenePainter() {
        this.paintListeners = new ArrayList<PaintListener>();
        this.setFocusable(true);
    }

    @Override
    public List<PaintListener> getPaintListeners() {
        return this.paintListeners;
    }

    @Override
    public void addPaintListener(PaintListener paintListener) {
        this.paintListeners.add(paintListener);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D drawingGraphics = (Graphics2D) graphics;
        int listenerIndex = 0;
        while (listenerIndex < this.paintListeners.size()) {
            PaintListener paintListener = this.paintListeners.get(listenerIndex);
            paintListener.paint(drawingGraphics);
            listenerIndex++;
        }
    }
}
