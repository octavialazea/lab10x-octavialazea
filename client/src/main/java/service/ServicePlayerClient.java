package service;

import domain.utils.Factory;
import domain.entities.Game;
import domain.entities.Message;
import domain.entities.Player;
import exceptions.ServiceException;
import tcp.TcpClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ServicePlayerClient implements ServicePlayer{

    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ServicePlayerClient(ExecutorService executorService, TcpClient tcpClient){
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<Boolean> addPlayer(String name, String dateofbirth, String email) {
        Player newPlayer = new Player(name, dateofbirth, email);
        Message response = tcpClient.sendAndReceive(new Message("add player", Factory.playerToLine(newPlayer)));

        if (response.getHeader().equals("error")){
            throw new ServiceException("error adding player");
        }
        return executorService.submit(() -> true);
    }

    @Override
    public Future<Boolean> removePlayer(Integer ID) {
        Message response = tcpClient.sendAndReceive(new Message("remove player", Integer.toString(ID)));
        if (response.getHeader().equals("error"))
            throw new ServiceException("error removing player");
        return executorService.submit(
                () -> true
        );
    }

    @Override
    public Future<Boolean> updatePlayer(Integer ID, String name, String dateofbirth, String email) {
        Player player = new Player(name, dateofbirth, email);
        player.setId(ID);
        Message response = tcpClient.sendAndReceive(new Message("update player", Factory.playerToString(player)));
        if (response.getHeader().equals("error"))
            throw new ServiceException("error updating player");
        return executorService.submit(
                ()->true
        );

    }

    @Override
    public  Future<Iterable<Player>> getAllPlayers() {
        Message response;
        response = tcpClient.sendAndReceive(new Message("list players", ""));
        String[] tokens = response.getBody().split(System.lineSeparator());
        List<Player> players = Arrays.stream(tokens)
                .map(Factory::stringToPlayer)
                .collect(Collectors.toList());
        return executorService.submit(
                () -> players
        );

    }

    @Override
    public Future<Boolean> addGame(String name, String company, Integer price, Double rating, Integer playerID) {
        Game game = new Game(name, company, price, rating, playerID);
        Message response = tcpClient.sendAndReceive(new Message("add game", Factory.gameToLine(game)));

        if (response.getHeader().equals("error")){
            throw new ServiceException("error adding game");
        }

        return executorService.submit(
                () -> true
        );
    }

    @Override
    public Future<Boolean> removeGame(Integer ID) {
        Message response = tcpClient.sendAndReceive(new Message("remove game", Integer.toString(ID)));
        if (response.getHeader().equals("error"))
            throw new ServiceException("error removing game");
        return executorService.submit(
                () -> true
        );
    }

    @Override
    public Future<Boolean> updateGame(Integer ID, String name, String company, Integer price, Double rating, Integer playerID) {
        Game game = new Game(name, company, price, rating, playerID);
        game.setId(ID);
        Message response = tcpClient.sendAndReceive(new Message("update game", Factory.gameToString(game)));
        if (response.getHeader().equals("error"))
            throw new ServiceException("error updating game");
        return executorService.submit(
                ()->true
        );
    }

    @Override
    public Future<Iterable<Game>> getAllGames() {
        Message response;
        response = tcpClient.sendAndReceive(new Message("list games", ""));
        String[] tokens = response.getBody().split(System.lineSeparator());
        List<Game> games = Arrays.stream(tokens)
                .map(Factory::stringToGame)
                .collect(Collectors.toList());
        return executorService.submit(
                () -> games
        );
    }

    @Override
    public Future<Player> getPlayerById(Integer ID) {
        Message response;
        response = tcpClient.sendAndReceive(new Message("get player", ""));
        String[] tokens = response.getBody().split(System.lineSeparator());
        List<Player> players = Arrays.stream(tokens)
                .map(Factory::messageToPlayer)
                .collect(Collectors.toList());
        Optional<Player> player = players.stream()
                .filter(p -> p.getId().equals(ID))
                .findAny();
        return executorService.submit(
                player::get
        );
    }

    @Override
    public Future<Game> getGameById(Integer ID) {
        Message response;
        response = tcpClient.sendAndReceive(new Message("get game", ""));
        String[] tokens = response.getBody().split(System.lineSeparator());
        List<Game> games = Arrays.stream(tokens)
                .map(Factory::messageToGame)
                .collect(Collectors.toList());
        Optional<Game> game = games.stream()
                .filter(p -> p.getId().equals(ID))
                .findAny();
        return executorService.submit(
                game::get
        );
    }

}
