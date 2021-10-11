package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import operations.*;

public class Server {

    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Connected(clientSocket).start();
        }
    }

    static class Connected extends Thread {
        private final Socket clientSocket;

        public Connected(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OperationFactory operationFactory = new OperationFactory();
                String operationArguments;
                while ((operationArguments = in.readLine()) != null) {
                    try {
                        Operation operation = operationFactory.getOperation(operationArguments);
                        System.out.println(operation.perform());
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(6666);
    }
}
