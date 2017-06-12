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
                    	
                    	<a class="btn btn-app" id="btn-detail"><i class="fa fa-pause"></i>Detail</a>
                    	<a class="btn btn-app" id="btn-acl"><i class="fa fa-pause"></i>ACL</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row" id="error_show">
			<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2 style="color:RED;">
							Error<small id="error_show_msg"></small>
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
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
							<li class="dropdown">
		                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
		                        <ul class="dropdown-menu" role="menu">
		                          <li><a href="${pageContext.request.contextPath}/zk/instance/auth/index?instanceId=${zkinstance.id }">设置权限信息</a></li>
		                        </ul>
		                      </li>
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div id="zk_node_tree"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div id="zk_node_add_panel" class="x_panel " >
					<div class="x_title">
						<h2>
							添加或修改节点信息
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form id="demo-form2" action="${pageContext.request.contextPath}/zk/instance/add" method="post" data-parsley-validate class="form-horizontal form-label-left">
							<div class="ln_solid"></div>
							<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Parent Node Name : <span class="required">*</span></label>
                        		<div class="col-md-6 col-sm-6 col-xs-12">
                          			<input type="text" id="parentNodeName" name="parentNodeName" required="required" class="form-control col-md-7 col-xs-12">
                        		</div>
                      		</div>
                      		<div class="form-group">
                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Node Name : <span class="required">*</span></label>
                        		<div class="col-md-6 col-sm-6 col-xs-12">
                          			<input type="text" id="nodeName" name="nodeName" required="required" class="form-control col-md-7 col-xs-12">
                        		</div>
                      		</div>
                      		<div class="form-group">
                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Node Data : </label>
                        		<div class="col-md-6 col-sm-6 col-xs-12">
                          			<textarea rows="5" id="nodeData" name="nodeData" class="form-control col-md-7 col-xs-12"></textarea>
                        		</div>
                      		</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
									<button type="submit" class="btn btn-success">Save</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div id="zk_node_acl_panel" class="x_panel" >
					<div class="x_title">
						<h2>
							ACL信息
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
							<li><a class="close-link-new"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<table class="countries_list">
						</table>
					</div>
				</div>
				<div id="zk_node_detail_panel" class="x_panel">
					<div class="x_title">
						<h2>
							节点详细信息
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
							<li><a class="close-link-new"><i class="fa fa-close"></i></a></li>
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
	buildTree();
	$("#zk_node_add_panel").hide();
	$("#zk_node_acl_panel").hide();
	hideError();
	// 构建节点树
	function buildTree(){
		$.post("${pageContext.request.contextPath}/zk/instance/tree",{id:"${zkinstance.id }"},function(data){
			if(data.code==200){
				$zk_node_tree = $('#zk_node_tree').treeview(
					{
						color: "#428bca",
						data: data.data,
						onNodeSelected: function(event, node){
							selectedNodeId = node.nodeId;
							$.post("${pageContext.request.contextPath}/zk/instance/node",{path:getfullpath(node),id:"${zkinstance.id }"},function(data){
								if(data.code==200){
									showNodeData(data.data);
								}else{
									showError(data);
								}
							});
						}
					});
			}else{
				showError(data);
			}
		});
	}
	
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
	
	$('#btn-add-child').on('click', function (e) {
		var fullPath = getfullpath($zk_node_tree.treeview('getSelected', $zk_node_tree)[0]);
		if(fullPath.length>1){
			fullPath = fullPath.toString().substring(1); 
		}
		$("#parentNodeName").val(fullPath);
		$("#zk_node_add_panel").show();
	});
	
	$('#btn-add-subling').on('click', function (e) {
		var fullPath = getfullpath($zk_node_tree.treeview('getSelected', $zk_node_tree)[0]);
		if(fullPath.length>1){
			fullPath = fullPath.substring(1); 
		}
		if(fullPath.length>1){
			var index = fullPath.lastIndexOf("/");
			if(index==0){
				fullPath = fullPath.substring(0, 1);	
			}else{
				fullPath = fullPath.substring(0, index);
			}
		}
		$("#parentNodeName").val(fullPath);
		$("#zk_node_add_panel").show();
	});
	
	$('#btn-edit').on('click', function (e) {
		var fullPath = getfullpath($zk_node_tree.treeview('getSelected', $zk_node_tree)[0]);
		if(fullPath.length>1){
			fullPath = fullPath.substring(1); 
		}
		if(fullPath.length>1){
			var index = fullPath.lastIndexOf("/");
			if(index==0){
				fullPath = fullPath.substring(0, 1);
			}else{
				fullPath = fullPath.substring(0, index);
			}
		}
		$("#parentNodeName").val(fullPath);
		$("#nodeName").val($zk_node_tree.treeview('getSelected', $zk_node_tree)[0].text);
		$("#zk_node_add_panel").show();
	});
	
	$('#btn-delete').on('click', function (e) {
		var fullPath = getfullpath($zk_node_tree.treeview('getSelected', $zk_node_tree)[0]);
		$.post("${pageContext.request.contextPath}/zk/instance/node",{path:getfullpath(node),id:"${zkinstance.id }"},function(data){
			if(data.code==200){
				showNodeData(data.data);
			}else{
				showError(data);
			}
		});
		buildTree();
	});
	
	$('#btn-detail').on('click', function (e) {
		$.post("${pageContext.request.contextPath}/zk/instance/node",{path:getfullpath($zk_node_tree.treeview('getSelected', $zk_node_tree)[0]),id:"${zkinstance.id }"},function(data){
			if(data.code==200){
				showNodeData(data.data);
			}else{
				showError(data);
			}
		});
	});
	
	$('#btn-acl').on('click', function (e) {
		$.post("${pageContext.request.contextPath}/zk/instance/acl",{path:getfullpath($zk_node_tree.treeview('getSelected', $zk_node_tree)[0]),id:"${zkinstance.id }"},function(data){
			if(data.code==200){
				$("#zk_node_acl_panel").show();
				$("#zk_node_acl_panel .x_content .countries_list").text("");
				$("#zk_node_acl_panel .x_content .countries_list").append('<thead>'
																		+ '<tr>'
																		+ '    <th>Scheme</th>'
																		+ '    <th class="fs15 fw700 text-right">ID</th>'
																		+ '</tr>'
																		+ '</thead>');
				for (var i=0;i<data.data.length;i++)
				{
					$("#zk_node_acl_panel .x_content .countries_list").append('<tr>'
                    										+ '<td>' + data.data[i].id.scheme + '</td>'
                    										+ '<td class="fs15 fw700 text-right">' + data.data[i].id.id + '</td>'
                    										+ '</tr>');
				}
			}else{
				showError(data);
			}
		});
	});
	
	function getSelectedNodeId(){
		if(selectedNodeId == null || selectedNodeId==undefined){
			return $zk_node_tree.treeview('search', [ '/', { ignoreCase: false, exactMatch: true } ]);
		}else{
			return selectedNodeId;
		}
	}
	
	function showNodeData(data){
		$("#zk_node_detail_panel").show();
		
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
	}
	
	function hideError(){
		$("#error_show").hide();
		$("#error_show_msg").text();
	}
	
	function showError(data){
		$("#error_show").show();
		$("#error_show_msg").text(data.data);
	}
	
	$(".close-link-new").click(function(e){
		$(this).parents(".x_panel").hide();
	})
});
</script>