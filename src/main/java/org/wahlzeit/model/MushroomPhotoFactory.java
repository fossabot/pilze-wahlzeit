package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class MushroomPhotoFactory extends PhotoFactory{

   private static final Logger log = Logger.getLogger(PhotoFactory.class.getName());

   private static MushroomPhotoFactory instance = null;

    /**
     * @methodtype constructor
     */
    private MushroomPhotoFactory(){

    }

    /**
     * Public singleton access method.
     */
    public static synchronized MushroomPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic MushroomPhotoFactory").toString());
            setInstance(new MushroomPhotoFactory());
        }
        return instance;
    }

    /**
     * @methodtype factory
     */
    public Photo createPhoto() {
        return new MushroomPhoto();
    }

    /**
     * Creates a new photo with the specified id
     *  @methodtype factory
     */
    public Photo createPhoto(PhotoId id) {
        return new MushroomPhoto(id);
    }

    /**
     *  Creates a new photo with the specified id and location
     * @methodtype factory
     */
    public Photo createPhoto(PhotoId id, double latitude, double longitude) {
        return new MushroomPhoto(id, latitude, longitude);
    }



}
