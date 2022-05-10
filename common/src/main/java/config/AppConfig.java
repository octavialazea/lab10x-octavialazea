package config;

import domain.entities.Computer;
import domain.entities.Game;
import domain.entities.Player;
import domain.entities.Zone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.ServicePlayer;
import storage.Repository;
//import storage.database.ComputerDBRepo;
//import storage.database.GameDBRepo;
//import storage.database.PlayerDBRepo;
//import storage.database.ZoneDBRepo;
//import view.Console;

@Configuration
public class AppConfig {

//    @Bean
//    Console console(){
//        return new Console(servicePlayer(), serviceZone());
//    }

//    @Bean
//    ServicePlayer servicePlayer(){
//        return new ServicePlayer(playerRepository(), gameRepository());
//    }
//
//
//    @Bean
//    ServiceZone serviceZone(){
//        return new ServiceZone(zoneRepository(), computerRepository());
//    }
//
//    @Bean
//    Repository<Integer, Player> playerRepository(){
//        return new PlayerDBRepo();
//    }
//    @Bean
//    Repository<Integer, Game> gameRepository(){
//        return new GameDBRepo();
//    }
//    @Bean
//    Repository<Integer, Zone> zoneRepository(){
//        return new ZoneDBRepo();
//    }
//    @Bean
//    Repository<Integer, Computer> computerRepository(){
//        return new ComputerDBRepo();
//    }
//


}
