package com.zohotestl3;

import java.util.List;
import java.util.TreeMap;

public class Booking {
    public static TreeMap<Integer, Booking> bookings = new TreeMap<>();

    private int id;
    private Flight flight;
    private List<Seat> bookedSeats;
    private String seatClass;
    private boolean isCanceled;
    private boolean isMealPreferred;
    private int totalPrice;
    public Booking(Flight flight, List<Seat> bookedSeats, String seatClass, boolean isMealPreferred) {
        this.id = bookings.size() + 1;
        this.flight = flight;
        this.bookedSeats = bookedSeats;
        this.seatClass = seatClass;
        this.isMealPreferred = isMealPreferred;
        this.totalPrice = calculateTotalPrice();
        bookings.put(this.id, this);
    }

    private int calculateTotalPrice() {
        int pricePerSeat = seatClass.equals("BC") ? flight.getBcSeatPrice() : flight.getEcSeatPrice();
        int totalNoOfSeatsBooked = bookedSeats.size();
        int totalPrice = pricePerSeat * totalNoOfSeatsBooked;
        totalPrice += isMealPreferred ? totalNoOfSeatsBooked * 200 : 0;
        return totalPrice;
    }

    public static Booking getBookingWithId(int bookingId) {
        return bookings.get(bookingId);
    }

    public int getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Seat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        this.isCanceled = canceled;
    }

    public boolean isMealPreferred() {
        return isMealPreferred;
    }

    public void setMealPreferred(boolean mealPreferred) {
        this.isMealPreferred = mealPreferred;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
