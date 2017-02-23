package com.freud.zkadmin.business.zk.vo;

import java.util.List;

public class ZkTreeNode {

	private String text;
	private String href;
	private int[] tags;
	private List<ZkTreeNode> nodes;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int[] getTags() {
		tags = new int[] { getNodes() == null ? 0 : getNodes().size() };
		return tags;
	}

	public void setTags(int[] tags) {
		this.tags = tags;
	}

	public List<ZkTreeNode> getNodes() {
		if (nodes == null || nodes.isEmpty()) {
			return null;
		}
		return nodes;
	}

	public void setNodes(List<ZkTreeNode> nodes) {
		this.nodes = nodes;
	}

}
