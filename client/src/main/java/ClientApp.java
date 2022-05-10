import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ServicePlayer;
import service.ServicePlayerClient;
import service.ServiceZone;
import service.ServiceZoneClient;
import tcp.TcpClient;
import ui.ClientConsole;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ClientApp {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        TcpClient tcpClient = new TcpClient(executorService);
        ServicePlayer servicePlayer = new ServicePlayerClient(executorService, tcpClient);
        ServiceZone serviceZone = new ServiceZoneClient(executorService, tcpClient);
        ClientConsole clientConsole = new ClientConsole(servicePlayer, serviceZone);
        clientConsole.runConsole();

        System.out.println("bye client");
        executorService.shutdown();


    }
}
