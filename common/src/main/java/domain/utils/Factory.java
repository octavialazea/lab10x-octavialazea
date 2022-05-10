package domain.utils;

import domain.entities.Computer;
import domain.entities.Game;
import domain.entities.Player;
import domain.entities.Zone;

public class Factory {
    public static String playerToLine(Player player){
        return player.getName() + "," + player.getDate() + "," + player.getEmail();
    }

    public static String playerToString(Player player){
        return player.getId() + "," + player.getName() + "," + player.getDate() + "," + player.getEmail();
    }

    public static String gameToLine(Game game){
        return game.getName() + "," + game.getCompany() + "," + game.getPrice() + "," + game.getRating() +
                "," + game.getPlayerID();
    }

    public static String gameToString(Game game){
        return game.getId() + "," + game.getName() + "," + game.getCompany() + "," + game.getPrice() + "," + game.getRating() +
                "," + game.getPlayerID();
    }

    public static String zoneToLine(Zone zone){
        return zone.getName() + ","  +zone.getTheme() + "," + zone.getCapacity();
    }

    public static String zoneToString(Zone zone){
        return zone.getId() + "," + zone.getName() + ","  +zone.getTheme() + "," + zone.getCapacity();
    }

    public static  String computerToLine(Computer computer){
        return computer.getZoneID() + "," + computer.getOperatingSystem() + "," + computer.getPurchaseDate();
    }

    public static  String computerToString(Computer computer){
        return computer.getId() + "," + computer.getZoneID() + "," + computer.getOperatingSystem() + "," + computer.getPurchaseDate();
    }

    public static Player messageToPlayer(String message){
        if (message.length() == 0)
            return new Player();
        String[] tokens = message.split(",");

        if (tokens.length == 0)
            return new Player();
        var name = tokens[0];
        var dob = tokens[1];
        var email = tokens [2];
        return new Player(name, dob, email);
    }

    public static Player stringToPlayer(String message){
        if (message.length() == 0)
            return new Player();
        String[] tokens = message.split(",");

        if (tokens.length == 0)
            return new Player();
        var id = Integer.parseInt(tokens[0]);
        var name = tokens[1];
        var dob = tokens[2];
        var email = tokens [3];
        Player p =  new Player(name, dob, email);
        p.setId(id);
        return p;
    }

    public static Game messageToGame(String message){
        if (message.length() == 0)
            return new Game();
        String[] tokens = message.split(",");
        if (tokens.length == 0)
            return new Game();
        var name = tokens[0];
        var comapny = tokens[1];
        var price = Integer.parseInt(tokens[2]);
        var rating = Double.parseDouble(tokens[3]);
        var playerId = Integer.parseInt(tokens[4]);

        Game game = new Game(name, comapny, price, rating, playerId);
        return game;
    }

    public static Game stringToGame(String message){
        if (message.length() == 0)
            return new Game();
        String[] tokens = message.split(",");
        if (tokens.length == 0)
            return new Game();
        var id = Integer.parseInt(tokens[0]);
        var name = tokens[1];
        var comapny = tokens[2];
        var price = Integer.parseInt(tokens[3]);
        var rating = Double.parseDouble(tokens[4]);
        var playerId = Integer.parseInt(tokens[5]);

        Game game = new Game(name, comapny, price, rating, playerId);
        game.setId(id);
        return game;
    }

    public static Zone messageToZone(String message){
        if (message.length() == 0)
            return new Zone();
        String[] tokens = message.split(",");
        if (tokens.length == 0)
            return new Zone();
        var name = tokens[0];
        var theme = tokens[1];
        var capacity = Integer.parseInt(tokens[2]);
        Zone zone = new Zone(name, theme, capacity);
        return zone;
    }

    public static Zone stringToZone(String message){
        if (message.length() == 0)
            return new Zone();
        String[] tokens = message.split(",");
        if (tokens.length == 0)
            return new Zone();
        var id = Integer.parseInt(tokens[0]);
        var name = tokens[1];
        var theme = tokens[2];
        var capacity = Integer.parseInt(tokens[3]);
        Zone zone = new Zone(name, theme, capacity);
        zone.setId(id);
        return zone;
    }

    public static Computer messageToComputer(String message){
        if (message.length() == 0)
            return new Computer();
        String[] tokens = message.split(",");
        if (tokens.length == 0)
            return new Computer();
        var zoneId = Integer.parseInt(tokens[0]);
        var os = tokens[1];
        var date = tokens[2];
        return  new Computer(zoneId, os, date);
    }

    public static Computer stringToComputer(String message){
        if (message.length() == 0)
            return new Computer();
        String[] tokens = message.split(",");
        if (tokens.length == 0)
            return new Computer();
        var id = Integer.parseInt(tokens[0]);
        var zoneId = Integer.parseInt(tokens[1]);
        var os = tokens[2];
        var date = tokens[3];
        Computer computer = new Computer(zoneId, os, date);
        computer.setId(id);
        return computer;
    }
}
