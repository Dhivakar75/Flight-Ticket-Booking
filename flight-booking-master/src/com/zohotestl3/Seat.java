package com.zohotestl3;

public class Seat {

    private int seatNo;
    private final Flight flight;
    private boolean isBooked;
    private boolean isMealPreferred;

    public boolean isMealPreferred() {
        return isMealPreferred;
    }

    public void setMealPreferred(boolean mealPreferred) {
        isMealPreferred = mealPreferred;
    }

    public String getSeatClass() {
        return (seatNo <= 6) ? "BC" : "EC";
    }

    public int getSeatNo() {
        return seatNo;
    }

    public Flight getFlight() {
        return flight;
    }

    public boolean isBooked() {
        return isBooked;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatNo=" + seatNo +
                ", flight=" + flight.toString() +
                ", isBooked=" + isBooked +
                '}';
    }

    public Seat(int seatNo, Flight flight) {
        this.seatNo = seatNo;
        this.flight = flight;
        isBooked = false;
        isMealPreferred = false;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

}
