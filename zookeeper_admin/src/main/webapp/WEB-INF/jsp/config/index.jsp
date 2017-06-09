<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="right_col">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>实例列表</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
               <div class="x_panel">
                 <div class="x_title">
                   <h2>Zookeeper<small>列表</small></h2>
                   <div class="clearfix"></div>
                 </div>
                 <div class="x_content">
                   <table class="table table-hover">
                     <thead>
                       <tr>
                         <th>#</th>
                         <th>Name</th>
                         <th>IP</th>
                         <th>PORT</th>
                         <th>IN USE</th>
                         <th>LAST OPERATE TIME</th>
                         <th>DTAIL</th>
                         <th>UPDATE</th>
                         <th>DELETE</th>
                       </tr>
                     </thead>
                     <tbody>
                     	<c:forEach items="${zkinstances }" var="zkinstance" varStatus="status">
	                       <tr>
	                         <th scope="row">${status.count }</th>
	                         <td>${zkinstance.name }</td>
	                         <td>${zkinstance.ip }</td>
	                         <td>${zkinstance.port }</td>
	                         <td>${zkinstance.use=='1'?'是':'否' }</td>
	                         <td>${zkinstance.operateDate }</td>
	                         <td><a href="${pageContext.request.contextPath}/zk/config/edit?id=${zkinstance.id }" class="btn btn-primary">DETAIL</a></td>
	                         <td><a href="${pageContext.request.contextPath}/zk/config/edit?id=${zkinstance.id }" class="btn btn-success">UPDATE</a></td>
                         	 <td><a href="${pageContext.request.contextPath}/zk/config/delete?id=${zkinstance.id }" class="btn btn-warning">DELETE</a></td>
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
