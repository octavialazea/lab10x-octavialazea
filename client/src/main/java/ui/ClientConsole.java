package ui;

import domain.entities.Computer;
import domain.entities.Game;
import domain.entities.Player;
import domain.entities.Zone;
import exceptions.InputException;
import service.ServicePlayer;
import service.ServiceZone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ClientConsole {
    private ServicePlayer servicePlayer;
    private ServiceZone serviceZone;

    public ClientConsole(ServicePlayer servicePlayer, ServiceZone serviceZone){
        this.servicePlayer = servicePlayer;
        this.serviceZone = serviceZone;
    }

    public void menu() {
        System.out.println("\nAdd operation - add (entity) (parameters)");
        System.out.println("Update operation - update (entity) (id) (parameters)");
        System.out.println("Remove operation - remove (entity) (id)");
        System.out.println("List operation - list (entity)");
        System.out.println("Help! - help");
        System.out.println("Exit - exit\n");
    }

    public void instructions() {
        System.out.println("\nPlayer has 3 parameters: name dateOfBirth (dd/mm/yyyy) email");
        System.out.println("Game has 5 parameters: name company price rating IDofBestPlayer");
        System.out.println("Computer has 3 parameters: zoneID operatingSystem purchaseDate");
        System.out.println("Zone has 3 parameters: name theme capacity");
    }

    public void runConsole(){
        this.menu();
        String command;
        String[] params;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Command>>");
            command = scanner.nextLine();
            params = command.trim().split(" ");
            try {
                switch (params[0]) {
                    case "add" -> this.add(params);
                    case "update" -> this.update(params);
                    case "remove" -> this.remove(params);
                    case "list" -> this.list(params);
                    case "help" -> this.instructions();
                    case "exit" -> {
                        System.out.println("Bye!");
                        return;
                    }
                    default -> System.out.println("Invalid command");
                }
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        }

    }

    private void list(String[] params) {
        String entity = params[1];
        switch (entity) {
            case "players" -> {
                try{
                    List<Player> players = new ArrayList<>();
                    servicePlayer.getAllPlayers().get().forEach(players::add);
                    players.stream().map(Player::toString).map(s -> s + "\n").forEach(System.out::print);
                }catch (InterruptedException | ExecutionException e){
                    e.printStackTrace();
                }
            }
            case "games" -> {
                try{
                    List<Game> games = new ArrayList<>();
                    servicePlayer.getAllGames().get().forEach(games::add);
                    games.stream().map(Game::toString).map(s -> s + "\n").forEach(System.out::print);
                }catch (InterruptedException | ExecutionException e){
                    e.printStackTrace();
                }
            }
            case "computers" -> {
                try{
                    List<Computer> computers = new ArrayList<>();
                    serviceZone.getAllComputers().get().forEach(computers::add);
                    computers.stream().map(Computer::toString).map(s -> s + "\n").forEach(System.out::print);
                }catch (InterruptedException | ExecutionException e){
                    e.printStackTrace();
                }
            }
            case "zones" -> {
                try{
                    List<Zone> zones = new ArrayList<>();
                    serviceZone.getAllZones().get().forEach(zones::add);
                    zones.stream().map(Zone::toString).map(s -> s + "\n").forEach(System.out::print);
                }catch (InterruptedException | ExecutionException e){
                    e.printStackTrace();
                }
            }
            default -> System.out.println("Invalid command");
        }
    }

    private void remove(String[] params) {
        String entity = params[1];
        switch (entity) {
            case "player" -> {
                if (params.length != 3) throw new InputException("Incorrect number of parameters");
                this.servicePlayer.removePlayer(Integer.parseInt(params[2]));
            }
            case "game" -> {
                if (params.length != 3) throw new InputException("Incorrect number of parameters");
                this.servicePlayer.removeGame(Integer.parseInt(params[2]));
            }
            case "computer" -> {
                if (params.length != 3) throw new InputException("Incorrect number of parameters");
                this.serviceZone.removeComputer(Integer.parseInt(params[2]));
            }
            case "zone" -> {
                if (params.length != 3) throw new InputException("Incorrect number of parameters");
                this.serviceZone.removeZone(Integer.parseInt(params[2]));
            }
            default -> System.out.println("Invalid command");
        }
    }

    private void update(String[] params) {String entity = params[1];
        switch (entity) {
            case "player" -> {
                if (params.length != 6) throw new InputException("Incorrect number of parameters");
                this.servicePlayer.updatePlayer(Integer.parseInt(params[2]), params[3], params[4], params[5]);
            }
            case "game" -> {
                if (params.length != 8) throw new InputException("Incorrect number of parameters");
                this.servicePlayer.updateGame(Integer.parseInt(params[2]), params[3], params[4],
                        Integer.parseInt(params[5]), Double.parseDouble(params[6]), Integer.parseInt(params[7]));
            }
            case "computer" -> {
                if (params.length != 6) throw new InputException("Incorrect number of parameters");
                this.serviceZone.updateComputer(Integer.parseInt(params[2]), Integer.parseInt(params[3]), params[4], params[5]);
            }
            case "zone" -> {
                if (params.length != 6) throw new InputException("Incorrect number of parameters");
                this.serviceZone.updateZone(Integer.parseInt(params[2]), params[3], params[4], Integer.parseInt(params[5]));
            }
            default -> System.out.println("Invalid command");
        }


    }

    public void add(String[] params) {
        String entity = params[1];
        switch (entity) {
            case "player" -> {
                if (params.length != 5) throw new InputException("Incorrect number of parameters");
                this.servicePlayer.addPlayer(params[2], params[3], params[4]);
            }
            case "game" -> {
                if (params.length != 7) throw new InputException("Incorrect number of parameters");
                this.servicePlayer.addGame(params[2], params[3], Integer.parseInt(params[4]),
                        Double.parseDouble(params[5]), Integer.parseInt(params[6]));
            }
            case "computer" -> {
                if (params.length != 5) throw new InputException("Incorrect number of parameters");
                this.serviceZone.addComputer(Integer.parseInt(params[2]), params[3], params[4]);
            }
            case "zone" -> {
                if (params.length != 5) throw new InputException("Incorrect number of parameters");
                this.serviceZone.addZone(params[2], params[3], Integer.parseInt(params[4]));
            }

            default -> System.out.println("Bad command");
        }
    }
}
