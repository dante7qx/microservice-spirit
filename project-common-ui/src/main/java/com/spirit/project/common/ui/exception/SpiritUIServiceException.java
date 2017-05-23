package com.spirit.project.common.ui.exception;

/**
 * 统一UI层异常类
 * 
 * @author dante
 *
 */
public class SpiritUIServiceException extends Exception {

	private static final long serialVersionUID = -2253964864226758398L;

	/**
	 * 构造函数
	 */
	public SpiritUIServiceException() {
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
	public SpiritUIServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 * @param cause
	 */
	public SpiritUIServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 */
	public SpiritUIServiceException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param cause
	 */
	public SpiritUIServiceException(Throwable cause) {
		super(cause);
	}

}
