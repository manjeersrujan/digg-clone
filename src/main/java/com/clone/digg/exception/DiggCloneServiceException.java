package com.clone.digg.exception;

public class DiggCloneServiceException extends Exception {

	int statusCode;

	public DiggCloneServiceException(String message) {
		super(message);
	}

	public DiggCloneServiceException(DiggCloneServiceError error) {
		super(error.errorMessage);
	}

	public DiggCloneServiceException(Throwable ex) {
		super(ex);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2651790075167593475L;

}
