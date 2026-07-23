package grail.compositeShapes.interfaces;

import grail.simpleShapes.interfaces.LineInterface;
import util.annotations.Visible;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Width", "Height", "TopLine", "RightBoundary", "BottomLine", "LeftBoundary", "PropertyChangeListeners"})
@EditablePropertyNames({"X", "Y", "Width", "Height"})
public interface StandingAreaInterface extends BoundedShapeInterface {
    LineInterface getTopLine();
    LineInterface getRightBoundary();
    LineInterface getBottomLine();
    LineInterface getLeftBoundary();
    @Visible(false)
    int getCenterX();
    @Visible(false)
    int getCenterY();
    boolean contains(AvatarInterface avatar);
}
