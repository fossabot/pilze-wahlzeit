package org.wahlzeit.model;
import org.wahlzeit.services.ObjectManager;
import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Location {

    // Datastore values
    @Id
    private String idLong = "location";
    @Parent
    Key parent = ObjectManager.applicationRootKey;

    private String name;
    private Coordinate coordinate;

    /**
     * @methodtype constructor
     */
    public Location() {
        this(SphericCoordinate.DEFAULT_LATITUDE, SphericCoordinate.DEFAULT_LONGITUDE);
    }

    /**
     * @methodtype constructor
     */
    public Location(double latitude, double longitude) {
        this(latitude, longitude, "");
    }

    /**
     * @methodtype constructor
     */
    public Location(double latitude, double longitude, String name) {
        this.coordinate = new SphericCoordinate(latitude, longitude);
        this.name = name;
    }

    /**
     * @methodtype get
     */
    public String getName() {
        return name;
    }

    /**
     * @methodtype set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @methodtype set
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}