<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="right_col">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>${zkinstance.ip }: ${zkinstance.port }<small>权限</small></h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
               <div class="x_panel">
                 <div class="x_title">
                   <h2>权限列表<small>digest</small>
                   <a href="${pageContext.request.contextPath}/zk/instance/auth/add?instanceId=${zkinstance.id}" class="btn btn-primary">ADD</a>
                   </h2>
                   <div class="clearfix"></div>
                 </div>
                 <div class="x_content">
                   <table class="table table-hover">
                     <thead>
                       <tr>
                         <th>#</th>
                         <th>AUTH</th>
                         <th>PASS</th>
                         <th>UPDATE</th>
                         <th>DELETE</th>
                       </tr>
                     </thead>
                     <tbody>
                     	<c:forEach items="${zkauths }" var="zkauth" varStatus="status">
	                       <tr>
	                         <th scope="row">${status.count }</th>
	                         <td>${zkauth.auth }</td>
	                         <td>${zkauth.pass }</td>
	                         <td><a href="${pageContext.request.contextPath}/zk/instance/auth/edit?id=${zkauth.id }&instanceId=${zkinstance.id}" class="btn btn-success">UPDATE</a></td>
                         	 <td><a href="${pageContext.request.contextPath}/zk/instance/auth/delete?id=${zkauth.id }&instanceId=${zkinstance.id}" class="btn btn-warning">DELETE</a></td>
	                       </tr>
                       </c:forEach>
                     </tbody>
                   </table>
                 </div>
               </div>
             </div>
		</div>
	</div>
</div>
