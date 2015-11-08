package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.CoordinateHelper;

@Subclass(index = true)
public class CartesianCoordinate implements Coordinate {

    private double x;
    private double y;
    private double z;

    /**
     * @methodtype constructor
     */
    public CartesianCoordinate() {
        this(0.0, 0.0, 0.0);
    }

    /**
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double getDistance(Coordinate coordinate) {
        CartesianCoordinate cartCoord = transformCoordinateToCartesian(coordinate);

        double dx = x - cartCoord.getX();
        double dy = y - cartCoord.getY();
        double dz = z - cartCoord.getZ();

        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    private CartesianCoordinate transformCoordinateToCartesian(Coordinate coordinate){
        if(coordinate instanceof SphericCoordinate){
            return doSphericToCartesian((SphericCoordinate)coordinate);
        } else if(coordinate instanceof CartesianCoordinate){
            return (CartesianCoordinate)coordinate;
        } else {
            throw new IllegalArgumentException("unknown coordinate type");
        }
    }

    private CartesianCoordinate doSphericToCartesian(SphericCoordinate sphericCoord){
        double lat = Math.toRadians(sphericCoord.getLatitude());
        double lng = Math.toRadians(sphericCoord.getLongitude());
        double radius = sphericCoord.getRadius();

        double x = radius * Math.cos(lng) * Math.sin(lat);
        double y = radius * Math.sin(lng) * Math.sin(lat);
        double z = radius * Math.cos(lat);
        return new CartesianCoordinate(x,y,z);
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        CartesianCoordinate cartCoord = transformCoordinateToCartesian(coordinate);
        boolean eqX = CoordinateHelper.isDoubleEqual(x, cartCoord.getX());
        boolean eqY = CoordinateHelper.isDoubleEqual(y, cartCoord.getY());
        boolean eqZ = CoordinateHelper.isDoubleEqual(z, cartCoord.getZ());

        return eqX && eqY && eqZ;
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     * @methodtype set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype set
     */
    public void setX(double x) {
        this.x = x;
    }
}
