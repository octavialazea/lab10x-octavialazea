package domain.entities;

import java.io.*;
import java.util.Objects;
import java.util.Random;

public class Game extends BaseEntity<Integer>{
    private String name;
    private String company;
    private Integer price;
    private Double rating;
    private Integer bestPlayerID;

    /***
     * Constructor for Game
     *
     * @param _name name of the game
     * @param _company game developer
     * @param _price game price (0 if it is free)
     * @param _rating game rating (between 0 and 5)
     * @param _playerID id of the best player of this game
     */
    public Game(String _name, String _company, Integer _price, Double _rating, Integer _playerID) {
        this.name = _name;
        this.company = _company;
        this.price = _price;
        this.rating = _rating;
        this.bestPlayerID = _playerID;
    }

    public Game() {

    }


    /***
     * Generates a random id for the game
     */
    public void generateID(){
        Random rand = new Random();
        int newID = rand.nextInt(9999);
        this.setId(newID);
    }

    /***
     * Getter for the name of the game
     *
     * @return a string representing the name of the game
     */
    public String getName() {
        return name;
    }

    /***
     * Setter for game name
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Getter for company name
     *
     * @return a string representing the name of the game development company
     */
    public String getCompany() {
        return company;
    }

    /***
     * Setter for company name
     *
     * @param company new company name
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /***
     * Getter for price
     *
     * @return an integer representing the price of the game
     */
    public Integer getPrice() {
        return price;
    }

    /***
     * Setter for price
     *
     * @param price new price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /***
     * Getter for rating
     *
     * @return a double representing the rating
     */
    public Double getRating() {
        return rating;
    }

    /***
     * Setter for rating
     *
     * @param rating new rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /***
     * Getter for best player
     *
     * @return the id of the best player
     */
    public Integer getPlayerID() {
        return bestPlayerID;
    }

    /***
     * Setter for best player
     *
     * @param playerID new best player ID
     */
    public void setBestPlayerID(Integer playerID){
        bestPlayerID = playerID;
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

        Game game = (Game) o;

        return Objects.equals(game.getName(), this.name)
                && Objects.equals(game.getCompany(), this.company) && Objects.equals(game.getRating(), this.rating)
                && Objects.equals(game.getPrice(), this.price);
    }

    /**
     * Overrides the toString method
     *
     * @return the string representation of the object
     */
    @Override
    public String toString(){
        return "Game id: " + this.getId() + " || " +"Name: " + this.name + " || " +"Company: "+ this.company + " || "+
                "Price: "+ this.price + " || " + "Rating: " + this.rating + " || " + "Best player id: "+this.bestPlayerID;
    }

    @Override
    public String getVar(String var) throws Exception{
        switch (var){
            case "id" -> {return this.getId().toString();}
            case "name" ->{return name;}
            case "company" ->{return company;}
            case "price" -> {return price.toString();}
            case "rating" ->{return rating.toString();}
            case "bestPlayerID" ->{return bestPlayerID.toString();}
            default -> throw new Exception("Not a valid field");
        }

    }
}
