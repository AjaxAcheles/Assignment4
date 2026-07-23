package grail.simpleShapes.interfaces;

import grail.compositeShapes.interfaces.BoundedShapeInterface;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
@PropertyNames({"X", "Y", "Width", "Height", "ImageFileName", "PropertyChangeListeners"})
@EditablePropertyNames({"X", "Y", "Width", "Height", "ImageFileName"})
public interface ImageInterface extends BoundedShapeInterface {
    String getImageFileName();
    void setImageFileName(String newImageFileName);
}
