package com.spirit.project.common.api.exception;

/**
 * 统一Rest API异常类
 * 
 * @author dante
 *
 */
public class SpiritAPIServiceException extends Exception {

	private static final long serialVersionUID = -2253964864226758398L;

	/**
	 * 构造函数
	 */
	public SpiritAPIServiceException() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SpiritAPIServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 * @param cause
	 */
	public SpiritAPIServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 */
	public SpiritAPIServiceException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param cause
	 */
	public SpiritAPIServiceException(Throwable cause) {
		super(cause);
	}

}
