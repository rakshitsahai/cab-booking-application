package com.springboot.cab_booking.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.cab_booking.entity.Driver;
import com.springboot.cab_booking.entity.User;
import com.springboot.cab_booking.exception.NoDriverFoundException;
import com.springboot.cab_booking.exception.NoUserFoundException;

@Service
public class CabBookingServiceImpl implements CabBookingService {
	
	private Map<String, User> users = new HashMap<>();
    private Map<String, Driver> drivers = new HashMap<>();
    
    public CabBookingServiceImpl() {
    	users.put("Abhishek", new User("Abhishek", 'M', 23));
    	users.put("Rahul", new User("Rahul", 'M', 29));
    	users.put("Nandini", new User("Nandini", 'F', 22));
    	
    	drivers.put("Driver1", new Driver("Driver1", 'M', 22, "Swift", "KA-01-12345", new int[] {10, 1}, true));
    	drivers.put("Driver2", new Driver("Driver2", 'M', 29, "Swift", "KA-01-12345", new int[] {11, 10}, true));
    	drivers.put("Driver3", new Driver("Driver3", 'M', 24, "Swift", "KA-01-12345", new int[] {5, 3}, true));    	
    }
    
    @Override
    public List<User> showAllUsers() {
		Collection<User> values = users.values();
		return new ArrayList<>(values);
	}

    @Override
    public String addUser(User userDetails) {
    	String name = userDetails.getName();

        if (users.containsKey(name)) {
            throw new IllegalArgumentException("User already exists");
        }

        users.put(name, userDetails);
        return "User added successfully";
    }
    
    @Override
	public List<Driver> showAllDrivers() {
		Collection<Driver> values = drivers.values();
		return new ArrayList<>(values);
	}

    @Override
    public String addDriver(Driver driverDetails) {
        
        String name = driverDetails.getName();

        if (drivers.containsKey(name)) {
            throw new IllegalArgumentException("Driver already exists");
        }

        drivers.put(name, driverDetails);
        return "Driver added successfully";
    }

    @Override
    public List<String> findRide(String username, @RequestParam int sourceX, @RequestParam int sourceY, @RequestParam int destinationX, @RequestParam int destinationY) throws NoUserFoundException {
        if (!users.containsKey(username)) {
            throw new NoUserFoundException("User not found");
        }

        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers.values()) {
        	int driverX = driver.getCurrentLocation()[0];
        	int driverY = driver.getCurrentLocation()[1];
            if (driver.isAvailable() && calculateDistance(sourceX, sourceY, driverX, driverY) <= 5) {
                availableDrivers.add(driver);
            }
        }

        availableDrivers.sort(Comparator.comparingInt(driver -> calculateDistance(sourceX, sourceY, driver.getCurrentLocation()[0], driver.getCurrentLocation()[1])));

        List<String> result = new ArrayList<>();
        for (Driver driver : availableDrivers) {
            result.add(driver.getName());
        }

        return result.isEmpty() ? List.of("No ride found") : result;
    }

    @Override
    public String chooseRide(String username, String driverName) throws NoUserFoundException, NoDriverFoundException {
        if (!users.containsKey(username)) {
            throw new NoUserFoundException("User not found");
        }
        if (!drivers.containsKey(driverName)) {
            throw new NoDriverFoundException("Driver not found");
        }

        Driver driver = drivers.get(driverName);
        if (!driver.isAvailable()) {
        	throw new IllegalArgumentException("Driver not available");
        }
        driver.setAvailable(false);
        return new String(driver.getName() + " is arriving at your location soon");
        
    }

    private int calculateDistance(int point1X, int point1Y, int point2X, int point2Y) {
    	double x = Math.pow(Math.abs(point1X - point2X), 2);
    	double y = Math.pow(Math.abs(point1Y - point2Y), 2);
        return (int) Math.sqrt(x + y);
    }

}
