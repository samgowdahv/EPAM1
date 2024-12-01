package com.portal.backend.clientinterviewtracker.exceptionhandler;

public class InvalidUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3362768102987946954L;

	public InvalidUserException(String message) {
		super(message);
	}
}
