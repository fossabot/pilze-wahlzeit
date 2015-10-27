package org.wahlzeit.model;

public class Coordinate {

    private double latitude;
    private double longitude;

    /**
     * Represent a location by latitude and longitude values.
     */
    public Coordinate(double latitude, double longitude){
        checkValidLat(latitude);
        checkValidLng(longitude);
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getDistance(Coordinate other){
        int radius = 6371;

        double lat = Math.toRadians(other.getLatitude() - this.getLatitude());
        double lon = Math.toRadians(other.getLongitude() - this.getLongitude());

        double firstSum = Math.sin(lat / 2) * Math.sin(lat / 2) + Math.cos(Math.toRadians(this.getLatitude()))
                * Math.cos(Math.toRadians(other.getLatitude())) * Math.sin(lon / 2) * Math.sin(lon / 2);
        double secondSum = 2 * Math.atan2(Math.sqrt(firstSum), Math.sqrt(1 - firstSum));
        double realDistanceInM = radius * secondSum * 1000;

        return realDistanceInM;
    }

    public double getLatitudinalDistance(Coordinate other){
        checkNotNull(other);
        return Math.abs(getLatitude() - other.getLatitude());
    }

    public double getLongitudinalDistance(Coordinate other){
        checkNotNull(other);
        return Math.abs(getLongitude() - other.getLongitude());
    }

    private void checkNotNull(Coordinate c) throws IllegalArgumentException{
        if(c == null){
            throw new IllegalArgumentException();
        }
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

}
