package com.freud.zkadmin.business.zk.vo;

/**
 * @Description: TODO <br>
 * @date 2017年6月13日 下午1:52:23 <br>
 * 
 * @author Freud
 */
public class EditNodeRequest {

	private String parentNodeName;
	private String nodeName;
	private String nodeData;
	private Integer id;

	/**
	 * @return String parentNodeName.
	 */
	public String getParentNodeName() {
		return parentNodeName;
	}

	/**
	 * @param parentNodeName
	 *            The parentNodeName to set.
	 */
	public void setParentNodeName(String parentNodeName) {
		this.parentNodeName = parentNodeName;
	}

	/**
	 * @return String nodeName.
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @param nodeName
	 *            The nodeName to set.
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * @return String nodeData.
	 */
	public String getNodeData() {
		return nodeData;
	}

	/**
	 * @param nodeData
	 *            The nodeData to set.
	 */
	public void setNodeData(String nodeData) {
		this.nodeData = nodeData;
	}

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

}
