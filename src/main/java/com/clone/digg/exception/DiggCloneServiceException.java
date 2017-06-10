package com.clone.digg.exception;

/**
 * @author Manjeer
 *
 * Created on Jun 10, 2017
 */
public class DiggCloneServiceException extends Exception {

	int statusCode;

	/**
	 * @param message
	 */
	public DiggCloneServiceException(String message) {
		super(message);
	}

	/**
	 * @param error
	 */
	public DiggCloneServiceException(DiggCloneServiceError error) {
		super(error.errorMessage);
	}

	/**
	 * @param ex
	 */
	public DiggCloneServiceException(Throwable ex) {
		super(ex);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2651790075167593475L;

}
