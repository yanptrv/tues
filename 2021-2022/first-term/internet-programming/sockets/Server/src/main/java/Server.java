import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

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
                String[] commands;
                while ((greeting = in.readLine()) != null) {
                    commands = greeting.split(" ");
                    if ("time".equalsIgnoreCase(commands[0])) {
                        long offset = TimeZone.getDefault().getOffset(new Date().getTime());
                        LocalTime time = LocalTime.now().minusHours(offset / 1000 / 60 / 60);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                        if (commands.length == 3){
                            out.println(formatter.format(time.plusHours(Integer.parseInt(commands[2]))));
                        } else if (commands.length == 2 && commands[1].length() == 6 && (commands[1].charAt(3) == ':') && ((commands[1].charAt(0) == '-') || (commands[1].charAt(0) == '+'))){
                            switch (commands[1]) {
                                case "+14:00" -> out.println(formatter.format(time.plusMinutes(14 * 60)));
                                case "+13:00" -> out.println(formatter.format(time.plusMinutes(13 * 60)));
                                case "+12:45" -> out.println(formatter.format(time.plusMinutes(12 * 60 + 45)));
                                case "+12:00" -> out.println(formatter.format(time.plusMinutes(12 * 60)));
                                case "+11:00" -> out.println(formatter.format(time.plusMinutes(11 * 60)));
                                case "+10:30" -> out.println(formatter.format(time.plusMinutes(10 * 60 + 30)));
                                case "+10:00" -> out.println(formatter.format(time.plusMinutes(10 * 60)));
                                case "+09:30" -> out.println(formatter.format(time.plusMinutes(9 * 60 + 30)));
                                case "+09:00" -> out.println(formatter.format(time.plusMinutes(9 * 60)));
                                case "+08:45" -> out.println(formatter.format(time.plusMinutes(8 * 60 + 45)));
                                case "+08:00" -> out.println(formatter.format(time.plusMinutes(8 * 60)));
                                case "+07:00" -> out.println(formatter.format(time.plusMinutes(7 * 60)));
                                case "+06:30" -> out.println(formatter.format(time.plusMinutes(6 * 60 + 30)));
                                case "+06:00" -> out.println(formatter.format(time.plusMinutes(6 * 60)));
                                case "+05:45" -> out.println(formatter.format(time.plusMinutes(5 * 60 + 45)));
                                case "+05:30" -> out.println(formatter.format(time.plusMinutes(5 * 60 + 30)));
                                case "+05:00" -> out.println(formatter.format(time.plusMinutes(5 * 60)));
                                case "+04:30" -> out.println(formatter.format(time.plusMinutes(4 * 60 + 30)));
                                case "+04:00" -> out.println(formatter.format(time.plusMinutes(4 * 60)));
                                case "+03:30" -> out.println(formatter.format(time.plusMinutes(3 * 60 + 30)));
                                case "+03:00" -> out.println(formatter.format(time.plusMinutes(3 * 60)));
                                case "+02:00" -> out.println(formatter.format(time.plusMinutes(2 * 60)));
                                case "+01:00" -> out.println(formatter.format(time.plusMinutes(60)));
                                case "+00:00" -> out.println(formatter.format(time));
                                case "-01:00" -> out.println(formatter.format(time.minusMinutes(60)));
                                case "-02:00" -> out.println(formatter.format(time.minusMinutes(2 * 60)));
                                case "-03:00" -> out.println(formatter.format(time.minusMinutes(3 * 60)));
                                case "-03:30" -> out.println(formatter.format(time.minusMinutes(3 * 60 + 30)));
                                case "-04:00" -> out.println(formatter.format(time.minusMinutes(4 * 60)));
                                case "-05:00" -> out.println(formatter.format(time.minusMinutes(5 * 60)));
                                case "-06:00" -> out.println(formatter.format(time.minusMinutes(6 * 60)));
                                case "-07:00" -> out.println(formatter.format(time.minusMinutes(7 * 60)));
                                case "-08:00" -> out.println(formatter.format(time.minusMinutes(8 * 60)));
                                case "-09:00" -> out.println(formatter.format(time.minusMinutes(9 * 60)));
                                case "-09:30" -> out.println(formatter.format(time.minusMinutes(9 * 60 + 30)));
                                case "-10:00" -> out.println(formatter.format(time.minusMinutes(10 * 60)));
                                case "-11:00" -> out.println(formatter.format(time.minusMinutes(11 * 60)));
                                case "-12:00" -> out.println(formatter.format(time.minusMinutes(12 * 60)));
                                default -> out.println("invalid time zone");
                            }
                        } else {
                            out.println("invalid input");
                        }
                    } else {
                        out.println("invalid input");
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