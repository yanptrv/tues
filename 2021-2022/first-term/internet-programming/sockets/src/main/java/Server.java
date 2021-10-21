import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Connected(clientSocket).start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    static class Connected extends Thread {
        private final Socket clientSocket;

        public Connected(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader asd = new BufferedReader(new InputStreamReader(System.in));
                String greeting;
                while ((greeting = in.readLine()) != null) {
                    System.out.println(greeting);
                    out.println(asd.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void read(Socket clientSocket) throws IOException {

    }

    public static void main(String[] args) throws IOException {
        Server server=new Server();
        server.start(9090);
    }
}
