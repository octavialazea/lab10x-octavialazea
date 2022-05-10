package config;

import domain.entities.Computer;
import domain.entities.Game;
import domain.entities.Player;
import domain.entities.Zone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import service.ServicePlayer;
import service.ServicePlayerImpl;
import service.ServiceZone;
import service.ServiceZoneImpl;
import storage.Repository;
import repository.ComputerDBRepo;
import repository.GameDBRepo;
import repository.PlayerDBRepo;
import repository.ZoneDBRepo;
import tcp.TcpServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ServerConfig {

    @Bean
    ServicePlayer servicePlayer() { return new ServicePlayerImpl(playerRepository(), gameRepository(), executorService());}
    @Bean
    ServiceZone serviceZone(){return new ServiceZoneImpl(zoneRepository(), computerRepository(), executorService());}
    @Bean
    Repository<Integer, Player> playerRepository(){return new PlayerDBRepo();}
    @Bean
    Repository<Integer, Game> gameRepository(){
        return new GameDBRepo();
    }
    @Bean
    Repository<Integer, Zone> zoneRepository(){
        return new ZoneDBRepo();
    }
    @Bean
    Repository<Integer, Computer> computerRepository(){
        return new ComputerDBRepo();
    }
    @Bean
    ExecutorService executorService(){return Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );}

}
