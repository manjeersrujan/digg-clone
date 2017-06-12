package com.clone.digg.exception;

import java.io.Serializable;


/**
 * @author Manjeer
 *
 * Created on Jun 10, 2017
 * 
 * Generic Error format
 */
public class DiggCloneServiceError implements Serializable {

	/**
	 * 
	 */
	public DiggCloneServiceError() {
		super();
	}

	/**
	 * @param status
	 * @param errorMessage
	 */
	public DiggCloneServiceError(int status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -9161253911517761782L;
	int status;
	String errorMessage;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static DiggCloneServiceError getGenericError() {
		return new DiggCloneServiceError(500, "Internal Server Error");
	}

}
