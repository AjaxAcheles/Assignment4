package grail.compositeShapes.interfaces;

import grail.exceptions.ImpossibleAngle;

public interface RotationInterface {
    void rotate(int rotationUnits) throws ImpossibleAngle;
}
