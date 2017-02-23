package com.freud.zkadmin.business.zk.vo;

import java.sql.Timestamp;

import com.freud.zkadmin.framework.util.DateUtil;

public class ZkNodeInfo {

	private String text;
	private long cZxid;
	private long ctime;
	private long mZxid;
	private long mtime;
	private long pZxid;
	private int cVersion;
	private int dataVersion;
	private int aclVersion;
	private long ephemeralOwner;
	private int dataLength;
	private int numChildren;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getcZxid() {
		return cZxid;
	}

	public void setcZxid(long cZxid) {
		this.cZxid = cZxid;
	}

	public String getCtime() {
		return DateUtil.timestamp2String(new Timestamp(ctime), DateUtil.PATTERN_STANDARD);
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	public long getmZxid() {
		return mZxid;
	}

	public void setmZxid(long mZxid) {
		this.mZxid = mZxid;
	}

	public String getMtime() {
		return DateUtil.timestamp2String(new Timestamp(mtime), DateUtil.PATTERN_STANDARD);
	}

	public void setMtime(long mtime) {
		this.mtime = mtime;
	}

	public long getpZxid() {
		return pZxid;
	}

	public void setpZxid(long pZxid) {
		this.pZxid = pZxid;
	}

	public int getcVersion() {
		return cVersion;
	}

	public void setcVersion(int cVersion) {
		this.cVersion = cVersion;
	}

	public int getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(int dataVersion) {
		this.dataVersion = dataVersion;
	}

	public int getAclVersion() {
		return aclVersion;
	}

	public void setAclVersion(int aclVersion) {
		this.aclVersion = aclVersion;
	}

	public long getEphemeralOwner() {
		return ephemeralOwner;
	}

	public void setEphemeralOwner(long ephemeralOwner) {
		this.ephemeralOwner = ephemeralOwner;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public int getNumChildren() {
		return numChildren;
	}

	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}

}
