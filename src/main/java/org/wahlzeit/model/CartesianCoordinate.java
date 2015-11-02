package org.wahlzeit.model;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import org.wahlzeit.CoordinateHelper;
import org.wahlzeit.services.ObjectManager;

@Entity
public class CartesianCoordinate implements Coordinate {
    @Id
    private String idLong = "cartesianCoordinate";
    @Parent
    Key parent = ObjectManager.applicationRootKey;

    private double x;
    private double y;
    private double z;

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
        double lat = sphericCoord.getLatitude();
        double lng = sphericCoord.getLongitude();
        double r = sphericCoord.getRadius();

        double x = r * Math.cos(lat) * Math.cos(lng);
        double y = r * Math.cos(lat) * Math.sin(lng);
        double z = r * Math.cos(lat);
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
