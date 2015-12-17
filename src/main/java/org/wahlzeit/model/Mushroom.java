package org.wahlzeit.model;

public class Mushroom {
    protected static long lastId = 1;
    protected long id;
    protected MushroomType type = null;

    private boolean fullGrown;
    private double size;
    private String color;

    /**
     * @methodtype constructor
     */
    public Mushroom(MushroomType type){
        this.type = type;
        this.id = ++lastId;
    }

    /**
     * @methodtype constructor
     */
    public Mushroom(MushroomType type,boolean fullGrown, double size, String color){
        this(type);
        this.fullGrown = fullGrown;
        this.size = size;
        this.color = color;
    }

    /**
     * @methodtype query
     */
    public MushroomType getType(){
        return type;
    }

    /**
     * @methodtype get
     */
    public long getId(){
        return lastId;
    }

    /**
     * @methodtype get
     */
    public boolean getFullGrown() {
        return fullGrown;
    }

    /**
     * @methodtype set
     */
    public void setLocation(boolean fullGrown) {
        assertIsNotNull(fullGrown);
        this.fullGrown = fullGrown;
    }

    /**
     * @methodtype get
     */
    public double getSize() {
        return size;
    }

    /**
     * @methodtype set
     */
    public void setSize(double size) {
        assertIsPositiveDouble(size);
        this.size = size;
    }

    /**
     * @methodtype get
     */
    public String getColor() {
        return color;
    }

    /**
     * @methodtype set
     */
    public void setColor(String color) {
        assertIsNotNull(color);

        this.color = color;
    }

    /**
     * @methodtype assertion
     */
    private void assertIsPositiveDouble(double value) {
        if (value < 0) {
            throw new IllegalArgumentException(
                    "Negative double value is not allowed");
        }
    }

    /**
     * @methodtype assertion
     */
    private void assertIsNotNull(Object value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
    }
}
