package org.wahlzeit.model;


public abstract class AbstractCoordinate implements Coordinate {

    protected final static double DELTA = 0.0000001;

    /**
     * @methodtype query
     */
    @Override
    public double getDistance(Coordinate coordinate) {
        // preconditions
        assertIsValidCoordinate(coordinate);

        double dx = getCartesianX() - coordinate.getCartesianX();
        double dy = getCartesianY() - coordinate.getCartesianY();
        double dz = getCartesianZ() - coordinate.getCartesianZ();
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        // postconditions
        assertIsValidDistance(distance);
        return distance;
    }

    /**
     * @methodtype query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        // preconditions
        assertIsNotNull(coordinate);
        assertIsValidCoordinate(coordinate);

        boolean eqX = isDoubleEqual(getCartesianX(), coordinate.getCartesianX());
        boolean eqY = isDoubleEqual(getCartesianY(), coordinate.getCartesianY());
        boolean eqZ = isDoubleEqual(getCartesianZ(), coordinate.getCartesianZ());

        // postconditions
        // boolean value doesn't need to be checked
        return eqX && eqY && eqZ;
    }

    /**
     * @methodtype helper
     */
    protected static boolean isDoubleEqual(double value, double otherValue){
        return (Math.abs(value - otherValue) < DELTA);
    }

    /**
     * @methodtype assertion
     */
    protected void assertIsNotNull(Object obj) throws IllegalArgumentException{
        if(obj == null){
            throw new IllegalArgumentException();
        }
    }

    /**
     * @methodtype assertion
     */
    protected void assertIsValidCoordinate(Coordinate coordinate) {
        assertIsNotNull(coordinate);
        assertIsValidDouble(coordinate.getCartesianX());
        assertIsValidDouble(coordinate.getCartesianY());
        assertIsValidDouble(coordinate.getCartesianZ());
    }


    /**
     * @methodtype assertion
     */
    protected void assertIsValidDistance(double distance) {
        assertIsValidDouble(distance);
        assertIsNotNegativeDouble(distance);
    }

    /**
     * @methodtype assertion
     */
    protected void assertIsValidDouble(double value) {
        if(Double.isNaN(value)){
            throw new IllegalArgumentException("value needs to be a number");
        }
    }

    /**
     * @methodtype assertion
     */
    protected void assertIsNotNegativeDouble(double value) {
        if(value < 0){
            throw new IllegalArgumentException("value needs to be greater then zero");
        }
    }


}
