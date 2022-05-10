package service;

import domain.entities.Game;
import domain.entities.Player;
import exceptions.ServiceException;
import storage.Repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServicePlayerImpl implements ServicePlayer{

    private Repository<Integer, Player> players;
    private Repository<Integer, Game> games;
    private ExecutorService executorService;

    public ServicePlayerImpl(Repository<Integer, Player> players, Repository<Integer, Game> games, ExecutorService executorService){
        this.players = players;
        this.games = games;
        this.executorService = executorService;
    }


    @Override
    public Future<Boolean> addPlayer(String name, String dateofbirth, String email) {
        Player player = new Player(name, dateofbirth, email);
        player.generateID();
        while (players.findOne(player.getId()).isPresent()){
            player.generateID();
        }
        return executorService.submit(() -> {
            players.save(player);
            return true;
        });
    }

    @Override
    public Future<Boolean> removePlayer(Integer ID) {
//
        return executorService.submit(() -> {
            try{
                players.findOne(ID).orElseThrow(() -> new ServiceException("Player is not in the database"));
                players.delete(ID);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    @Override
    public Future<Boolean> updatePlayer(Integer ID, String name, String dateofbirth, String email) {
        return executorService.submit( () -> {
            try{
                Player maybe = players.findOne(ID).orElseThrow(() -> new ServiceException("Player is not in the database"));
                maybe.setName(name);
                maybe.setEmail(email);
                maybe.setDateOfBirth(dateofbirth);
                maybe.setId(ID);
                players.update(maybe);
                return true;
            }catch (Exception e){
                return false;
            }
        });

    }

    @Override
    public Future<Iterable<Player>> getAllPlayers() {
        return executorService.submit(
                () -> {
                    try {
                        return this.players.findAll();
                    } catch (Exception e) {
                        throw new ServiceException(e.getMessage());
                    }
                });
    }

    @Override
    public Future<Boolean> addGame(String name, String company, Integer price, Double rating, Integer playerID) {
        players.findOne(playerID).orElseThrow(()-> new ServiceException("Given player is not in the database"));
        Game attempt = new Game(name, company, price, rating, playerID);
        games.findAll().forEach(game -> {
            if (game.equals(attempt))
                throw new ServiceException("Game already stored");
        });
        attempt.generateID();
        while(games.findOne(attempt.getId()).isPresent())
            attempt.generateID();
        return executorService.submit( () -> {
            try{
                games.save(attempt);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    @Override
    public Future<Boolean> removeGame(Integer ID) {
        games.findOne(ID).orElseThrow(()-> new ServiceException("Game is not in the database"));
        return executorService.submit( () -> {
            try{
                games.delete(ID);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    @Override
    public Future<Boolean> updateGame(Integer ID, String name, String company, Integer price, Double rating, Integer playerID) {
        return executorService.submit(() -> {
            try{
                Game maybe = games.findOne(ID).orElseThrow(()-> new ServiceException("Game is not in the database"));
                players.findOne(playerID).orElseThrow(()-> new ServiceException("Given player is not in the database"));
                maybe.setName(name);
                maybe.setCompany(company);
                maybe.setBestPlayerID(playerID);
                maybe.setRating(rating);
                maybe.setPrice(price);
                games.update(maybe);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    @Override
    public Future<Iterable<Game>> getAllGames() {
        return executorService.submit(
                () -> {
                    try {
                        return this.games.findAll();
                    } catch (Exception e) {
                        throw new ServiceException(e.getMessage());
                    }
                });
    }

    @Override
    public Future<Player> getPlayerById(Integer ID) {
        return executorService.submit(() -> {
            try {
                return players.findOne(ID).orElse(null);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    @Override
    public Future<Game> getGameById(Integer ID) {
        return executorService.submit(() -> {
            try {
                return games.findOne(ID).orElse(null);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }

}
