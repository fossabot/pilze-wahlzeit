package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

import java.util.HashMap;

@Subclass(index = true)
public class SphericCoordinate extends AbstractCoordinate{

    private static final HashMap<String, SphericCoordinate> instanceMap = new HashMap<String, SphericCoordinate>();


    static final double DEFAULT_LATITUDE = 0.0;
    static final double DEFAULT_LONGITUDE = 0.0;
    static final double EARTH_RADIUS = 6371.0;

    private final double latitude;
    private final double longitude;
    private final double radius;


    /**
     * @methodtype constructor
     */
    private SphericCoordinate(double latitude, double longitude, double radius){
        // preconditions
        assertIsValidLatitude(latitude);
        assertIsValidLongitude(longitude);
        assertIsValidRadius(radius);

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;

        // postconditions
        assertClassInvariants();
    }

    /**
     * @methodtype helper
     */
    public static SphericCoordinate getInstance(double lat, double lng, double r){
        String key = lat+";"+lng+";"+r;
        SphericCoordinate result = instanceMap.get(key);

        if (result == null) {
            synchronized (instanceMap) {
                result = instanceMap.get(key);;
                if (result == null) {
                    result = new SphericCoordinate(lat, lng, r);
                    instanceMap.put(key, result);
                }
            }
        }
        return result;
    };

    /**
     * @methodtype get
     */
    public double getLongitude() {
        return longitude;
    }
    /**
     * @methodtype set
     */
    public SphericCoordinate setLongitude(double longitude) {
        // preconditions
        assertIsValidLongitude(longitude);

        SphericCoordinate result = SphericCoordinate.getInstance(getLatitude(),longitude,getRadius());

        // postconditions
        assertClassInvariants();
        assertIsValidCoordinate(result);
        return result;
    }

    /**
     * @methodtype get
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @methodtype set
     */
    public SphericCoordinate setLatitude(double latitude) {
        // preconditions
        assertIsValidLatitude(latitude);

        SphericCoordinate result = SphericCoordinate.getInstance(latitude,getLongitude(),getRadius());

        // postconditions
        assertClassInvariants();
        assertIsValidCoordinate(result);
        return result;
    }

    /**
     * @methodtype get
     */
    public double getRadius(){
        return radius;
    }

    /**
     * @methodtype set
     */
    public SphericCoordinate setRadius(double radius){
        // preconditions
        assertIsValidRadius(radius);

        SphericCoordinate result = SphericCoordinate.getInstance(getLatitude(),getLongitude(),radius);

        // postconditions
        assertIsValidCoordinate(result);
        return result;
    }

    /**
     * Calculate distance to another location on a spherical surface.
     *
     * @param other
     * @return distance in km
     */
    public double getDistance(SphericCoordinate other){
        // preconditions
        assertIsNotNull(other);

        double lat = Math.toRadians(other.getLatitude() - this.getLatitude());
        double lon = Math.toRadians(other.getLongitude() - this.getLongitude());

        double firstSum = Math.sin(lat / 2) * Math.sin(lat / 2) + Math.cos(Math.toRadians(this.getLatitude()))
                * Math.cos(Math.toRadians(other.getLatitude())) * Math.sin(lon / 2) * Math.sin(lon / 2);
        double secondSum = 2 * Math.atan2(Math.sqrt(firstSum), Math.sqrt(1 - firstSum));
        double realDistanceInKM = radius * secondSum;

        // postconditions
        assertIsValidDistance(realDistanceInKM);
        return realDistanceInKM;
    }

    /**
     * @methodtype query
     */
    @Override
    public double getCartesianX() {
        double cartX = radius *
                Math.cos(Math.toRadians(longitude)) *
                Math.sin(Math.toRadians(latitude));

        // postconditions
        assertIsValidDouble(cartX);
        return cartX;
    }

    /**
     * @methodtype query
     */
    @Override
    public double getCartesianY() {
        double cartY = radius *
                Math.sin(Math.toRadians(longitude)) *
                Math.sin(Math.toRadians(latitude));

        // postconditions
        assertIsValidDouble(cartY);
        return cartY;
    }

    /**
     * @methodtype query
     */
    @Override
    public double getCartesianZ() {
        double cartZ = radius * Math.cos(Math.toRadians(latitude));

        // postconditions
        assertIsValidDouble(cartZ);
        return cartZ;
    }

    /**
     * @methodtype query
     */
    public double getLatitudinalDistance(SphericCoordinate other){
        // preconditions
        assertIsNotNull(other);

        double distance = Math.abs(getLatitude() - other.getLatitude());

        // postconditions
        assertIsValidDistance(distance);
        return distance;
    }

    /**
     * @methodtype query
     */
    public double getLongitudinalDistance(SphericCoordinate other){
        // preconditions
        assertIsNotNull(other);

        double distance = Math.abs(getLongitude() - other.getLongitude());

        // postconditions
        assertIsValidDistance(distance);
        return distance;
    }


    /**
     * @methodtype assertion
     */
    protected void assertClassInvariants() {
        assertIsValidDouble(latitude);
        assertIsValidDouble(longitude);
        assertIsValidDouble(radius);
    }

    /**
     * @methodtype assertion
     */
    private void assertIsValidLatitude(double lat) throws IllegalArgumentException{
        assertIsValidDouble(lat);
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @methodtype assertion
     */
    private void assertIsValidLongitude(double lng) throws IllegalArgumentException{
        assertIsValidDouble(lng);
        if (lng < -180 || lng > 180) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @methodtype assertion
     */
    private void assertIsValidRadius(double r) throws IllegalArgumentException{
        assertIsValidDouble(r);
        assertIsNotNegativeDouble(r);
    }
}
