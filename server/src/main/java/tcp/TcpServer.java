package tcp;

import domain.entities.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

public class TcpServer {
    private ExecutorService executorService;
    private int port;
    private Map<String, UnaryOperator<Message>> methodHandlers;

    public TcpServer(ExecutorService executorService, int port){
        this.executorService = executorService;
        this.port = port;
        this.methodHandlers = new HashMap<>();
    }

    public void addHandler(String methodName, UnaryOperator<Message> handler){
        methodHandlers.putIfAbsent(methodName, handler);
    }

    public void startServer() throws IOException {
        try(var serverSocket = new ServerSocket(this.port)){
            System.out.println("Server started. Waiting for clients...");
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected");
                executorService.submit(new ClientHandler(clientSocket));
            }
        }
    }

    private class ClientHandler implements Runnable{
        private Socket socket;

        public ClientHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try (var is = socket.getInputStream();
                 var os = socket.getOutputStream()){
                Message request = new Message();
                request.readFrom(is);
                System.out.println("received request: " + request);

                Message response = methodHandlers.get(request.getHeader()).apply(request);
                System.out.println("computed response: " + response);

                response.writeTo(os);
                System.out.println("response sent to client");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



    }
