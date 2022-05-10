import domain.entities.Computer;
import domain.entities.Game;
import domain.entities.Player;
import domain.entities.Zone;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.PlayerDBRepo;
import service.ServicePlayer;
import service.ServicePlayerImpl;
import service.ServiceZone;
import service.ServiceZoneImpl;
import storage.Repository;
import repository.ComputerDBRepo;
import repository.GameDBRepo;
import repository.ZoneDBRepo;
import tcp.MessageHandler;
import tcp.TcpServer;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Server starting...");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("config");

        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        Repository<Integer, Player> playerRepository = new PlayerDBRepo();
        Repository<Integer, Game> gameRepository = new GameDBRepo();
        Repository<Integer, Zone> zoneRepository = new ZoneDBRepo();
        Repository<Integer, Computer> computerRepository = new ComputerDBRepo();

        ServicePlayer servicePlayer = context.getBean(ServicePlayer.class);
        ServiceZone serviceZone = context.getBean(ServiceZone.class);
//        ServicePlayer servicePlayer = new ServicePlayerImpl(playerRepository, gameRepository,executorService);
//        ServiceZone serviceZone = new ServiceZoneImpl(zoneRepository, computerRepository,executorService);

        MessageHandler messageHandler = new MessageHandler(servicePlayer, serviceZone);
        TcpServer tcpServer = new TcpServer(executorService , 1234);
        tcpServer.addHandler("add player", messageHandler::addPlayer);
        tcpServer.addHandler("remove player", messageHandler::removePlayer);
        tcpServer.addHandler("update player", messageHandler::updatePlayer);
        tcpServer.addHandler("list players", messageHandler::getAllPlayers);
        tcpServer.addHandler("add zone", messageHandler::addZone);
        tcpServer.addHandler("remove zone", messageHandler::removeZone);
        tcpServer.addHandler("update zone", messageHandler::updatePlayer);
        tcpServer.addHandler("list zones", messageHandler::getAllZones);
        tcpServer.addHandler("add game", messageHandler::addGame);
        tcpServer.addHandler("remove game", messageHandler::removeGame);
        tcpServer.addHandler("update game", messageHandler::updateGame);
        tcpServer.addHandler("list games", messageHandler::getAllGames);
        tcpServer.addHandler("add computer", messageHandler::addComputer);
        tcpServer.addHandler("remove computer", messageHandler::removeComputer);
        tcpServer.addHandler("update computer", messageHandler::updateComputer);
        tcpServer.addHandler("list computers", messageHandler::getAllComputers);


        System.out.println("Server started");
        tcpServer.startServer();
        executorService.shutdown();
        System.out.println("bye server!");

    }
}
