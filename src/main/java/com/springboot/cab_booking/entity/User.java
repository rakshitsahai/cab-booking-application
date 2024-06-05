package com.springboot.cab_booking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
	
	private String name;
	private Character gender;
	private int age;

}
