package tcp;

import domain.entities.Message;
import domain.entities.Player;
import exceptions.ServiceException;
import service.ServicePlayer;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;

public class TcpClient {
    private ExecutorService executorService;

    public TcpClient(ExecutorService es){
        this.executorService = es;
    }

    public Message sendAndReceive(Message request){
        try (var socket = new Socket(Message.HOST, Message.PORT);
             var is = socket.getInputStream();
             var os = socket.getOutputStream()) {
            System.out.println("sending request: " + request);
            request.writeTo(os);
            System.out.println("request sent");

            Message response = new Message();
            response.readFrom(is);
            System.out.println("received response: " + response);

            return response;

        } catch (IOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

}
