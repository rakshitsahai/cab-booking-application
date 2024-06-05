package com.springboot.cab_booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.cab_booking.entity.Driver;
import com.springboot.cab_booking.entity.User;
import com.springboot.cab_booking.exception.NoDriverFoundException;
import com.springboot.cab_booking.exception.NoUserFoundException;

@Service
public interface CabBookingService {
	
	public List<User> showAllUsers();

    public String addUser(User userDetails);

    public String addDriver(Driver driverDetails);

    public String chooseRide(String username, String driverName) throws NoUserFoundException, NoDriverFoundException;

	public List<Driver> showAllDrivers();

	public List<String> findRide(String username, int sourceX, int sourceY, int destinationX, int destinationY) throws NoUserFoundException;

}
