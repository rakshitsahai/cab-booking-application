package com.springboot.cab_booking.exception;

public class NoUserFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoUserFoundException(String message) {
		super(message);
	}

}
