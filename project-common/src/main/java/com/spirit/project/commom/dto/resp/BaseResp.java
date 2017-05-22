package com.spirit.project.commom.dto.resp;

/**
 * 请求返回基类
 * 
 * @author dante
 *
 * @param <T>
 */
public class BaseResp<T> {
	
	private int resultCode = RespCodeEnum.SUCCESS.code();
	private T data;
	private String errorMsg;
	
	public BaseResp() {
		// 默认构造函数
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
