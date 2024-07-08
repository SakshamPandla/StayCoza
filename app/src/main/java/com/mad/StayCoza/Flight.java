package com.mad.StayCoza;
public class Flight {
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private String date;
    private double price;
    private String airline;

    public Flight(String departure, String arrival, String departureTime, String arrivalTime, String duration, String date, double price, String airline) {
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.date = date;
        this.price = price;
        this.airline = airline;
    }

    // Getter methods
    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public String getAirline() {
        return airline;
    }
}
