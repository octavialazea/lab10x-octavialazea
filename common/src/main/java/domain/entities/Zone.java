package domain.entities;

import java.io.*;
import java.util.Objects;
import java.util.Random;

public class Zone extends BaseEntity<Integer>{

    private String name;
    private String theme;
    private Integer capacity;

    /***
     * Constructor for Zone
     *
     * @param _name name of the zone
     * @param _theme theme of the zone
     * @param _capacity computer capacity of the zone
     */
    public Zone(String _name, String _theme, Integer _capacity) {
        this.name = _name;
        this.theme = _theme;
        this.capacity = _capacity;
    }

    public Zone() {

    }

    /***
     * Generates a random id for the zone
     */




    public void generateID(){
        Random rand = new Random();
        int newID = rand.nextInt(9999);
        this.setId(newID);
    }

    /***
     * Getter for the name of the zone
     *
     * @return a string representing the name of the zone
     */
    public String getName() {
        return name;
    }

    /***
     * Getter for the theme of the zone
     *
     * @return a string representing the theme of the zone
     */
    public String getTheme() {
        return theme;
    }

    /***
     * Getter for the capacity of the zone
     *
     * @return a Integer representing the capacity of the zone
     */
    public Integer getCapacity() {
        return capacity;
    }

    /***
     * Setter for zone name
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Setter for zone theme
     *
     * @param theme new theme
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /***
     * Setter for zone capacity
     *
     * @param capacity new capacity
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /***
     * Overrides the equals method
     *
     * @param o object to which we compare
     * @return true if all attributes are equal and false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Zone zone = (Zone) o;

        return Objects.equals(zone.getId(), this.getId()) && Objects.equals(zone.getName(), this.name)
                && Objects.equals(zone.getTheme(), this.theme) && Objects.equals(zone.getCapacity(), this.capacity);
    }


    @Override
    public String getVar(String var) throws Exception {
        switch (var) {
            case "id" -> {return this.getId().toString();}
            case "name" -> {return this.name.toString();}
            case "theme" -> {return this.theme.toString();}
            case "capacity" -> {return this.capacity.toString();}

            default -> {throw new Exception("Not a valid field");}
        }
    }


    /**
     * Overrides the toString method
     *
     * @return the string representation of the object
     */
    @Override
    public String toString(){
        return "Zone id:"+this.getId() + " || " +"Name: " +this.name + " || " +
                "Theme: " + this.theme + " || " + "Capacity: "+this.capacity;
    }
}
