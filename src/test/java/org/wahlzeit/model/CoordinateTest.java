package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test cases for the Coordinate class.
 */
public class CoordinateTest {

    private Coordinate coordinate;
    private final static  double DELTA = 0.0000001;

    @Before
    public void setUp(){
        coordinate = new Coordinate(1.1,2.2);
    }

    public void getterSetterTest(){
        double lat = 99.99;
        double lng = 88.88;
        coordinate.setLatitude(lat);
        coordinate.setLongitude(lng);

        assertEquals(lat,coordinate.getLatitude(),DELTA);
        assertEquals(lng,coordinate.getLongitude(),DELTA);
    }

    @Test
    public void latitudeDistanceTest(){
        Coordinate secondCoordinate = new Coordinate(5.5,7.7);
        assertEquals(4.4,coordinate.getLatitudinalDistance(secondCoordinate),DELTA);

        Coordinate thirdCoordinate = new Coordinate(-3.0,1.1);
        assertEquals(4.1,coordinate.getLatitudinalDistance(thirdCoordinate),DELTA);

    }

    @Test
    public void longitudeDistanceTest(){
        Coordinate secondCoordinate = new Coordinate(5.5,7.7);
        assertEquals(5.5, coordinate.getLongitudinalDistance(secondCoordinate),DELTA);

        Coordinate thirdCoordinate = new Coordinate(1.5,1.5);
        assertEquals(0.7, coordinate.getLongitudinalDistance(thirdCoordinate),DELTA);
    }

    @Test
    public void distanceTest(){
        Coordinate otherCoordinate = new Coordinate(10.0,10.0);

        Coordinate distCoord = coordinate.getDistance(otherCoordinate);

        assertEquals(8.9,distCoord.getLatitude(),DELTA);
        assertEquals(7.8,distCoord.getLongitude(),DELTA);
    }
}
