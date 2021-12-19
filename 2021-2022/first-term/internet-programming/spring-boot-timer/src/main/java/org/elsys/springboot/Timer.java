package org.elsys.springboot;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer {
    private final String name;
    private String hours;
    private String minutes;
    private String seconds;
    private String time;
    private String done;
    private LocalDateTime timeToStop;

    public Timer(String name, String time, String hours, String minutes, String seconds) {
        this.name = name;
        this.time = time;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.done = "no";
    }

    void begin() throws Exception {
        if (name == null) {
            throw new Exception("Name can't be null");
        }
        if (time != null) {
            if (hours != null || minutes != null || seconds != null) {
                throw new Exception("You can't have time and hours/minutes/seconds");
            } else {
                String[] hoursMinutesSeconds = time.split(":");
                if (hoursMinutesSeconds.length != 3) {
                    throw new Exception("Invalid time format");
                }
                LocalTime sumUp = LocalTime.ofSecondOfDay(Long.parseLong(hoursMinutesSeconds[0])*3600+Long.parseLong(hoursMinutesSeconds[1])*60+Long.parseLong(hoursMinutesSeconds[2]));
                this.timeToStop = LocalDateTime.now().plusHours(sumUp.getHour()).plusMinutes(sumUp.getMinute()).plusSeconds(sumUp.getSecond());
                time = sumUp.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
        } else {
            if (hours == null && minutes == null && seconds == null) {
                throw new Exception("You need to have time or hours/minutes/seconds");
            } else {
                if (hours == null) {
                    hours = "0";
                }
                if (minutes == null) {
                    minutes = "0";
                }
                if (seconds == null) {
                    seconds = "0";
                }
                LocalTime sumUp = LocalTime.ofSecondOfDay(Long.parseLong(hours)*3600+Long.parseLong(minutes)*60+Long.parseLong(seconds));
                this.timeToStop = LocalDateTime.now().plusHours(sumUp.getHour()).plusMinutes(sumUp.getMinute()).plusSeconds(sumUp.getSecond());
                time = sumUp.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
        }
    }

    public String getDone() {
        getTime();
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getTimerName() {
        return name;
    }

    public String getTime() {
        if (timeToStop.isBefore(LocalDateTime.now())) {
            this.time = LocalTime.of(0,0,0).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            this.setDone("yes");
        } else {
            LocalDateTime timeLeft = timeToStop.minusHours(LocalTime.now().getHour()).minusMinutes(LocalTime.now().getMinute()).minusSeconds(LocalTime.now().getSecond());
            this.time = timeLeft.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        return time;
    }
}