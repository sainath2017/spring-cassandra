package com.sai.dto;

import lombok.Data;

/**
 * Error message is to represent an error in any step or during validations.
 *
 * @author sainath
 */
@Data
public class ErrorMessage {

	private String message;

	private String field;

	private String id;

	private Object fieldValue;

	public ErrorMessage() {
	}

	/**
	 * Create ErrorMessage with just a message String.
	 *
	 * @param message description of error.
	 */
	public ErrorMessage(String message) {
		this.message = message;
	}

	/**
	 * Create ErrorMessage with a message String and the name of the related Field
	 * in java class.
	 *
	 * @param message description of error
	 * @param field   name of the Field in java class.
	 */
	public ErrorMessage(String message, String field) {
		this.message = message;
		this.field = field;
	}

	/**
	 * Create ErrorMessage with a message String, name of the related Field in java
	 * class and id of the respective java class.
	 *
	 * @param message description of error
	 * @param field   name of the Field in java class.
	 * @param id      context id to identify the record if list of records are
	 *                validated
	 */
	public ErrorMessage(String message, String field, String id) {
		this(message, field);
		this.id = id;
	}

	/**
	 * @param message
	 * @param field
	 * @param fieldValue
	 */
	public ErrorMessage(String message, String field, Object fieldValue) {
		this(message, field);
		this.fieldValue = fieldValue;
	}

	/**
	 * @param message
	 * @param field
	 * @param fieldValue
	 * @param id
	 */
	public ErrorMessage(String message, String field, Object fieldValue, String id) {
		this(message, field, fieldValue);
		this.id = id;
	}

	public static ErrorMessage of(String message) {
		return new ErrorMessage(message);
	}

	public static ErrorMessage of(String message, String field) {
		return new ErrorMessage(message, field);
	}

	public static ErrorMessage of(String message, String field, String id) {
		return new ErrorMessage(message, field, id);
	}

	public static ErrorMessage of(String message, String field, Object fieldValue) {
		return new ErrorMessage(message, field, fieldValue);
	}

	public static ErrorMessage of(String message, String field, Object fieldValue, String id) {
		return new ErrorMessage(message, field, fieldValue, id);
	}

}
