package com.devsuperior.bds02.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5668529505519654471L;

	private static final String defaultMsg = "Could not find this resource";

	public ResourceNotFoundException() {
		super(defaultMsg);
	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

	public ResourceNotFoundException(long resourceId) {
		super(defaultMsg + ", by the given ID: " + resourceId);
	}

}
