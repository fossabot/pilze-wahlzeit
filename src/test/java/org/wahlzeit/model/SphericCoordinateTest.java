package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test cases for the SphericCoordinate class.
 */
public class SphericCoordinateTest {

    private SphericCoordinate sphericCoord;
    private SphericCoordinate sphericOtherCoord;
    private CartesianCoordinate cartesianCoord;
    private CartesianCoordinate cartesianOtherCoord;

    private final static  double DELTA = 0.000001;

    @Before
    public void setUp(){

        sphericCoord = new SphericCoordinate(49.599937, 11.006300);
        sphericOtherCoord = new SphericCoordinate(34.052235, -118.243683);

        cartesianCoord = new CartesianCoordinate(4762.5137725, 926.2823628,
                4129.1772245);
        cartesianOtherCoord = new CartesianCoordinate(-1688.1891415,
                -3142.7037588, 5278.5482385);
    }

    @Test
    public void testGetterSetter(){
        double lat = 30.3;
        double lng = 40.4;
        sphericCoord.setLatitude(lat);
        sphericCoord.setLongitude(lng);

        assertEquals(lat, sphericCoord.getLatitude(),DELTA);
        assertEquals(lng, sphericCoord.getLongitude(),DELTA);
    }

    @Test
    public void testLatitudeDistance(){

        assertEquals(15.547702, sphericCoord.getLatitudinalDistance(sphericOtherCoord), DELTA);
        assertEquals(15.547702, sphericOtherCoord.getLatitudinalDistance(sphericCoord) , DELTA);

    }

    @Test
    public void testLongitudeDistance(){
        assertEquals(129.249983, sphericCoord.getLongitudinalDistance(sphericOtherCoord),
                DELTA);
        assertEquals(129.249983, sphericOtherCoord.getLongitudinalDistance(sphericCoord),
                DELTA);
    }

    @Test
    public void testDistance(){
        sphericCoord.setLatitude(49.572680);
        sphericCoord.setLongitude(11.028427);

        SphericCoordinate other = new SphericCoordinate(37.427994,-122.170255);

        assertEquals(9304.454815, sphericCoord.getDistance(other), DELTA);
    }

    @Test
    public void testDoCartesianToSpheric(){
        double distanceCartesian = cartesianCoord
                .getDistance(sphericCoord);
        double distanceSpheric = sphericCoord
                .getDistance(cartesianCoord);
        assertEquals(0.0, distanceCartesian, DELTA);
        assertEquals(0.0, distanceSpheric, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLatitudeOutOfHigherBounds() {
        sphericCoord = new SphericCoordinate(90.1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLatitudeOutOfLowerBounds() {
        sphericCoord = new SphericCoordinate(-90.1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongitudeOutOfHigherBounds() {
        sphericCoord = new SphericCoordinate(45, 180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongitudeOutOfLowerBounds() {
        sphericCoord = new SphericCoordinate(45, -180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRadiusOutOfBounds() {
        sphericCoord = new SphericCoordinate(45, 40, -20);
    }

}
