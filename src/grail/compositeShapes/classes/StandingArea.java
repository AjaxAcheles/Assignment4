package grail.compositeShapes.classes;

import grail.compositeShapes.interfaces.AvatarInterface;
import grail.compositeShapes.interfaces.StandingAreaInterface;
import grail.simpleShapes.classes.RotatingLine;
import grail.simpleShapes.interfaces.LineInterface;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Width", "Height", "TopLine", "RightBoundary", "BottomLine", "LeftBoundary", "PropertyChangeListeners"})
@EditablePropertyNames({"X", "Y", "Width", "Height"})
public class StandingArea extends BoundedShape implements StandingAreaInterface {
    private static final double ZERO_ANGLE = 0;
    private static final double DOWN_DIRECTION = Math.PI / 2;
    private static final int CENTER_DIVISOR = 2;

    private final LineInterface topLine;
    private final LineInterface rightBoundary;
    private final LineInterface bottomLine;
    private final LineInterface leftBoundary;

    public StandingArea(int initialX, int initialY, int initialWidth, int initialHeight) {
        super(initialX, initialY, initialWidth, initialHeight);
        this.topLine = new RotatingLine(initialX, initialY, initialWidth, ZERO_ANGLE);
        this.rightBoundary = new RotatingLine(initialX + initialWidth, initialY,
                initialHeight, DOWN_DIRECTION);
        this.bottomLine = new RotatingLine(initialX, initialY + initialHeight,
                initialWidth, ZERO_ANGLE);
        this.leftBoundary = new RotatingLine(initialX, initialY,
                initialHeight, DOWN_DIRECTION);
    }

    @Override
    public LineInterface getTopLine() {
        return this.topLine;
    }

    @Override
    public LineInterface getRightBoundary() {
        return this.rightBoundary;
    }

    @Override
    public LineInterface getBottomLine() {
        return this.bottomLine;
    }

    @Override
    public LineInterface getLeftBoundary() {
        return this.leftBoundary;
    }

    @Override
    @Visible(false)
    public int getCenterX() {
        return this.getX() + this.getWidth() / CENTER_DIVISOR;
    }

    @Override
    @Visible(false)
    public int getCenterY() {
        return this.getY() + this.getHeight() / CENTER_DIVISOR;
    }

    @Override
    public boolean contains(AvatarInterface avatar) {
        int avatarX = avatar.getX();
        int avatarY = avatar.getY();
        return avatarX >= this.getX()
                && avatarX <= this.getX() + this.getWidth()
                && avatarY >= this.getY()
                && avatarY <= this.getY() + this.getHeight();
    }

    @Override
    protected void locationChanged(int changeInX, int changeInY) {
        this.topLine.move(changeInX, changeInY);
        this.rightBoundary.move(changeInX, changeInY);
        this.bottomLine.move(changeInX, changeInY);
        this.leftBoundary.move(changeInX, changeInY);
    }

    @Override
    protected void widthChanged(int oldWidth, int newWidth) {
        this.topLine.setWidth(newWidth);
        this.rightBoundary.setX(this.getX() + newWidth);
        this.bottomLine.setWidth(newWidth);
    }

    @Override
    protected void heightChanged(int oldHeight, int newHeight) {
        this.rightBoundary.setHeight(newHeight);
        this.bottomLine.setY(this.getY() + newHeight);
        this.leftBoundary.setHeight(newHeight);
    }
}
