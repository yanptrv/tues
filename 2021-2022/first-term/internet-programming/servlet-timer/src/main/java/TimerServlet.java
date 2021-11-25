import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class TimerServlet extends HttpServlet {
    private final HashSet<Timer> timers = new HashSet<>();

    private class Timer {
        private final String id;
        private final LocalTime startingTime;
        private LocalTime cpyStartingTime;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        private final ArrayList<LocalTime> allLaps;

        public Timer(String id) {
            this.id = id;
            this.startingTime = LocalTime.now();
            this.cpyStartingTime = this.startingTime;
            this.allLaps = new ArrayList<>();

        }

        public String setLap() {
            LocalTime currentLap = LocalTime.now().minusHours(cpyStartingTime.getHour()).minusMinutes(cpyStartingTime.getMinute()).minusSeconds(cpyStartingTime.getSecond());
            allLaps.add(currentLap);
            cpyStartingTime = LocalTime.now();
            return format.format(currentLap);
        }

        public String stop() {
            LocalTime sumTime = LocalTime.of(0,0,0);
            StringBuilder returnedValue = new StringBuilder();
            setLap();
            int lapCounter = 0;
            for(LocalTime lap : allLaps) {
                sumTime = sumTime.plusHours(lap.getHour()).plusMinutes(lap.getMinute()).plusSeconds(lap.getSecond());
                ++lapCounter;
                if (lapCounter <= 9) {
                    returnedValue.append(0).append(lapCounter).append(" ").append(format.format(lap)).append(" / ").append(format.format(sumTime)).append("\n");
                } else {
                    returnedValue.append(lapCounter).append(" ").append(format.format(lap)).append(" / ").append(format.format(sumTime)).append("\n");
                }
            }

            return String.valueOf(returnedValue);
        }

        public String getId() {
            return id;
        }

        public String getStartingTime() {
            return format.format(LocalTime.now().minusHours(startingTime.getHour()).minusMinutes(startingTime.getMinute()).minusSeconds(startingTime.getSecond()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String[] full = req.getPathInfo().split("/");
            if (full.length != 2 || !full[1].equals("start")) {
                resp.setStatus(400);
            } else {
                resp.setStatus(201);
                String randomId = UUID.randomUUID().toString();
                timers.add(new Timer(randomId));
                resp.getWriter().println(randomId);
            }
        }catch (NullPointerException | IOException e) {
            resp.setStatus(400);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            boolean noSuchId = true;
            String[] full = req.getPathInfo().split("/");
            if (full.length != 2) {
                resp.setStatus(400);
            } else {
                for (Timer timer : timers) {
                    if (timer.getId().equals(full[1])) {
                        noSuchId = false;
                        resp.setStatus(200);
                        resp.getWriter().println(timer.getStartingTime());
                        break;
                    }
                }
                if (noSuchId) {
                    resp.setStatus(404);
                }
            }
        }catch (NullPointerException | IOException e) {
            resp.setStatus(400);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            boolean noSuchId = true;
            String[] full = req.getPathInfo().split("/");
            if (full.length != 3 || !full[2].equals("lap")) {
                resp.setStatus(400);
            } else {
                for (Timer timer : timers) {
                    if (timer.getId().equals(full[1])) {
                        noSuchId = false;
                        resp.setStatus(200);
                        resp.getWriter().println(timer.setLap());
                        break;
                    }
                }
                if (noSuchId) {
                    resp.setStatus(404);
                }
            }
        }catch (NullPointerException | IOException e) {
            resp.setStatus(400);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            boolean noSuchId = true;
            String[] full = req.getPathInfo().split("/");
            if (full.length != 2) {
                resp.setStatus(400);
            } else {
                for (Timer timer : timers) {
                    if (timer.getId().equals(full[1])) {
                        noSuchId = false;
                        resp.setStatus(200);
                        resp.getWriter().println(timer.stop());
                        break;
                    }
                }
                if (noSuchId) {
                    resp.setStatus(404);
                }
            }
        }catch (NullPointerException | IOException e) {
            resp.setStatus(400);
        }
    }
}
