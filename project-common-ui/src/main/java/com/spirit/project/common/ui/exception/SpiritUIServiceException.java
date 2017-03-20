package com.spirit.project.common.ui.exception;

public class SpiritUIServiceException extends Exception {

	private static final long serialVersionUID = -2253964864226758398L;

	public SpiritUIServiceException() {
		super();
	}

	public SpiritUIServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SpiritUIServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpiritUIServiceException(String message) {
		super(message);
	}

	public SpiritUIServiceException(Throwable cause) {
		super(cause);
	}

}
