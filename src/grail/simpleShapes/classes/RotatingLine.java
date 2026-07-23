package grail.simpleShapes.classes;

import java.beans.PropertyChangeEvent;

import grail.atomicShapes.classes.PolarPoint;
import grail.atomicShapes.interfaces.PointInterface;
import grail.compositeShapes.classes.BoundedShape;
import grail.exceptions.ImpossibleAngle;
import grail.simpleShapes.interfaces.LineInterface;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@Tags(Comp301Tags.ROTATING_LINE)
@PropertyNames({"X", "Y", "Width", "Height", "Radius", "Angle", "End", "PropertyChangeListeners"})
@EditablePropertyNames({"X", "Y", "Width", "Height", "Radius", "Angle"})
public class RotatingLine extends BoundedShape implements LineInterface {
    
    private static final int DEFAULT_X = 100;
    private static final int DEFAULT_Y = 100;
    private static final double DEFAULT_RADIUS = 50.0;
    private static final double DEFAULT_ANGLE = Math.PI / 4;

    private final PointInterface endPoint;
    private final double minimumAngle;
    private final double maximumAngle;
    private double angleValue;

    public RotatingLine() {
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_RADIUS, DEFAULT_ANGLE);
    }

    
    public RotatingLine(int startX, int startY, double radius, double initialAngle) {
        this(startX, startY, radius, initialAngle,
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    protected RotatingLine(int startX, int startY, double radius,
            double initialAngle, double lowerAngleBound,
            double upperAngleBound) {
        super(startX, startY,
                (int) Math.round(radius * Math.cos(initialAngle)),
                (int) Math.round(radius * Math.sin(initialAngle)));
        this.angleValue = initialAngle;
        this.endPoint = new PolarPoint(radius, initialAngle);
        this.minimumAngle = lowerAngleBound;
        this.maximumAngle = upperAngleBound;
    }

    @Override
    public double getRadius() {
        return endPoint.getRadius();
    }
    
    @Override
    public double getAngle() {
        return this.angleValue;
    }

    @Override
    public void setRadius(double newRadius) {
        double oldRadius = this.getRadius();
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setEndPoint(newRadius, this.angleValue);
        this.setWidthWithoutNotification(this.endPoint.getX());
        this.setHeightWithoutNotification(this.endPoint.getY());
        this.notifyAllListeners(new PropertyChangeEvent(this, "Radius", oldRadius, newRadius));
        this.notifyAllListeners(new PropertyChangeEvent(this, "Width", oldWidth, this.getWidth()));
        this.notifyAllListeners(new PropertyChangeEvent(this, "Height", oldHeight, this.getHeight()));
    }

    @Override
    public PointInterface getEnd() {
        return this.endPoint;
    }

    @Override
    public void setAngle(double newAngle) throws ImpossibleAngle {
        if (newAngle < this.minimumAngle
                || newAngle > this.maximumAngle) {
            throw new ImpossibleAngle("Illegal angle " + newAngle
                    + "; legal range is [" + this.minimumAngle
                    + ", " + this.maximumAngle + "]");
        }
        double oldAngle = this.angleValue;
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.angleValue = newAngle;
        this.setEndPoint(this.getRadius(), newAngle);
        this.setWidthWithoutNotification(this.endPoint.getX());
        this.setHeightWithoutNotification(this.endPoint.getY());
        this.notifyAllListeners(new PropertyChangeEvent(this, "Angle", oldAngle, newAngle));
        this.notifyAllListeners(new PropertyChangeEvent(this, "Width", oldWidth, this.getWidth()));
        this.notifyAllListeners(new PropertyChangeEvent(this, "Height", oldHeight, this.getHeight()));
    }

    @Override
    public void rotate(int units) throws ImpossibleAngle {
        this.setAngle(this.angleValue + Math.toRadians(units));
    }

    @Override
    public void moveLine(int moveX, int moveY) {
        this.setX(this.getX() + moveX);
        this.setY(this.getY() + moveY);
    }
    
    @Override
    public void scale(double scaleMultiplier) {
        this.setRadius(this.getRadius() * scaleMultiplier);
    }

    private void setEndPoint(double radius, double angle) {
        this.endPoint.setX((int) Math.round(radius * Math.cos(angle)));
        this.endPoint.setY((int) Math.round(radius * Math.sin(angle)));
    }

    @Override
    protected void widthChanged(int oldWidth, int newWidth) {
        double oldRadius = this.getRadius();
        double oldAngle = this.getAngle();
        this.endPoint.setX(newWidth);
        this.angleValue = this.endPoint.getAngle();
        this.notifyAllListeners(new PropertyChangeEvent(this, "Radius", oldRadius, this.getRadius()));
        this.notifyAllListeners(new PropertyChangeEvent(this, "Angle", oldAngle, this.angleValue));
    }

    @Override
    protected void heightChanged(int oldHeight, int newHeight) {
        double oldRadius = this.getRadius();
        double oldAngle = this.getAngle();
        this.endPoint.setY(newHeight);
        this.angleValue = this.endPoint.getAngle();
        this.notifyAllListeners(new PropertyChangeEvent(this, "Radius", oldRadius, this.getRadius()));
        this.notifyAllListeners(new PropertyChangeEvent(this, "Angle", oldAngle, this.angleValue));
    }
}
