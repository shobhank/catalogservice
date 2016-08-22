package org.readme.exception;
/**
 *
 * @author shsharma
 */
public class BadRequestException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public BadRequestException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public BadRequestException() {
		super();
	}
}
