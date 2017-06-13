package com.freud.zkadmin.business.zk.bean;

/**
 * @Description: TODO <br>
 * @date 2017年6月9日 上午9:24:17 <br>
 * 
 * @author Freud
 */
public class ZkAuth {

	private int id;
	private int instanceId;
	private String auth;
	private String pass;

	/**
	 * @return int id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return int instanceId.
	 */
	public int getInstanceId() {
		return instanceId;
	}

	/**
	 * @param instanceId
	 *            The instanceId to set.
	 */
	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * @return String auth.
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @param auth
	 *            The auth to set.
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * @return String pass.
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            The pass to set.
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

}
