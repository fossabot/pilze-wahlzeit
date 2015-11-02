package org.wahlzeit;


public class CoordinateHelper {

    public final static double DELTA = 0.0000001;

    public static boolean isDoubleEqual(double value, double otherValue){
        return (Math.abs(value - otherValue) < DELTA);
    }
}
