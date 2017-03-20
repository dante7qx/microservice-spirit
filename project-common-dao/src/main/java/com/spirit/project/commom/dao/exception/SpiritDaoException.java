package com.spirit.project.commom.dao.exception;

public class SpiritDaoException extends Exception {

	private static final long serialVersionUID = -2253964864226758398L;

	public SpiritDaoException() {
		super();
	}

	public SpiritDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SpiritDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpiritDaoException(String message) {
		super(message);
	}

	public SpiritDaoException(Throwable cause) {
		super(cause);
	}

}
