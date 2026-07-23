package grail.compositeShapes.classes;

import grail.compositeShapes.interfaces.AngleInterface;
import grail.exceptions.ImpossibleAngle;
import grail.simpleShapes.classes.RotatingLine;
import grail.simpleShapes.interfaces.LineInterface;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags(Comp301Tags.ANGLE)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"LeftLine", "RightLine"})
@EditablePropertyNames({})
public class Angle implements AngleInterface {
    private static final int DEFAULT_X = 0;
    private static final int DEFAULT_Y = 0;
    private static final double DEFAULT_RADIUS = 50;
    private static final double DEFAULT_SPLIT_ANGLE = Math.PI / 2;
    private static final double DEFAULT_DOWN_DIRECTION = Math.PI / 2;
    private static final double ANGLE_SIDE_DIVISOR = 2.0;

    private final LineInterface leftLine;
    private final LineInterface rightLine;

    public Angle() {
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_RADIUS,
                DEFAULT_SPLIT_ANGLE, DEFAULT_DOWN_DIRECTION);
    }

    public Angle(int initialX, int initialY, double radius,
            double splitAngle, double downDirection) {
        this(new RotatingLine(initialX, initialY, radius,
                        downDirection - splitAngle / ANGLE_SIDE_DIVISOR),
                new RotatingLine(initialX, initialY, radius,
                        downDirection + splitAngle / ANGLE_SIDE_DIVISOR));
    }

    protected Angle(LineInterface initialLeftLine, LineInterface initialRightLine) {
        this.leftLine = initialLeftLine;
        this.rightLine = initialRightLine;
    }

    @Override
    public LineInterface getLeftLine() {
        return this.leftLine;
    }

    @Override
    public LineInterface getRightLine() {
        return this.rightLine;
    }

    @Override
    public void moveAngle(int changeInX, int changeInY) {
        this.leftLine.move(changeInX, changeInY);
        this.rightLine.move(changeInX, changeInY);
    }

    @Override
    public void rotate(int rotationUnits) throws ImpossibleAngle {
        this.leftLine.rotate(rotationUnits);
        this.rightLine.rotate(rotationUnits);
    }
}
