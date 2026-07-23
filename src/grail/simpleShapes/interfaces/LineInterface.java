package grail.simpleShapes.interfaces;
import grail.atomicShapes.interfaces.PointInterface;
import grail.atomicShapes.interfaces.MovableInterface;
import grail.compositeShapes.interfaces.BoundedShapeInterface;
import grail.compositeShapes.interfaces.RotationInterface;
import grail.compositeShapes.interfaces.ScalingInterface;
import grail.exceptions.ImpossibleAngle;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags(Comp301Tags.ROTATING_LINE)
@StructurePattern(StructurePatternNames.LINE_PATTERN)
@PropertyNames({"X", "Y", "Width", "Height", "Radius", "Angle", "End", "PropertyChangeListeners"})
@EditablePropertyNames({"X", "Y", "Width", "Height", "Radius", "Angle"})
public interface LineInterface extends BoundedShapeInterface,
        RotationInterface, ScalingInterface, MovableInterface {

    PointInterface getEnd();
    void setRadius(double radius);
    void setAngle(double angle) throws ImpossibleAngle;
    
    @Override
    default void move(int moveX, int moveY) {
        this.moveLine(moveX, moveY);
    }

    @Visible(false)
    void moveLine(int moveX, int moveY);
}
