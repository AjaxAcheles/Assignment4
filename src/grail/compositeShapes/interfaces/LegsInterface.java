package grail.compositeShapes.interfaces;

import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags(Comp301Tags.LEGS)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"LeftLine", "RightLine"})
@EditablePropertyNames({})
public interface LegsInterface extends AngleInterface {
}
