package grail.compositeShapes.interfaces;

import grail.atomicShapes.interfaces.MovableInterface;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags(Comp301Tags.ANGLE)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"LeftLine", "RightLine"})
@EditablePropertyNames({})
public interface AngleInterface extends LinePairInterface, RotationInterface,
        MovableInterface {
    @Override
    default void move(int changeInX, int changeInY) {
        this.moveAngle(changeInX, changeInY);
    }

    @Visible(false)
    void moveAngle(int changeInX, int changeInY);
}
