import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new ServerConnection(clientSocket).start();
        }
    }

    static class ServerConnection extends Thread {
        private final Socket clientSocket;

        ServerConnection(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String greeting;
                while ((greeting = in.readLine()) != null) {
                    if ("hello server".equals(greeting)) {
                        out.println("hello client");
                    } else {
                        out.println("unrecognised greeting");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            Server server = new Server();
            try {
                int port = Integer.parseInt(args[0]);
                if (port < 0 || port > 65535) {
                    System.err.println("invalid arguments");
                    System.exit(1);
                }
                server.start(Integer.parseInt(args[0]));
                server.close();
            } catch (BindException e) {
                System.err.println("port is already in use");
                System.exit(2);
            } catch(NumberFormatException e) {
                System.err.println("invalid arguments");
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("invalid arguments");
            System.exit(1);
        }
    }
}