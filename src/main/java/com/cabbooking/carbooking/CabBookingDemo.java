package com.cabbooking.carbooking;

import com.cabbooking.carbooking.Service.RideService;
import com.cabbooking.carbooking.model.Driver;
import com.cabbooking.carbooking.model.Location;

import java.util.List;

public class CabBookingDemo {
	public static void main(String[] args) {
		RideService rideService = new RideService();

		// Onboarding Users
		rideService.addUser("Abhishek", "M", 23);
		rideService.addUser("Rahul", "M", 29);
		rideService.addUser("Nandini", "F", 22);

		// Onboarding Drivers
		rideService.addDriver("Driver1", "M", 22, "Swift, KA-01-12345", new Location(10, 1));
		rideService.addDriver("Driver2", "M", 29, "Swift, KA-01-12345", new Location(11, 10));
		rideService.addDriver("Driver3", "M", 24, "Swift, KA-01-12345", new Location(5, 3));
		// Finding rides concurrently using separate threads for each user.Each thread
		// represents a user trying to find and book a ride concurrently.I have used
		// multiple to show how multiple users interacting with the car booking system
		// at the same time.

		Thread user1 = new Thread(() -> {
			System.out.println("Finding ride for Abhishek: ");
			List<Driver> availableDrivers = rideService.findRide("Abhishek", new Location(0, 0), new Location(20, 1));
			if (availableDrivers != null && !availableDrivers.isEmpty()) {
				rideService.chooseRide("Abhishek", availableDrivers.get(0).getName());
			}
		});

		Thread user2 = new Thread(() -> {
			System.out.println("Finding ride for Rahul: ");
			List<Driver> availableDrivers = rideService.findRide("Rahul", new Location(10, 0), new Location(15, 3));
			if (availableDrivers != null && !availableDrivers.isEmpty()) {
				rideService.chooseRide("Rahul", availableDrivers.get(0).getName());
			}
		});
		Thread user3 = new Thread(() -> {
			System.out.println("Finding ride for Nandini: ");
			List<Driver> availableDrivers = rideService.findRide("Nandini", new Location(15, 6), new Location(20, 4));
			if (availableDrivers != null && !availableDrivers.isEmpty()) {
				rideService.chooseRide("Nandini", availableDrivers.get(0).getName());
			}
		});
		Thread user4 = new Thread(() -> {
			System.out.println("Finding ride for John: ");
			List<Driver> availableDrivers = rideService.findRide("John", new Location(12, 9), new Location(16, 12));
			if (availableDrivers != null && !availableDrivers.isEmpty()) {
				rideService.chooseRide("John", availableDrivers.get(0).getName());
			}
		});

		user1.start();
		user2.start();
		user3.start();
		user4.start();
	}
}
