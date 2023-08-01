package com.zohotestl3;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;

public class Main {
	
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            FlightSystem.registerNewFlight(fileEntry.getName());
	        }
	    }
	}

	public static void main(String[] args) {

		final File folder = new File("FlightInputs");
		listFilesForFolder(folder);
//        FlightSystem.registerNewFlight(101);
//        FlightSystem.registerNewFlight(102);
		Scanner in = null;
		for (final File fileEntry : folder.listFiles()) {
			String flightId = fileEntry.getName();
			File inputFile = new File("FlightInputs/" + flightId);

			try {
				in = new Scanner(inputFile);
				while (true) {
					String operation = in.next();
					System.out.println("Operation requested: " + (operation.equals("B") ? "Booking" : "Cancellation"));
					if (operation.equals("B")) {
						System.out.println("Flight requested: " + flightId + " (Id)");
						String seatClass = in.next();
						System.out.println("Seat class requested: "
								+ (seatClass.equals("BC") ? "Business Class" : "Economy Class"));
						int totalSeatsToBook = in.nextInt();
						System.out.println("No. of seats requested: " + totalSeatsToBook);
						boolean isMealPreferred = in.next().equals("Y");
						System.out.println("Meal preferred: " + (isMealPreferred ? "Yes" : "No") + "\n");
						FlightSystem.bookTicket(flightId, seatClass, totalSeatsToBook, isMealPreferred);
					} else {
						int bookingId = in.nextInt();
						System.out.println("Cancellation requested against Booking Id: " + bookingId + "\n");
						FlightSystem.cancelTicket(bookingId);
					}
					System.out.println("--------------------------------------------------------------------------------");
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchElementException e) {
			}

			Booking.bookings.forEach((id, booking) -> {
				Flight flight = booking.getFlight();
				System.out.println("Booking Id: " + id);
				System.out.println("Flight Number: " + flight.getId());
				if (booking.isCanceled()) {
					System.out.println("Cancelled");
				} else {
					if (booking.getSeatClass().equals("BC")) {
						System.out.print("Business Class: ");
					} else {
						System.out.print("Economy Class: ");
					}
					List<Seat> seats = booking.getBookedSeats();
					for (Seat seat : seats) {
						System.out.print(seat.getSeatNo() + ", ");
					}
					System.out.println();
					System.out.println("Meals Required: " + (booking.isMealPreferred() ? "Yes" : "No"));
				}
				System.out.println("Total Cost: " + booking.getTotalPrice() + "\n");
			});

			Flight.flights.forEach((id, flight) -> {
				System.out.println("Summary for Flight Number: " + flight.getId());
				List<Seat> seats = flight.getSeats();
				System.out.print("Seats Booked: ");
				for (Seat seat : seats) {
					if (seat.isBooked()) {
						System.out.print(seat.getSeatNo() + ", ");
					}
				}
				System.out.print("\nMeals Booked: ");
				for (Seat seat : seats) {
					if (seat.isBooked() && seat.isMealPreferred()) {
						System.out.print(seat.getSeatNo() + ", ");
					}
				}
				List<Booking> bookings = flight.getBookings();
				int total = 0;
				for (Booking booking : bookings) {
					total += booking.getTotalPrice();
				}
				System.out.println("\nTotal Cost: " + total);
				System.out.print("Seats Available: ");
				for (Seat seat : seats) {
					if (!seat.isBooked()) {
						System.out.print(seat.getSeatNo() + ", ");
					}
				}
				System.out.println("\n");
			});

		}
	}
}
