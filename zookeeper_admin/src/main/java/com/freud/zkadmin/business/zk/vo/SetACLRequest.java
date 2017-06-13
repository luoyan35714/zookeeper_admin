package com.freud.zkadmin.business.zk.vo;

/**
 * @Description: TODO <br>
 * @date 2017年6月13日 下午1:52:04 <br>
 * 
 * @author Freud
 */
public class SetACLRequest {

	private Integer id;

	private String nodePathACL;
	private String acls;

	/**
	 * @return Integer id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return String nodePathACL.
	 */
	public String getNodePathACL() {
		return nodePathACL;
	}

	/**
	 * @param nodePathACL
	 *            The nodePathACL to set.
	 */
	public void setNodePathACL(String nodePathACL) {
		this.nodePathACL = nodePathACL;
	}

	/**
	 * @return String acls.
	 */
	public String getAcls() {
		return acls;
	}

	/**
	 * @param acls
	 *            The acls to set.
	 */
	public void setAcls(String acls) {
		this.acls = acls;
	}

}
