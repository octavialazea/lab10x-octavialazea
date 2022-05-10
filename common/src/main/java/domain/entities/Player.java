package domain.entities;

import java.io.*;
import java.util.Objects;
import java.util.Random;

public class Player extends BaseEntity<Integer> {

    private String name;
    private String dateOfBirth;
    private String email;

    /**
     * Constructor for Player
     *
     * @param _name  player's name
     * @param _date  date of birth as a String
     * @param _email contact email for the player
     */

    public Player(String _name, String _date, String _email) {
        this.name = _name;
        this.dateOfBirth = _date;
        this.email = _email;
    }

    public Player() {

    }

    /**
     * Setter for player name
     *
     * @param dateOfBirth new date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Setter for player email
     *
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter for player name
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for name
     *
     * @return a string representing the player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for date
     *
     * @return a string representing the player's birth date
     */
    public String getDate() {
        return this.dateOfBirth;
    }

    /**
     * Getter for email
     *
     * @return a string representing the player's contact email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Generates a random ID for the player
     */
    public void generateID() {
        Random rand = new Random();
        int newID = rand.nextInt(9999);
        this.setId(newID);
    }

    /**
     * Compares two players
     *
     * @param o object to compare to
     * @return true if both are players and have the same ID
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Player))
            return false;
        return Objects.equals(((Player) o).getId(), this.getId());
    }

    /**
     * Overrides the toString method
     *
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return "Player id: " + this.getId() + " || " + "Name: " + this.name + " || " + "Email: " +
                this.email + " || " + "Date of birth: " + this.dateOfBirth;
    }



    @Override
    public String getVar(String var) throws Exception{
        switch (var){
            case "id" -> { return this.getId().toString();}
            case "name" -> {
                return name;
            }
            case "email" -> {
                return email;
            }
            case "dateOfBirth" ->{
                return this.dateOfBirth.substring(6, 10) + this.dateOfBirth.substring(3, 5) + this.dateOfBirth.substring(0, 2);
            }
            default -> {
                throw new Exception("Not a valid field");
            }
        }
    }

}
