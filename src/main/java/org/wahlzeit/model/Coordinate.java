package org.wahlzeit.model;

public interface Coordinate {

    double getDistance(Coordinate coordinate);
    boolean isEqual(Coordinate coordinate);
}
