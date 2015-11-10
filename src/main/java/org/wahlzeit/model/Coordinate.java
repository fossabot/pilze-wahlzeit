package org.wahlzeit.model;

public interface Coordinate {

    /**
     * @methodtype query
     */
    double getDistance(Coordinate coordinate);

    /**
     * @methodtype comparison
     */
    boolean isEqual(Coordinate coordinate);

    /**
     * @methodtype query
     */
    double getCartesianX();

    /**
     * @methodtype query
     */
    double getCartesianY();

    /**
     * @methodtype query
     */
    double getCartesianZ();

}
