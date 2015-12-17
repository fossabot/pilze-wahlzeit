package org.wahlzeit.model;

public class MushroomType {

    private String latinName;
    private boolean eatable;
    private String name;

    /**
     * @methodtype constructor
     */
    public MushroomType(String name,String latinName, boolean eatable){
        this.name = name;
        this.latinName = latinName;
        this.eatable = eatable;
    }

    /**
     * @methodtype factory
     */
    public Mushroom createInstance(){
        return new Mushroom(this);
    }

    /**
     * @methodtype get
     */
    public String getName(){
        return name;
    }

    /**
     * @methodtype get
     */
    public String getLatinName(){
        return latinName;
    }

    /**
     * @methodtype get
     */
    public boolean getEatable(){
        return eatable;
    }
}
