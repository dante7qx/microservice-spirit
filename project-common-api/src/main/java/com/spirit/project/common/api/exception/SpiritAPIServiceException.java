package com.spirit.project.common.api.exception;

public class SpiritAPIServiceException extends Exception {

	private static final long serialVersionUID = -2253964864226758398L;

	public SpiritAPIServiceException() {
		super();
	}

	public SpiritAPIServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SpiritAPIServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpiritAPIServiceException(String message) {
		super(message);
	}

	public SpiritAPIServiceException(Throwable cause) {
		super(cause);
	}

}
