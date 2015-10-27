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

    @Test
    public void testGetterSetter(){
        double lat = 30.3;
        double lng = 40.4;
        coordinate.setLatitude(lat);
        coordinate.setLongitude(lng);

        assertEquals(lat,coordinate.getLatitude(),DELTA);
        assertEquals(lng,coordinate.getLongitude(),DELTA);
    }

    @Test
    public void testLatitudeDistance(){
        Coordinate secondCoordinate = new Coordinate(5.5,7.7);
        assertEquals(4.4,coordinate.getLatitudinalDistance(secondCoordinate),DELTA);

        Coordinate thirdCoordinate = new Coordinate(-3.0,1.1);
        assertEquals(4.1,coordinate.getLatitudinalDistance(thirdCoordinate),DELTA);

    }

    @Test
    public void testLongitudeDistance(){
        Coordinate secondCoordinate = new Coordinate(5.5,7.7);
        assertEquals(5.5, coordinate.getLongitudinalDistance(secondCoordinate),DELTA);

        Coordinate thirdCoordinate = new Coordinate(1.5,1.5);
        assertEquals(0.7, coordinate.getLongitudinalDistance(thirdCoordinate),DELTA);
    }

    @Test
    public void testDistance(){
        coordinate.setLatitude(49.572680);
        coordinate.setLongitude(11.028427);

        Coordinate other = new Coordinate(37.427994,-122.170255);

        assertEquals(9304454.8155896, coordinate.getDistance(other), DELTA);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentsLat(){
        Coordinate c = new Coordinate(90.1,0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentsLng(){
        Coordinate c = new Coordinate(00.0,180.1);
    }
}
