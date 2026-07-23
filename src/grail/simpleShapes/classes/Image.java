package grail.simpleShapes.classes;

import java.beans.PropertyChangeEvent;

import grail.compositeShapes.classes.BoundedShape;
import grail.simpleShapes.interfaces.ImageInterface;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
@PropertyNames({"X", "Y", "Width", "Height", "ImageFileName", "PropertyChangeListeners"})
@EditablePropertyNames({"X", "Y", "Width", "Height", "ImageFileName"})
public class Image extends BoundedShape implements ImageInterface {
    
    private String imageFileName;
    
    public Image(int initialX, int initialY, int initialWidth, int initialHeight,
            String initialText, String initialImageFileName) {
        super(initialX, initialY, initialWidth, initialHeight);
        this.imageFileName = initialImageFileName;
    }
    
    @Override
    public String getImageFileName() {
        return imageFileName;
    }
    
    @Override
    public void setImageFileName(String newImageFileName) {
        String oldImageFileName = this.imageFileName;
        this.imageFileName = newImageFileName;
        this.notifyAllListeners(new PropertyChangeEvent(this, "ImageFileName", oldImageFileName, newImageFileName));
    }
}
