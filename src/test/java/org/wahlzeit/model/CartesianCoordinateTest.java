package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the CartesianCoordinate class.
 */
public class CartesianCoordinateTest {

    private final static  double DELTA = 0.000001;

    private CartesianCoordinate cartesianCoord;
    private CartesianCoordinate cartesianOtherCoord;

    private SphericCoordinate sphericOtherCoord;
    private SphericCoordinate sphericCoord;

    private static final double otherRadius = 7712.928747957382;

    @Before
    public void setUp() {
        cartesianCoord = CartesianCoordinate.getInstance(4762.5137725, 926.2823628,
                4129.1772245);
        cartesianOtherCoord = CartesianCoordinate.getInstance(-1688.1891415,
                -3142.7037588, 5278.5482385);

        sphericCoord = SphericCoordinate.getInstance(49.599937, 11.006300, SphericCoordinate.EARTH_RADIUS);
        sphericOtherCoord = SphericCoordinate.getInstance(34.052235, -118.243683, SphericCoordinate.EARTH_RADIUS);
    }

    @Test
    public void testConstructorWithArguments() {
        CartesianCoordinate coordinate = CartesianCoordinate.getInstance(7000.0, 250.0,
                3.7);
        assertEquals(7000.0, coordinate.getX(), DELTA);
        assertEquals(250.0, coordinate.getY(), DELTA);
        assertEquals(3.7, coordinate.getZ(), DELTA);
    }

    @Test
    public void testCartesianDistance() {
        double distance = cartesianCoord
                .getDistance(cartesianOtherCoord);
        double distanceAnother = cartesianOtherCoord
                .getDistance(cartesianCoord);

        assertEquals(otherRadius,
                distance, DELTA);
        assertEquals(otherRadius,
                distanceAnother, DELTA);
    }



    @Test
    public void testDistanceWithConversion() {
        double distance = cartesianCoord
                .getDistance(sphericOtherCoord);
        double distanceAnother = cartesianOtherCoord
                .getDistance(sphericCoord);
        assertEquals(otherRadius,
                distance, DELTA);
        assertEquals(otherRadius,
                distanceAnother, DELTA);
    }

    @Test
      public void testCartesianConversion() {
        double distanceCartToSpheric = cartesianCoord
                .getDistance(sphericCoord);
        double distanceSphericToCart = sphericCoord
                .getDistance(cartesianCoord);
        assertEquals(0.0, distanceCartToSpheric, DELTA);
        assertEquals(0.0, distanceSphericToCart, DELTA);
    }

    @Test
    public void testIsSame(){
        CartesianCoordinate other = CartesianCoordinate.getInstance(4762.5137725, 926.2823628,
                4129.1772245);
        assertTrue(cartesianCoord.isSame(other));
        assertFalse(sphericCoord.isSame(cartesianCoord));
    }

    @Test
    public void testEquals(){
        CartesianCoordinate other = CartesianCoordinate.getInstance(4762.5137725, 926.2823628,
                4129.1772245);
        assertTrue(cartesianCoord.equals(other));
        assertTrue(sphericCoord.equals(cartesianCoord));
    }

    @Test
    public void testIsEquals(){
        CartesianCoordinate other = CartesianCoordinate.getInstance(4762.5137725, 926.2823628,
                4129.1772245);
        assertTrue(cartesianCoord.isEqual(other));
        assertTrue(sphericCoord.isEqual(cartesianCoord));
    }

    @Test
    public void testGetCartesianX(){
        assertEquals(cartesianCoord.getX(),cartesianCoord.getCartesianX(),DELTA);
    }

    @Test
    public void testGetCartesianY(){
        assertEquals(cartesianCoord.getY(),cartesianCoord.getCartesianY(),DELTA);
    }

    @Test
    public void testGetCartesianZ(){
        assertEquals(cartesianCoord.getZ(),cartesianCoord.getCartesianZ(),DELTA);
    }
}
