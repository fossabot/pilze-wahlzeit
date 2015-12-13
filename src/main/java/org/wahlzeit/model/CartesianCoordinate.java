package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

import java.util.HashMap;

@Subclass(index = true)
public class CartesianCoordinate extends AbstractCoordinate {

    private static final HashMap<String, CartesianCoordinate> instanceMap = new HashMap<String, CartesianCoordinate>();

    private double x;
    private double y;
    private double z;

    /**
     * @methodtype constructor
     */
    private CartesianCoordinate() {
        this(0.0, 0.0, 0.0);
    }

    /**
     * @methodtype constructor
     */
    private CartesianCoordinate(double x, double y, double z){
        // preconditions
        assertIsValidDouble(x);
        assertIsValidDouble(y);
        assertIsValidDouble(z);

        this.x = x;
        this.y = y;
        this.z = z;

        // postconditions
        assertClassInvariants();
    }

    /**
     * @methodtype helper
     */
    public static CartesianCoordinate getInstance(double cartesianX, double cartesianY, double cartesianZ){
        String key = cartesianX+";"+cartesianY+";"+cartesianZ;
        CartesianCoordinate result = instanceMap.get(key);

        if (result == null) {
            synchronized (instanceMap) {
                result = instanceMap.get(key);;
                if (result == null) {
                    result = new CartesianCoordinate(cartesianX, cartesianY, cartesianZ);
                    instanceMap.put(key, result);
                }
            }
        }
        return result;
    };


    /**
     * @methodtype query
     */
    @Override
    public double getCartesianX() {
        return getX();
    }

    /**
     * @methodtype query
     */
    @Override
    public double getCartesianY() {
        return getY();
    }

    /**
     * @methodtype query
     */
    @Override
    public double getCartesianZ() {
        return getZ();
    }

    /**
     * @methodtype assertion
     */
    protected void assertClassInvariants() {
        assertIsValidDouble(x);
        assertIsValidDouble(y);
        assertIsValidDouble(z);
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
    public CartesianCoordinate setZ(double z) {
        assertIsValidDouble(z);

        CartesianCoordinate result = getInstance(getX(),getY(),z);

        //this.z = z;
        assertIsNotNull(result);
        return result;
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
    public CartesianCoordinate setY(double y) {
        assertIsValidDouble(y);

        CartesianCoordinate result = getInstance(getX(),y,getZ());

        assertIsNotNull(result);
        return result;
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
    public CartesianCoordinate setX(double x) {
        assertIsValidDouble(z);

        CartesianCoordinate result = getInstance(x,getY(),getZ());

        //this.z = z;
        assertIsNotNull(result);
        return result;
    }
}
