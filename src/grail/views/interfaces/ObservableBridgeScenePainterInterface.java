package grail.views.interfaces;

import java.util.List;

import tags301.Comp301Tags;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags(Comp301Tags.OBSERVABLE_BRIDGE_SCENE_PAINTER)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"PaintListeners"})
public interface ObservableBridgeScenePainterInterface {
    List<PaintListener> getPaintListeners();

    void addPaintListener(PaintListener paintListener);
}