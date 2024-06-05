package com.springboot.cab_booking.exception;

public class NoDriverFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDriverFoundException(String message) {
		super(message);
	}

}
