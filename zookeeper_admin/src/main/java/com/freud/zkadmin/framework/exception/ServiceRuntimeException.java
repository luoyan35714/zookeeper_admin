package com.freud.zkadmin.framework.exception;

public class ServiceRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 6979177499012244356L;

	public ServiceRuntimeException() {
		super();
	}

	public ServiceRuntimeException(String message) {
		super(message);
	}
}
