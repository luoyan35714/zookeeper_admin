<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="${pageContext.request.contextPath}/resources/js/contact_me.js"></script>

<section id="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center top_50_margin">
				<h2>注册</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<form action="${pageContext.request.contextPath}/auth/register" id="user_registerForm" novalidate method="post">
					<c:if test="${exception!=null }">
						<div>
							<font style="color: red; font-size: 14;">${exception }</font>
						</div>
					</c:if>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>用户名</label> <input type="text" class="form-control"
								placeholder="用户名" id="username" name="username" required>
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>真实姓名</label> <input type="text" class="form-control"
								placeholder="真实姓名" id="realname" name="realname" required>
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>年龄</label> <input type="text" class="form-control"
								placeholder="年龄" id="age" name="age" required>
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>性别</label> 
							<select class="form-control" id="sex" name="sex" required>
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>密码</label> <input type="password"
								class="form-control" placeholder="密码" id="password" name="password"
								required
								data-validation-required-message="请输入正确的密码.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>再次输入密码</label> <input type="password"
								class="form-control" placeholder="再次输入密码" id="passwordAgain" name="passwordAgain"
								required
								data-validation-required-message="请再次输入正确的密码.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-green"><span>注册</span></button>
							<a href="${pageContext.request.contextPath}" class="btn btn-default btn-lg" ><span>放弃</span></a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>