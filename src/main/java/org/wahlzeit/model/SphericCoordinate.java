package org.wahlzeit.model;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import org.wahlzeit.CoordinateHelper;
import org.wahlzeit.services.ObjectManager;

@Entity
public class SphericCoordinate implements Coordinate{

    @Id
    private String idLong = "sphericCoordinate";
    @Parent
    Key parent = ObjectManager.applicationRootKey;

    static final double DEFAULT_LATITUDE = 0.0;
    static final double DEFAULT_LONGITUDE = 0.0;

    private double latitude;
    private double longitude;
    private double radius = 6371.0; // km

    /**
     * Represent a location by latitude and longitude values.
     */
    public SphericCoordinate(double latitude, double longitude){
        checkValidLat(latitude);
        checkValidLng(longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public SphericCoordinate(double latitude, double longitude, double radius){
        this(latitude, longitude);
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

    @Override
    public double getDistance(Coordinate other){
        SphericCoordinate coordinate = transformCoordinateToSpheric(other);

        double lat = Math.toRadians(coordinate.getLatitude() - this.getLatitude());
        double lon = Math.toRadians(coordinate.getLongitude() - this.getLongitude());

        double firstSum = Math.sin(lat / 2) * Math.sin(lat / 2) + Math.cos(Math.toRadians(this.getLatitude()))
                * Math.cos(Math.toRadians(coordinate.getLatitude())) * Math.sin(lon / 2) * Math.sin(lon / 2);
        double secondSum = 2 * Math.atan2(Math.sqrt(firstSum), Math.sqrt(1 - firstSum));
        double realDistanceInKM = radius * secondSum;

        return realDistanceInKM;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        SphericCoordinate sphericCoord = transformCoordinateToSpheric(coordinate);
        boolean eqLat = CoordinateHelper.isDoubleEqual(latitude,sphericCoord.getLatitude());
        boolean eqLng = CoordinateHelper.isDoubleEqual(longitude,sphericCoord.getLongitude());
        boolean eqR   = CoordinateHelper.isDoubleEqual(radius,sphericCoord.getRadius());

        return  eqLat && eqLng && eqR;
    }

    private SphericCoordinate transformCoordinateToSpheric(Coordinate coordinate){
        if(coordinate instanceof SphericCoordinate){
            return (SphericCoordinate)coordinate;
        } else if(coordinate instanceof CartesianCoordinate){
            return doCartesianToSpheric((CartesianCoordinate)coordinate);
        } else {
            throw new IllegalArgumentException("unknown coordinate type");
        }
    }

    private SphericCoordinate doCartesianToSpheric(CartesianCoordinate cartCoord){
        double x = cartCoord.getX();
        double y = cartCoord.getY();
        double z = cartCoord.getZ();

        double r = Math.sqrt(x*x + y*y + z*z);

        double lat = Math.atan2(z, Math.sqrt(x * x + y * y));
        double lng = Math.atan2(y, x);
        return new SphericCoordinate(lat,lng,r);
    }

    public double getLatitudinalDistance(SphericCoordinate other){
        checkNotNull(other);
        return Math.abs(getLatitude() - other.getLatitude());
    }

    public double getLongitudinalDistance(SphericCoordinate other){
        checkNotNull(other);
        return Math.abs(getLongitude() - other.getLongitude());
    }

    private void checkNotNull(SphericCoordinate c) throws IllegalArgumentException{
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
