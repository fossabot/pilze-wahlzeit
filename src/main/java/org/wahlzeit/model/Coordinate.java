package org.wahlzeit.model;

public class Coordinate {

    private double latitude;
    private double longitude;

    public Coordinate(){
        this(0.0,0.0);
    }

    /**
     * Represent a location by latitude and longitude values.
     */
    public Coordinate(double latitude, double longitude){
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
        this.latitude = latitude;
    }

    public Coordinate getDistance(Coordinate other){
        double lat = getLatitudinalDistance(other);
        double lng = getLongitudinalDistance(other);
        return new Coordinate(lat,lng);
    }

    public double getLatitudinalDistance(Coordinate other){
        return Math.abs(getLatitude() - other.getLatitude());
    }

    public double getLongitudinalDistance(Coordinate other){
        return Math.abs(getLongitude() - other.getLongitude());
    }
}
