package domain.entities;

import java.io.*;
import java.util.Objects;
import java.util.Random;

public class Computer extends BaseEntity<Integer>{

    private Integer zoneID;
    private String operatingSystem;
    private String purchaseDate;


    /***
     * Constructor for Computer
     *
     * @param z id of the zone
     * @param os operating system of the computer
     * @param date purchase date of the computer
     */
    public Computer(Integer z, String os, String date){
        this.zoneID = z;
        this.operatingSystem = os;
        this.purchaseDate = date;
    }

    public Computer() {

    }

    /***
     * Generates a random id for the game
     */
    public void generateID() {
        Random rand = new Random();
        int newID = rand.nextInt(9999);
        this.setId(newID);
    }

    /***
     * Getter for the zone ID
     *
     * @return an integer representing the ID of the zone to which the computer belongs
     */
    public Integer getZoneID(){
        return zoneID;
    }

    /***
     * Getter for the operating system of the computer
     *
     * @return a string representing the operating system
     */
    public String getOperatingSystem(){
        return operatingSystem;
    }

    /***
     * Getter for the purchase date of the computer
     *
     * @return a string representing the purchase date
     */
    public String getPurchaseDate(){
        return purchaseDate;
    }

    /***
     * Setter for zone ID
     *
     * @param id new zone id
     */
    public void setZoneID(Integer id){
        this.zoneID = id;
    }

    /***
     * Setter for operating system
     *
     * @param os new operating system
     */
    public void setOperatingSystem(String os){
        this.operatingSystem = os;
    }

    /***
     * Setter for purchase date
     *
     * @param date new purchase date
     */

    public void setPurchaseDate(String date){
        this.purchaseDate = date;
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

        Computer computer = (Computer) o;

        return Objects.equals(computer.getId(), this.getId()) && Objects.equals(computer.getZoneID(), this.zoneID)
                && Objects.equals(computer.getOperatingSystem(), this.operatingSystem) && Objects.equals(computer.getPurchaseDate(), this.purchaseDate);
    }

    @Override
    public String getVar(String var) throws Exception {
        switch(var) {
            case "zoneID" -> {
                return this.zoneID.toString();
            }
            case "operatingSystem" -> {
                return this.operatingSystem;
            }
            case "purchaseDate" -> {
                return this.purchaseDate.substring(6, 10) + this.purchaseDate.substring(3, 5) + this.purchaseDate.substring(0, 2);
            }
            default -> {
                throw new Exception("This field does not exist!");
            }
        }
    }

    /**
     * Overrides the toString method
     *
     * @return the string representation of the object
     */
    @Override
    public String toString(){
        return "Computer id: " + this.getId() + " || " + "Zone id: "+ this.getZoneID() + " || " +"Operating system: "+ this.getOperatingSystem() + " || " +"Purchase date: "+ this.getPurchaseDate();
    }


}
