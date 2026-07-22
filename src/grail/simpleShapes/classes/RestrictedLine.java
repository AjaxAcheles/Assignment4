package grail.simpleShapes.classes;

import grail.exceptions.ImpossibleAngle;
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
    private final double minimumAngle;
    private final double maximumAngle;

    public RestrictedLine(int startX, int startY, double radius,
            double initialAngle, double lowerAngleBound, double upperAngleBound) {
        super(startX, startY, radius, initialAngle);
        this.minimumAngle = lowerAngleBound;
        this.maximumAngle = upperAngleBound;
    }

    @Override
    public void setAngle(double newAngle) throws ImpossibleAngle {
        if (newAngle < this.minimumAngle || newAngle > this.maximumAngle) {
            throw new ImpossibleAngle("Illegal angle " + newAngle
                    + "; legal range is [" + this.minimumAngle
                    + ", " + this.maximumAngle + "]");
        }
        super.setAngle(newAngle);
    }
}
