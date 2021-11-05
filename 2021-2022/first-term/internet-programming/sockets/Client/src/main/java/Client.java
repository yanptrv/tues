import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.TimeZone;

public class Client {
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static boolean serverUp = true;

    private static class ifConnected extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(1000*4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.println("test");
                try {
                    in.readLine();
                } catch (SocketException e) {
                    serverUp = false;
                    System.out.println("server disconnect");
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) {
        out.println(msg);
        String resp = null;
        try {
            resp = in.readLine();
        } catch (SocketException e) {
            System.out.println("server disconnect");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args)  {
        if (args.length == 1) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] full = args[0].split(":");
            String ip = full[0];
            int port = Integer.parseInt(full[1]);
            if (port < 0 || port > 65535){
                System.err.println("invalid arguments");
                System.exit(1);
            }

            Client client = new Client();

            try {
                client.startConnection(ip, port);
                ifConnected tester = new ifConnected();
                tester.start();
                while(serverUp) {
                    String operation = reader.readLine();
                    if (operation.equalsIgnoreCase("quit") || operation.equalsIgnoreCase("exit")) {
                        client.stopConnection();
                        System.out.println("server disconnect");
                        System.exit(0);
                    } else if (operation.equalsIgnoreCase("time")) {
                        operation = operation + " - " + TimeZone.getDefault().getRawOffset() / 1000 / 60 / 60;
                    }
                    System.out.println(client.sendMessage(operation));
                }
            } catch(UnknownHostException e) {
                System.err.println("invalid host");
                System.exit(3);
            } catch(ConnectException e) {
                System.err.println("connection not possible");
                System.exit(4);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("invalid arguments");
            System.exit(1);
        }
    }
}