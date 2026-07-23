package grail.atomicShapes.classes;

import grail.atomicShapes.interfaces.PointInterface;
import util.annotations.EditablePropertyNames;
import util.annotations.Explanation;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Location in Java coordinate System.")
@PropertyNames({"X", "Y", "Angle", "Radius", "PropertyChangeListeners"})
@EditablePropertyNames({"X", "Y"})
public class CartesianPoint extends Locatable implements PointInterface {

    public CartesianPoint(int initialX, int initialY) {
        super(initialX, initialY);
    }
    
}
