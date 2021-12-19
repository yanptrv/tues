package org.elsys.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@RestController
public class TimerResource {

    ArrayList<Timer> timers = new ArrayList<>();

    @PostMapping("/timer")
    public ResponseEntity createTimer(@RequestParam(value = "long", required = false) String wait, @RequestBody Timer timer, @RequestHeader Map<String, String> headers){
        try {
            timer.begin();
            timers.add(timer);
            if (Objects.equals(wait, "true")) {
                for (int i = 0; i < 100; ++i) {
                    timer.getTime();
                    if (timer.getDone().equals("yes")) {
                        break;
                    }
                    Thread.sleep(100);
                }
            }
            if (headers.containsKey("time-format")) {
                String[] hrsMinSec = timer.getTime().split(":");
                if (headers.containsValue("seconds")) {
                    long totalSeconds = Long.parseLong(hrsMinSec[0])*3600 + Long.parseLong(hrsMinSec[1])*60 + Long.parseLong(hrsMinSec[2]);
                    return ResponseEntity.status(201).header("ACTIVE-TIMERS", activeTimers()).body(new PostTimerSeconds(Integer.toString(timers.size()-1), timer.getTimerName(), Long.toString(totalSeconds)));
                } else if (headers.containsValue("hours-minutes-seconds")) {
                    return ResponseEntity.status(201).header("ACTIVE-TIMERS", activeTimers()).body(new PostTimerHoursMinSeconds(Integer.toString(timers.size()-1), timer.getTimerName(), hrsMinSec[0], hrsMinSec[1], hrsMinSec[2]));
                }
            }
            return ResponseEntity.status(201).header("ACTIVE-TIMERS", activeTimers()).body(new PostTimerRecord(Integer.toString(timers.size()-1), timer.getTimerName(), timer.getTime()));
        } catch(Exception e) {
            return ResponseEntity.status(400).header("ACTIVE-TIMERS", activeTimers()).body(e.getMessage());
        }
    }

    @GetMapping("/timer/{id}")
    public ResponseEntity getTimer(@RequestParam(value = "long", required = false) String wait, @PathVariable String id, @RequestHeader Map<String, String> headers) {
        try {
            int timerID = Integer.parseInt(id);

            if (Objects.equals(wait, "true")) {
                for (int i = 0; i < 100; ++i) {
                    timers.get(timerID).getTime();
                    if (timers.get(timerID).getDone().equals("yes")) {
                        break;
                    }
                    Thread.sleep(100);
                }
            }
            if (headers.containsKey("time-format")) {
                String[] hrsMinSec = timers.get(timerID).getTime().split(":");
                if (headers.containsValue("seconds")) {
                    long totalSeconds = Long.parseLong(hrsMinSec[0])*3600 + Long.parseLong(hrsMinSec[1])*60 + Long.parseLong(hrsMinSec[2]);
                    return ResponseEntity.status(200).header("ACTIVE-TIMERS", activeTimers()).body(new GetTimerSeconds(Integer.toString(timerID), timers.get(timerID).getTimerName(), Long.toString(totalSeconds), timers.get(timerID).getDone()));
                } else if (headers.containsValue("hours-minutes-seconds")) {
                    return ResponseEntity.status(200).header("ACTIVE-TIMERS", activeTimers()).body(new GetTimerHoursMinSeconds(Integer.toString(timerID), timers.get(timerID).getTimerName(), hrsMinSec[0], hrsMinSec[1], hrsMinSec[2], timers.get(timerID).getDone()));
                }
            }
            return ResponseEntity.status(200).header("ACTIVE-TIMERS", activeTimers()).body(new GetTimerRecord(Integer.toString(timerID), timers.get(timerID).getTimerName(), timers.get(timerID).getTime(), timers.get(timerID).getDone()));
        } catch (Exception e) {
            return ResponseEntity.status(404).header("ACTIVE-TIMERS", activeTimers()).body(e.getMessage());
        }
    }

    public String activeTimers() {
        int count = timers.size();
        for (Timer timer : timers) {
            if (timer.getDone().equals("yes")) {
                count--;
            }
        }
        return Integer.toString(count);
    }
}
