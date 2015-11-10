package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass(index = true)
public class SphericCoordinate extends AbstractCoordinate{


    static final double DEFAULT_LATITUDE = 0.0;
    static final double DEFAULT_LONGITUDE = 0.0;
    static final double EARTH_RADIUS = 6371.0;

    private double latitude;
    private double longitude;
    private double radius = EARTH_RADIUS;

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double latitude, double longitude){
        checkValidLat(latitude);
        checkValidLng(longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double latitude, double longitude, double radius){
        this(latitude, longitude);

        checkValidRadius(radius);
        this.radius = radius;
    }

    /**
     * @methodtype get
     */
    public double getLongitude() {
        return longitude;
    }
    /**
     * @methodtype set
     */
    public void setLongitude(double longitude) {
        checkValidLng(longitude);
        this.longitude = longitude;
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
    public void setLatitude(double latitude) {
        checkValidLat(latitude);
        this.latitude = latitude;
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
    public void setRadius(double radius){
        this.radius = radius;
    }

    /**
     * Calculate distance to another location on a spherical surface.
     *
     * @param other
     * @return distance in km
     */
    public double getDistance(SphericCoordinate other){
        double lat = Math.toRadians(other.getLatitude() - this.getLatitude());
        double lon = Math.toRadians(other.getLongitude() - this.getLongitude());

        double firstSum = Math.sin(lat / 2) * Math.sin(lat / 2) + Math.cos(Math.toRadians(this.getLatitude()))
                * Math.cos(Math.toRadians(other.getLatitude())) * Math.sin(lon / 2) * Math.sin(lon / 2);
        double secondSum = 2 * Math.atan2(Math.sqrt(firstSum), Math.sqrt(1 - firstSum));
        double realDistanceInKM = radius * secondSum;

        return realDistanceInKM;
    }

    @Override
    public double getCartesianX() {
        return radius *
                Math.cos(Math.toRadians(longitude)) *
                Math.sin(Math.toRadians(latitude));
    }

    @Override
    public double getCartesianY() {
        return radius *
                Math.sin(Math.toRadians(longitude)) *
                Math.sin(Math.toRadians(latitude));
    }

    @Override
    public double getCartesianZ() {
        return radius * Math.cos(Math.toRadians(latitude));
    }

    public double getLatitudinalDistance(SphericCoordinate other){
        checkNotNull(other);
        return Math.abs(getLatitude() - other.getLatitude());
    }

    public double getLongitudinalDistance(SphericCoordinate other){
        checkNotNull(other);
        return Math.abs(getLongitude() - other.getLongitude());
    }

    private void checkValidLat(double lat) throws IllegalArgumentException{
        if (lat < -90 || lat > 90 || Double.isNaN(lat)) {
            throw new IllegalArgumentException();
        }
    }

    private void checkValidLng(double lng) throws IllegalArgumentException{
        if (lng < -180 || lng > 180 || Double.isNaN(lng)) {
            throw new IllegalArgumentException();
        }
    }

    private void checkValidRadius(double r) throws IllegalArgumentException{
        if(r < 0.0){
            throw new IllegalArgumentException();
        }
    }
}
