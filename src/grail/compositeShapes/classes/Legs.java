package grail.compositeShapes.classes;

import grail.compositeShapes.interfaces.LegsInterface;
import grail.simpleShapes.classes.RestrictedLine;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({Comp301Tags.LEGS})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"LeftLine", "RightLine"})
@EditablePropertyNames({})
public class Legs extends Angle implements LegsInterface {
    private static final double ANGLE_SIDE_DIVISOR = 2.0;
    private static final double LEFT_MINIMUM_ANGLE = Math.PI / 2;
    private static final double LEFT_MAXIMUM_ANGLE = Math.PI;
    private static final double RIGHT_MINIMUM_ANGLE = 0.0;
    private static final double RIGHT_MAXIMUM_ANGLE = Math.PI / 2;

    public Legs(int initialX, int initialY, double radius,
            double splitAngle, double downDirection) {
        super(new RestrictedLine(initialX, initialY, radius,
                        downDirection + splitAngle / ANGLE_SIDE_DIVISOR,
                        LEFT_MINIMUM_ANGLE, LEFT_MAXIMUM_ANGLE),
                new RestrictedLine(initialX, initialY, radius,
                        downDirection - splitAngle / ANGLE_SIDE_DIVISOR,
                        RIGHT_MINIMUM_ANGLE, RIGHT_MAXIMUM_ANGLE));
    }
}
