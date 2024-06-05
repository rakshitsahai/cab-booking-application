package com.springboot.cab_booking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Driver {
	
	private String name;
    private Character gender;
    private int age;
    private String vehicleName;
    private String vehicleNumber;
    private int[] currentLocation;
    private boolean isAvailable;

}
