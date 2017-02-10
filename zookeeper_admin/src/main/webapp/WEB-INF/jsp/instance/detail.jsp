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
						<a class="btn btn-app"><i class="fa fa-edit"></i> Edit</a>
                    	<a class="btn btn-app"><i class="fa fa-play"></i> Play</a>
                    	<a class="btn btn-app"><i class="fa fa-pause"></i> Pause</a>
                    	<a class="btn btn-app"><i class="fa fa-save"></i> Save</a>
                    	<a class="btn btn-app"><span class="badge bg-red">6</span><i class="fa fa-bullhorn"></i> Notifications</a>
                    	<a class="btn btn-app"><i class="fa fa-repeat"></i> Repeat</a>
                    	<a class="btn btn-app"><span class="badge bg-green">211</span><i class="fa fa-users"></i> Users</a>
                    	<a class="btn btn-app"><span class="badge bg-orange">32</span><i class="fa fa-inbox"></i> Orders</a>
                    	<a class="btn btn-app"><span class="badge bg-orange">12</span><i class="fa fa-envelope"></i> Inbox</a>
                    	<a class="btn btn-app"><span class="badge bg-blue">102</span><i class="fa fa-heart-o"></i> Likes</a>
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
						<script type="text/javascript">
						 $.post("${pageContext.request.contextPath}/zk/tree",{id:"${zkinstance.port }"},function(data){
							 $('#zk_node_tree').treeview({color: "#428bca",data: data}); 
						 })
						</script>
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
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
