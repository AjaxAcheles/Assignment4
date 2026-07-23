package grail.compositeShapes.interfaces;

import grail.simpleShapes.interfaces.LineInterface;
import util.annotations.Visible;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Width", "Height", "TopLine", "RightLine", "BottomLine", "LeftLine", "PropertyChangeListeners"})
@EditablePropertyNames({"X", "Y", "Width", "Height"})
public interface StandingAreaInterface extends BoundedShapeInterface, LinePairInterface {
    LineInterface getTopLine();
    LineInterface getBottomLine();
    @Visible(false)
    int getCenterX();
    @Visible(false)
    int getCenterY();
    boolean contains(AvatarInterface avatar);
}
