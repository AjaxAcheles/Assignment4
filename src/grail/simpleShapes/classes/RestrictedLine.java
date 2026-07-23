package grail.simpleShapes.classes;

import grail.simpleShapes.interfaces.RestrictedLineInterface;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({Comp301Tags.RESTRICTED_LINE})
@StructurePattern(StructurePatternNames.LINE_PATTERN)
@PropertyNames({"X", "Y", "Width", "Height", "Radius", "Angle", "End", "PropertyChangeListeners"})
@EditablePropertyNames({"X", "Y", "Width", "Height", "Radius", "Angle"})
public class RestrictedLine extends RotatingLine implements RestrictedLineInterface {
    public RestrictedLine(int startX, int startY, double radius,
            double initialAngle, double lowerAngleBound, double upperAngleBound) {
        super(startX, startY, radius, initialAngle,
                lowerAngleBound, upperAngleBound);
    }
}
