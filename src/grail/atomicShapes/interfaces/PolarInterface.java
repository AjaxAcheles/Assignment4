package grail.atomicShapes.interfaces;

import grail.exceptions.ImpossibleAngle;

public interface PolarInterface {
    double getRadius();
    double getAngle();
    void setRadius(double radius);
    void setAngle(double angle) throws ImpossibleAngle;
}
