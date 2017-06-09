package com.freud.zkadmin.business.zk.bean;

import java.sql.Timestamp;
import java.util.List;

public class ZkInstanceBean {

	private int id;
	private String name;
	private String ip;
	private int port;
	private int use;
	private Timestamp operateDate;

	private List<ZkAuth> zkAuths;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getUse() {
		return use;
	}

	public void setUse(int use) {
		this.use = use;
	}

	public Timestamp getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Timestamp operateDate) {
		this.operateDate = operateDate;
	}

	/**
	 * @return List<ZkAuth> zkAuths.
	 */
	public List<ZkAuth> getZkAuths() {
		return zkAuths;
	}

	/**
	 * @param zkAuths
	 *            The zkAuths to set.
	 */
	public void setZkAuths(List<ZkAuth> zkAuths) {
		this.zkAuths = zkAuths;
	}

}
