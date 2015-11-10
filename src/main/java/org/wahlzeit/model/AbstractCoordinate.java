package org.wahlzeit.model;


public abstract class AbstractCoordinate implements Coordinate {

    protected final static double DELTA = 0.0000001;

    @Override
    public double getDistance(Coordinate coordinate) {
        checkNotNull(coordinate);
        double dx = getCartesianX() - coordinate.getCartesianX();
        double dy = getCartesianY() - coordinate.getCartesianY();
        double dz = getCartesianZ() - coordinate.getCartesianZ();

        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        checkNotNull(coordinate);
        boolean eqX = isDoubleEqual(getCartesianX(), coordinate.getCartesianX());
        boolean eqY = isDoubleEqual(getCartesianY(), coordinate.getCartesianY());
        boolean eqZ = isDoubleEqual(getCartesianZ(), coordinate.getCartesianZ());

        return eqX && eqY && eqZ;
    }

    protected static boolean isDoubleEqual(double value, double otherValue){
        return (Math.abs(value - otherValue) < DELTA);
    }

    protected void checkNotNull(Object obj) throws IllegalArgumentException{
        if(obj == null){
            throw new IllegalArgumentException();
        }
    }
}
