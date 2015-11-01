package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class MushroomPhoto extends Photo{


    private String latinName;
    private boolean eatable;

    /**
     * @methodtype constructor
     */
    public MushroomPhoto(){
        super();
    }

    /**
     * @methodtype constructor
     */
    public MushroomPhoto(PhotoId myId){
        super(myId);
    }

    /**
     * @methodtype constructor
     */
    public MushroomPhoto(PhotoId myId, double latitude, double longitude){
        super(myId,latitude,longitude);
    }

    /**
     * @methodtype get
     */
    public String getLatinName() {
        return latinName;
    }

    /**
     * @methodtype set
     */
    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

}
