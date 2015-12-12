package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        sphericCoord = SphericCoordinate.getInstance(49.599937, 11.006300, SphericCoordinate.EARTH_RADIUS);
        sphericOtherCoord = SphericCoordinate.getInstance(34.052235, -118.243683, SphericCoordinate.EARTH_RADIUS);

        cartesianCoord = CartesianCoordinate.getInstance(4762.5137725, 926.2823628,
                4129.1772245);
        cartesianOtherCoord = CartesianCoordinate.getInstance(-1688.1891415,
                -3142.7037588, 5278.5482385);
    }

    @Test
    public void testGetterSetter(){
        double lat = 30.3;
        double lng = 40.4;
        SphericCoordinate sphericNewLat = sphericCoord.setLatitude(lat);
        assertEquals(lat, sphericNewLat.getLatitude(),DELTA);

        SphericCoordinate sphericNewLng = sphericCoord.setLongitude(lng);
        assertEquals(lng, sphericNewLng.getLongitude(),DELTA);
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

        SphericCoordinate other = SphericCoordinate.getInstance(37.427994, -122.170255, SphericCoordinate.EARTH_RADIUS);
        assertEquals(9301.062154, sphericCoord.getDistance(other), DELTA);

        Coordinate coord = SphericCoordinate.getInstance(37.427994, -122.170255, SphericCoordinate.EARTH_RADIUS);
        assertEquals(9301.062154, sphericCoord.getDistance(other), DELTA);
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

    @Test
    public void testGetCartesianX(){
        assertEquals(cartesianCoord.getX(),sphericCoord.getCartesianX(),DELTA);
    }

    @Test
    public void testGetCartesianY(){
        assertEquals(cartesianCoord.getY(),sphericCoord.getCartesianY(),DELTA);
    }

    @Test
    public void testGetCartesianZ(){
        assertEquals(cartesianCoord.getZ(),sphericCoord.getCartesianZ(),DELTA);
    }

    @Test
    public void testIsSame(){
        SphericCoordinate other = SphericCoordinate.getInstance(49.599937, 11.006300, SphericCoordinate.EARTH_RADIUS);
        assertTrue(sphericCoord.isSame(other));
        assertFalse(sphericCoord.isSame(cartesianCoord));
    }

    @Test
    public void testEquals(){
        SphericCoordinate other = SphericCoordinate.getInstance(49.599937, 11.006300, SphericCoordinate.EARTH_RADIUS);
        assertTrue(sphericCoord.equals(other));
        assertTrue(sphericCoord.equals(cartesianCoord));
    }

    @Test
    public void testIsEquals(){
        SphericCoordinate other = SphericCoordinate.getInstance(49.599937, 11.006300, SphericCoordinate.EARTH_RADIUS);
        assertTrue(sphericCoord.isEqual(other));
        assertTrue(sphericCoord.isEqual(cartesianCoord));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLatitudeOutOfHigherBounds() {
        sphericCoord = SphericCoordinate.getInstance(90.1, 0, SphericCoordinate.EARTH_RADIUS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLatitudeOutOfLowerBounds() {
        sphericCoord = SphericCoordinate.getInstance(-90.1, 0,SphericCoordinate.EARTH_RADIUS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongitudeOutOfHigherBounds() {
        sphericCoord = SphericCoordinate.getInstance(45, 180.1, SphericCoordinate.EARTH_RADIUS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongitudeOutOfLowerBounds() {
        sphericCoord = SphericCoordinate.getInstance(45, -180.1, SphericCoordinate.EARTH_RADIUS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRadiusOutOfBounds() {
        sphericCoord = SphericCoordinate.getInstance(45, 40, -20);
    }

}
