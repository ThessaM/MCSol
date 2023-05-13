package com.example.a2501974391_uts_mcs.Model;

public class Ticket {
    Integer id;
    String name;
    String venue;
    String time;
    String date;

    public Ticket() {
        //empty
    }

    public Ticket(Integer id, String name, String venue, String time, String date) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.time = time;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
