package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class MushroomPhoto extends Photo{


        Mushroom mushroom;

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
    public MushroomPhoto(PhotoId myId, Location location){
        super(myId,location);
    }

    /**
     * @methodtype constructor
     */
    public MushroomPhoto(PhotoId myId, Location location,String typeName){
        super(myId,location);
        mushroom = MushroomManager.getInstance().createMushroom(typeName);
    }

    /**
     * @methodtype getter
     */
    public Mushroom getMushroom(){
        return mushroom;
    }
}
