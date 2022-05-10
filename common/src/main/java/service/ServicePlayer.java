package service;

import domain.entities.Game;
import domain.entities.Player;
import storage.Repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface ServicePlayer {

    Future<Boolean> addPlayer(String name, String dateofbirth, String email);
    Future<Boolean> removePlayer(Integer ID);
    Future<Boolean> updatePlayer(Integer ID, String name, String dateofbirth, String email);
    Future<Iterable<Player>> getAllPlayers();
    Future<Boolean> addGame(String name, String company, Integer price, Double rating, Integer playerID);
    Future<Boolean> removeGame(Integer ID);
    Future<Boolean> updateGame(Integer ID, String name, String company, Integer price, Double rating, Integer playerID);
    Future<Iterable<Game>> getAllGames();
    Future<Player> getPlayerById(Integer ID);
    Future<Game> getGameById(Integer ID);
}
