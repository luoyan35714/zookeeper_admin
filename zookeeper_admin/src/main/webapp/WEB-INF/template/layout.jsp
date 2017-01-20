<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 
		<title><tiles:insertAttribute name="title" /></title>
		 
		<!-- Bootstrap core CSS -->
		 
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
		 
		<link href="${pageContext.request.contextPath}/resources/fonts/css/font-awesome.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/animate.min.css" rel="stylesheet">
		 
		<!-- Theme styling -->
		 
		<link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet">
		 
		<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		 
		<!--[if lt IE 9]>
		<script src="${pageContext.request.contextPath}/resources/js/ie8-responsive-file-warning.js"></script>
		<![endif]-->
		 
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="${pageContext.request.contextPath}/resources/js/html5shiv.min.js"></script>
		  <script src="${pageContext.request.contextPath}/resources/js/respond.min.js"></script>
		<![endif]-->
	
	</head>
	
	<body class="nav-md">
	
	
	    <div class="container body">
	        <div class="main_container">
				<tiles:insertAttribute name="left_tree" />
				<tiles:insertAttribute name="header" />
				<tiles:insertAttribute name="body" />
<%-- 				<tiles:insertAttribute name="footer" /> --%>
			</div>
		</div>
		
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	
	    <!-- chart js -->
	    <script src="${pageContext.request.contextPath}/resources/js/chartjs/chart.min.js"></script>
	    <!-- bootstrap progress js -->
	    <script src="${pageContext.request.contextPath}/resources/js/progressbar/bootstrap-progressbar.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/nicescroll/jquery.nicescroll.min.js"></script>
	    <!-- icheck -->
	    <script src="${pageContext.request.contextPath}/resources/js/icheck/icheck.min.js"></script>
	
	    <script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
	
	    <!-- chart js -->
	    <script src="${pageContext.request.contextPath}/resources/js/chartjs/chart.min.js"></script>
	    <!-- sparkline -->
	    <script src="${pageContext.request.contextPath}/resources/js/sparkline/jquery.sparkline.min.js"></script>
	    <!-- easypie -->
	    <script src="${pageContext.request.contextPath}/resources/js/easypie/jquery.easypiechart.min.js"></script>
	</body>
</html>
