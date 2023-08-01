package com.zohotestl3;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Flight {
    public static TreeMap<String, Flight> flights = new TreeMap<>();

    private int ecSeatPrice;
    private int bcSeatPrice;
    private final String id;
    private List<Seat> seats;
    private List<Booking> bookings;

    public int getEcSeatPrice() {
        return ecSeatPrice;
    }

    public void setEcSeatPrice(int ecSeatPrice) {
        this.ecSeatPrice = ecSeatPrice;
    }

    public int getBcSeatPrice() {
        return bcSeatPrice;
    }

    public void setBcSeatPrice(int bcSeatPrice) {
        this.bcSeatPrice = bcSeatPrice;
    }

    public Flight(String id) {
        this.id = id;
        this.seats = new ArrayList<>();
        this.ecSeatPrice = 1000;
        this.bcSeatPrice = 2000;
        for (int i = 0; i < 18; i++) {
            this.seats.add(new Seat(i + 1, this));
        }
        this.bookings = new ArrayList<>();
        flights.put(this.id, this);
    }

    public static Flight getFlightWithId(String id) {
        return flights.get(id);
    }

    public int getNoOfEmptySeats(String classType) {
        int cnt = 0;
        for (Seat seat : seats) {
            if (seat.getSeatClass().equals(classType)) {
                cnt += seat.isBooked() ? 0 : 1;
            }
        }
        return cnt;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "ecSeatPrice=" + ecSeatPrice +
                ", bcSeatPrice=" + bcSeatPrice +
                ", id=" + id +
                '}';
    }

    public String getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
