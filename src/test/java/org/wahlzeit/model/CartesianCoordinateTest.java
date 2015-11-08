package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        cartesianCoord = new CartesianCoordinate(4762.5137725, 926.2823628,
                4129.1772245);
        cartesianOtherCoord = new CartesianCoordinate(-1688.1891415,
                -3142.7037588, 5278.5482385);

        sphericCoord = new SphericCoordinate(49.599937, 11.006300);
        sphericOtherCoord = new SphericCoordinate(34.052235, -118.243683);
    }

    @Test
    public void testDefaultConstructor() {
        CartesianCoordinate coordinate = new CartesianCoordinate();
        assertEquals(0.0, coordinate.getX(), DELTA);
        assertEquals(0.0, coordinate.getY(), DELTA);
        assertEquals(0.0, coordinate.getZ(), DELTA);
    }

    @Test
    public void testConstructorWithArguments() {
        CartesianCoordinate coordinate = new CartesianCoordinate(7000.0, 250.0,
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
}
