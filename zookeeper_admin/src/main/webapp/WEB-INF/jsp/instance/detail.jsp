<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="right_col">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>${zkinstance.name } <small>${zkinstance.ip }:${zkinstance.port }</small></h3>
			</div>
		</div>
		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>
							Toolbars
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<a class="btn btn-app" id="btn-expand-node"><i class="fa fa-repeat"></i> Expand Node</a>
						<a class="btn btn-app" id="btn-collapse-node"><i class="fa fa-repeat"></i> Collapse Node</a>
						<a class="btn btn-app" id="btn-toggle-expanded"><i class="fa fa-bullhorn"></i> Toggle Node</a>

						<a class="btn btn-app" id="btn-expand-all"><i class="fa fa-inbox"></i> Expand All</a>
						<a class="btn btn-app" id="btn-collapse-all"><i class="fa fa-inbox"></i> Collapse All</a>
						
						<a class="btn btn-app" id="btn-add-child"><i class="fa fa-edit"></i> Add Child Node</a>
						<a class="btn btn-app" id="btn-add-subling"><i class="fa fa-edit"></i> Add subling Node</a>
                    	<a class="btn btn-app" id="btn-edit"><i class="fa fa-save"></i> Edit nde</a>
                    	<a class="btn btn-app" id="btn-delete"><i class="fa fa-pause"></i> Delete</a>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="x_panel">
					<div class="x_title">
						<h2>
							节点树
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div id="zk_node_tree"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="x_panel">
					<div class="x_title">
						<h2>
							节点详细信息
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<table class="countries_list">
                            <tbody>
                              <tr>
                                <td>DATA</td>
                                <td id="zknode-data" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>cZxid</td>
                                <td id="zknode-czxid" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>ctime</td>
                                <td id="zknode-ctime" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>mZxid</td>
                                <td id="zknode-mzxid" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>mtime</td>
                                <td id="zknode-mtime" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>pZxid</td>
                                <td id="zknode-pzxid" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>cVersion</td>
                                <td id="zknode-cversion" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>dataVersion</td>
                                <td id="zknode-dataversion" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>aclVersion</td>
                                <td id="zknode-aclversion" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>ephemeralOwner</td>
                                <td id="zknode-ephemeralowner" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>dataLength</td>
                                <td id="zknode-datalength" class="fs15 fw700 text-right">0</td>
                              </tr>
                              <tr>
                                <td>numChildren</td>
                                <td id="zknode-numchildren" class="fs15 fw700 text-right">0</td>
                              </tr>
                            </tbody>
                          </table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	var $zk_node_tree;
	var selectedNodeId;
	// 构建节点树
	$.post("${pageContext.request.contextPath}/zk/tree",{id:"${zkinstance.port }"},function(data){
		$zk_node_tree = $('#zk_node_tree').treeview(
			{
				color: "#428bca",
				data: data,
				onNodeSelected: function(event, node){
					selectedNodeId = node.nodeId;
					console.log(getfullpath(node));
					$.post("${pageContext.request.contextPath}/zk/node",{path:getfullpath(node)},function(data){
						$("#zknode-data").text(data.text);
						$("#zknode-czxid").text(data.cZxid);
						$("#zknode-ctime").text(data.ctime);
						$("#zknode-mzxid").text(data.mZxid);
						$("#zknode-mtime").text(data.mtime);
						$("#zknode-pzxid").text(data.pZxid);
						$("#zknode-cversion").text(data.cVersion);
						$("#zknode-dataversion").text(data.dataVersion);
						$("#zknode-aclversion").text(data.aclVersion);
						$("#zknode-ephemeralowner").text(data.ephemeralOwner);
						$("#zknode-datalength").text(data.dataLength);
						$("#zknode-numchildren").text(data.numChildren);
					});
				}
			});
	});
	
	function getfullpath(node){
		if( node.parentId==null || node.parentId==undefined){
			return node.text;
		} else {
			return getfullpath($zk_node_tree.treeview('getNode', node.parentId)) + node.text;
		}
	}
	
	// 为节点树操作的Button添加事件
	$('#btn-expand-node').on('click', function (e) {
		$zk_node_tree.treeview('expandNode', [ getSelectedNodeId(), { silent: true }]);
	});
	
	$('#btn-collapse-node').on('click', function (e) {
		$zk_node_tree.treeview('collapseNode', [ getSelectedNodeId(), { silent: true }]);
	});
	
	$('#btn-toggle-expanded').on('click', function (e) {
		$zk_node_tree.treeview('toggleNodeExpanded', [ getSelectedNodeId(), { silent: true }]);
	});
	
	  // Expand/collapse all
	$('#btn-expand-all').on('click', function (e) {
	    $zk_node_tree.treeview('expandAll', { silent: true });
	});
	
	$('#btn-collapse-all').on('click', function (e) {
		$zk_node_tree.treeview('collapseAll', { silent: true });
	});
	
	function getSelectedNodeId(){
		if(selectedNodeId == null || selectedNodeId==undefined){
			return $zk_node_tree.treeview('search', [ '/', { ignoreCase: false, exactMatch: true } ]);
		}else{
			return selectedNodeId;
		}
	}
});
</script>