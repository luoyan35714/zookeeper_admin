<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<section id="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center top_50_margin">
				<h2>登录</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<form action="${pageContext.request.contextPath}/j_spring_security_check" id="user_loginForm" novalidate method="post">
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>用户名</label> <input type="text" class="form-control"
								placeholder="用户名" id="j_username" name="j_username" required
								data-validation-required-message="用户名错误！">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>密码</label> <input type="password"
								class="form-control" placeholder="密码" id="j_password" name="j_password"
								required
								data-validation-required-message="密码错误！">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="container form-group col-xs-12">
							<button type="submit" class="btn btn-green btn-lg"><span>登录</span></button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>