package com.sai.exception;

public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException() {
		super("Invalid payload");
	}

	public ValidationException(String message) {
		super(message);
	}

}
