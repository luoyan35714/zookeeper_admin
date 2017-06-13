package com.freud.zkadmin.business.zk.vo;

/**
 * @Description: TODO <br>
 * @date 2017年6月12日 上午10:03:37 <br>
 * 
 * @author Freud
 */
public class ResponseInfo {

	private int code;
	private Object data;

	/**
	 * @return int code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            The code to set.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return Object data.
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            The data to set.
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
