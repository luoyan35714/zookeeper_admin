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
			<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>
							修改权限
						</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<br />
						<form id="demo-form2" action="${pageContext.request.contextPath}/zk/instance/auth/update" method="post" data-parsley-validate class="form-horizontal form-label-left">
							<input type="hidden" name="id" value="${zkauth.id }">
							<input type="hidden" name="instanceId" value="${zkinstance.id }">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">AUTH <span class="required">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="auth" required="required" class="form-control col-md-7 col-xs-12" value="${zkauth.auth }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">PASS <span class="required">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="pass" required="required" class="form-control col-md-7 col-xs-12" value="${zkauth.pass }">
								</div>
							</div>							
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
									<button type="submit" class="btn btn-success">Submit</button>
								</div>
							</div>	
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
