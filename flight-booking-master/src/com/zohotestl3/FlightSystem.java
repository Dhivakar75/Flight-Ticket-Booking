package com.zohotestl3;

import java.util.ArrayList;
import java.util.List;

public class FlightSystem {
    public static Flight registerNewFlight(String id) {
        return new Flight(id);
    }

    public static void bookTicket(String flightId, String seatClass, int totalSeatsToBook, boolean isMealPreferred) {
        Flight flight = Flight.getFlightWithId(flightId);
		if (flight.getNoOfEmptySeats(seatClass) >= totalSeatsToBook) {
			List<Seat> bookedSeats = new ArrayList<>();
			List<Seat> seats = flight.getSeats();
			int cnt = 0;
			System.out.print("Booking seats: ");
			for (Seat seat : seats) {
				if (seat.getSeatClass().equals(seatClass) && !seat.isBooked()) {
					System.out.print(seat.getSeatNo() + ", ");
					seat.setBooked(true);
					if (isMealPreferred) {
						seat.setMealPreferred(true);
					}
					bookedSeats.add(seat);
					cnt++;
					if (cnt == totalSeatsToBook) {
						break;
					}
				}
			}
			Booking booking = new Booking(flight, bookedSeats, seatClass, isMealPreferred);
			List<Booking> flightBookings = flight.getBookings();
			flightBookings.add(booking);
			if (seatClass.equals("BC")) {
				flight.setBcSeatPrice(flight.getBcSeatPrice() + 200);
			} else {
				flight.setEcSeatPrice(flight.getEcSeatPrice() + 100);
			}
			System.out.println("\nBooking successful! Your Booking Id: " + booking.getId() + ", Total price: "
					+ booking.getTotalPrice());
		} else {
            System.out.println("Booking failed! Not enough seats available.");
        }
    }

    public static void cancelTicket(int bookingId) {
        Booking booking = Booking.getBookingWithId(bookingId);
        List<Seat> bookedSeats = booking.getBookedSeats();
        System.out.print("Cancelling seats: ");
        for (Seat seat: bookedSeats) {
            System.out.print(seat.getSeatNo() + ", ");
            seat.setBooked(false);
        }
        booking.setTotalPrice(booking.getBookedSeats().size() * 200);
        System.out.println("\nCancellation successful.");
        booking.setCanceled(true);
    }
}
