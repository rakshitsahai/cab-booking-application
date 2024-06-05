package com.springboot.cab_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cab_booking.entity.Driver;
import com.springboot.cab_booking.entity.User;
import com.springboot.cab_booking.exception.NoDriverFoundException;
import com.springboot.cab_booking.exception.NoUserFoundException;
import com.springboot.cab_booking.service.CabBookingService;

@RestController
@RequestMapping("/api")
public class CabBookingController {

    @Autowired
    private CabBookingService cabBookingService;
    
    @GetMapping("/users")
    public List<User> showUsers() {
    	return cabBookingService.showAllUsers();
    }

    @PostMapping("/users")
    public String addUser(@RequestBody User userDetails) {
        return cabBookingService.addUser(userDetails);
    }
    
    @GetMapping("/drivers")
    public List<Driver> showDrivers() {
        return cabBookingService.showAllDrivers();
    }

    @PostMapping("/drivers")
    public String addDriver(@RequestBody Driver driverDetails) {
        return cabBookingService.addDriver(driverDetails);
    }

    @GetMapping("/find_ride")
    public List<String> findRide(@RequestParam String username, @RequestParam int sourceX, @RequestParam int sourceY, @RequestParam int destinationX, @RequestParam int destinationY) throws NoUserFoundException {
        return cabBookingService.findRide(username, sourceX, sourceY, destinationX, destinationY);
    }

    @PostMapping("/choose_ride")
    public String chooseRide(@RequestParam String username, @RequestParam String driverName) throws NoUserFoundException, NoDriverFoundException {
        return cabBookingService.chooseRide(username, driverName);
    }
}
