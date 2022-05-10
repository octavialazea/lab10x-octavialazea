package tcp;

import domain.utils.Factory;
import domain.entities.*;
import exceptions.ServiceException;
import service.ServicePlayer;
import service.ServiceZone;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MessageHandler {

    private ServicePlayer servicePlayer;
    private ServiceZone serviceZone;

    public MessageHandler(ServicePlayer servicePlayer, ServiceZone serviceZone){
        this.servicePlayer = servicePlayer;
        this.serviceZone = serviceZone;
    }

    public Message addPlayer(Message request){
        Player player = Factory.messageToPlayer(request.getBody());
        try{
            if (servicePlayer.addPlayer(player.getName(), player.getDate(), player.getEmail()).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Player already in the database");
        }catch (ServiceException | InterruptedException | ExecutionException e){
            return new Message("error", e.getMessage());
        }
    }

    public Message removePlayer(Message request){
        try{
            servicePlayer.removePlayer(Integer.parseInt(request.getBody())).get();
            return new Message("ok", "ok");
        } catch (ExecutionException | InterruptedException e) {
            return new Message("error", e.getMessage());
        }
    }

    public Message updatePlayer(Message request){
        Player player = Factory.messageToPlayer(request.getBody());
        try{
            servicePlayer.updatePlayer(player.getId(), player.getName(), player.getDate(), player.getEmail()).get();
            return new Message("ok", "ok");
        }catch (ExecutionException | InterruptedException e){
            return new Message("error", e.getMessage());
        }
    }

    public Message getAllPlayers(Message request){
        try{
            List<Player> players = new ArrayList<>();
            servicePlayer.getAllPlayers().get().forEach(players::add);
            String messageBody = players.stream()
                    .map(Factory::playerToString)
                    .reduce("", (a,b) -> a + b + System.lineSeparator());
            return new Message("ok", messageBody);
        }catch (Exception e){
            return new Message("error", e.getMessage());
        }
    }

    public Message addZone(Message request){
        Zone zone = Factory.messageToZone(request.getBody());
        try{
            if (serviceZone.addZone(zone.getName(), zone.getTheme(), zone.getCapacity()).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Zone already in the database");
        }catch (ServiceException | InterruptedException | ExecutionException e){
            return new Message("error", e.getMessage());
        }
    }

    public Message removeZone(Message request){
        try{
            serviceZone.removeZone(Integer.parseInt(request.getBody())).get();
            return new Message("ok", "ok");
        } catch (ExecutionException | InterruptedException e) {
            return new Message("error", e.getMessage());
        }
    }

    public Message updateZone(Message request){
        Zone zone = Factory.messageToZone(request.getBody());
        try{
            serviceZone.updateZone(zone.getId(), zone.getName(), zone.getTheme(), zone.getCapacity()).get();
            return new Message("ok", "ok");
        }catch (ExecutionException | InterruptedException e){
            return new Message("error", e.getMessage());
        }
    }

    public Message getAllZones(Message request){
        try{
            //Iterable<Player> players = servicePlayer.getAllPlayers().get();
            List<Zone> zones = new ArrayList<>();
            serviceZone.getAllZones().get().forEach(zones::add);
            String messageBody = zones.stream()
                    .map(Factory::zoneToLine)
                    .reduce("", (a,b) -> a + b + System.lineSeparator());
            return new Message("ok", messageBody);
        }catch (Exception e){
            return new Message("error", e.getMessage());
        }
    }
    public Message addGame(Message request){
        Game game = Factory.messageToGame(request.getBody());
        try{
            if (servicePlayer.addGame(game.getName(), game.getCompany(), game.getPrice(), game.getRating(), game.getPlayerID()).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Game already in the database");
        }catch (ServiceException | InterruptedException | ExecutionException e){
            return new Message("error", e.getMessage());
        }
    }

    public Message removeGame(Message request){
        try{
            servicePlayer.removeGame(Integer.parseInt(request.getBody())).get();
            return new Message("ok", "ok");
        } catch (ExecutionException | InterruptedException e) {
            return new Message("error", e.getMessage());
        }
    }

    public Message updateGame(Message request){
        Game game = Factory.messageToGame(request.getBody());
        try{
            servicePlayer.updateGame(game.getId(), game.getName(), game.getCompany(), game.getPrice(), game.getRating(), game.getPlayerID()).get();
            return new Message("ok", "ok");
        }catch (ExecutionException | InterruptedException e){
            return new Message("error", e.getMessage());
        }
    }

    public Message getAllGames(Message request){
        try{
            //Iterable<Player> players = servicePlayer.getAllPlayers().get();
            List<Game> games = new ArrayList<>();
            servicePlayer.getAllGames().get().forEach(games::add);
            String messageBody = games.stream()
                    .map(Factory::gameToLine)
                    .reduce("", (a,b) -> a + b + System.lineSeparator());
            return new Message("ok", messageBody);
        }catch (Exception e){
            return new Message("error", e.getMessage());
        }
    }

    public Message addComputer(Message request){
        Computer computer = Factory.messageToComputer(request.getBody());
        try{
            if (serviceZone.addComputer(computer.getZoneID(), computer.getOperatingSystem(), computer.getPurchaseDate()).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Computer already in the database");
        }catch (ServiceException | InterruptedException | ExecutionException e){
            return new Message("error", e.getMessage());
        }
    }

    public Message removeComputer(Message request){
        try{
            serviceZone.removeComputer(Integer.parseInt(request.getBody())).get();
            return new Message("ok", "ok");
        } catch (ExecutionException | InterruptedException e) {
            return new Message("error", e.getMessage());
        }
    }

    public Message updateComputer(Message request){
        Computer computer = Factory.messageToComputer(request.getBody());
        try{
            serviceZone.updateComputer(computer.getId(), computer.getZoneID(), computer.getOperatingSystem(), computer.getPurchaseDate()).get();
            return new Message("ok", "ok");
        }catch (ExecutionException | InterruptedException e){
            return new Message("error", e.getMessage());
        }
    }

    public Message getAllComputers(Message request){
        try{
            //Iterable<Player> players = servicePlayer.getAllPlayers().get();
            List<Computer> computers = new ArrayList<>();
            serviceZone.getAllComputers().get().forEach(computers::add);
            String messageBody = computers.stream()
                    .map(Factory::computerToLine)
                    .reduce("", (a,b) -> a + b + System.lineSeparator());
            return new Message("ok", messageBody);
        }catch (Exception e){
            return new Message("error", e.getMessage());
        }
    }
}
