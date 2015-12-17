package org.wahlzeit.model;

import java.util.HashMap;

public class MushroomManager {

    private static HashMap<Long,Mushroom> mushroomMap = new HashMap<>();
    private static HashMap<String,MushroomType> mushroomTypeMap = new HashMap<>();
    private static MushroomManager instance = null;

    /**
     * @methodtype constructor
     */
    private MushroomManager(){
        // create default types
        createMushroomType("Pfifferlinge","Cantharellus",true);
        createMushroomType("Fliegenpilz","Amanita muscaria",false);
        createMushroomType("Gifthübling","Galerina marginata",false);
    }

    /**
     * @methodtype getter
     */
    public static MushroomManager getInstance(){
        if(instance == null){
            instance = new MushroomManager();
        }
        return instance;
    }

    /**
     * @methodtype constructor
     */
    public Mushroom createMushroom(String typeName){
        assertIsMushroomTypeExisting(typeName);
        MushroomType mt = getMushroomType(typeName);
        Mushroom result = mt.createInstance();
        mushroomMap.put(result.getId(),result);
        return result;
    }
    /**
     * @methodtype factory
     */
    public MushroomType createMushroomType(String name, String latinName, boolean eatable){
        assertMushroomTypeNotExist(name);
        MushroomType mt = new MushroomType(name,latinName,eatable);
        mushroomTypeMap.put(name,mt);
        return mt;
    }

    /**
     * @methodtype query
     */
    private MushroomType getMushroomType(String name){
        return mushroomTypeMap.get(name);
    }

    /**
     * @methodtype command method
     */
    public void removeMushroom(long id) {
        mushroomMap.remove(id);
    }

    /**
     * @methodtype command method
     */
    public void removeMushroomType(String name) {
        mushroomTypeMap.remove(name);
    }

    /**
     * @methodtype assertion
     */
    private void assertMushroomTypeNotExist(String name) {
        if (mushroomTypeMap.get(name) != null) {
            throw new IllegalArgumentException("MushroomType " + name
                    + " already exists");
        }
    }

    /**
     * @methodtype assertion
     */
    private void assertIsMushroomTypeExisting(String name) {
        if (mushroomTypeMap.get(name) == null) {
            throw new IllegalArgumentException("MushroomType " + name
                    + " not available");
        }
    }
}
