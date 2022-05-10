//package config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.remoting.rmi.RmiProxyFactoryBean;
//import service.ServicePlayer;
//import service.ServicePlayerClient;
//import service.ServiceZone;
//import service.ServiceZoneClient;
//import ui.ClientConsole;
//
//@Configuration
//public class ClientConfig {
//    @Bean
//    RmiProxyFactoryBean rmiProxyFactoryBean(){
//        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
//        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/ServicePlayer");
//        rmiProxyFactoryBean.setServiceInterface(ServicePlayer.class);
//        return rmiProxyFactoryBean;
//    }
//
//    @Bean
//    ClientConsole ClientConsole(){
//        return new ClientConsole(servicePlayerClient(), serviceZoneClient());
//    }
//
//    @Bean
//    ServicePlayer servicePlayerClient(){
//        return new ServicePlayerClient();
//    }
//
//    @Bean
//    ServiceZone serviceZoneClient(){return  new ServiceZoneClient(); }
//
//}
