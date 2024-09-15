package com.cabbooking.carbooking.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.cabbooking.carbooking.model.Driver;
import com.cabbooking.carbooking.model.Location;
import com.cabbooking.carbooking.model.User;

public class RideService {
    private List<User> users = new CopyOnWriteArrayList<>();
    private List<Driver> drivers = new CopyOnWriteArrayList<>();
    private final Lock driverLock = new ReentrantLock();

    public void addUser(String name, String gender, int age) {
        users.add(new User(name, gender, age));
    }

    public void addDriver(String name, String gender, int age, String vehicleDetails, Location location) {
        drivers.add(new Driver(name, gender, age, vehicleDetails, location));
    }

    public List<Driver> findRide(String username, Location source, Location destination) {
        List<Driver> availableDrivers = new CopyOnWriteArrayList<>();
        for (Driver driver : drivers) {
            driverLock.lock();
            try {
                if (driver.isAvailable() && calculateDistance(source, driver.getCurrentLocation()) <= 5) {
                    availableDrivers.add(driver);
                }
            } finally {
                driverLock.unlock();
            }
        }

        if (availableDrivers.isEmpty()) {
            System.out.println("No ride found for " + username);
            return null;
        } else {
            System.out.println("Available rides for " + username + ": " + availableDrivers);
            return availableDrivers;
        }
    }

    public void chooseRide(String username, String driverName) {
        driverLock.lock();
        try {
            for (Driver driver : drivers) {
                if (driver.getName().equals(driverName)) {
                    if (driver.isAvailable()) {
                        driver.setAvailable(false);
                        System.out.println(username + " has chosen ride with " + driverName);
                        return;
                    } else {
                        System.out.println("Driver " + driverName + " is not available anymore.");
                        return;
                    }
                }
            }
            System.out.println("Driver " + driverName + " not found!");
        } finally {
            driverLock.unlock();
        }
    }

    private int calculateDistance(Location l1, Location l2) {
        return Math.abs(l1.getX() - l2.getX()) + Math.abs(l1.getY() - l2.getY());
    }
}
