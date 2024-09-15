package com.cabbooking.carbooking.model;

public class Driver {
    private String name;
    private String gender;
    private int age;
    private String vehicleDetails;
    private Location currentLocation;
    private boolean isAvailable;

    public Driver(String name, String gender, int age, String vehicleDetails, Location currentLocation) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.vehicleDetails = vehicleDetails;
        this.currentLocation = currentLocation;
        this.isAvailable = true;
    }

    
    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getVehicleDetails() {
		return vehicleDetails;
	}


	public void setVehicleDetails(String vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}


	public Location getCurrentLocation() {
		return currentLocation;
	}


	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}


	public boolean isAvailable() {
		return isAvailable;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	@Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", vehicleDetails='" + vehicleDetails + '\'' +
                ", currentLocation=" + currentLocation +
                ", isAvailable=" + isAvailable +
                '}';
    }
    
}
