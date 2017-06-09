<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="right_col">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>配置</h3>
			</div>
		</div>
		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>
							修改实例信息
						</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<br />
						<form id="demo-form2" action="${pageContext.request.contextPath}/zk/config/update" method="post" data-parsley-validate class="form-horizontal form-label-left">
							<input type="hidden" name="id" value="${zkinstance.id }">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Name <span class="required">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="name" required="required" class="form-control col-md-7 col-xs-12" value="${zkinstance.name }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">IP <span class="required">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="ip" required="required" class="form-control col-md-7 col-xs-12" value="${zkinstance.ip }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">PORT <span class="required">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="port" required="required" class="form-control col-md-7 col-xs-12" value="${zkinstance.port }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">USE</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<div id="gender" class="btn-group" data-toggle="buttons">
											<c:if test="${zkinstance.use==1 }">
												<label class="use_switch btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
													<input type="radio" name="use" value="1" checked="checked"> &nbsp; YES &nbsp;
												</label>
												<label class="use_switch btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
													<input type="radio" name="use" value="0"> &nbsp; NO &nbsp;
												</label>
											</c:if>
											<c:if test="${zkinstance.use==0 }">
												<label class="use_switch btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
													<input type="radio" name="use" value="1"> &nbsp; YES &nbsp;
												</label>
												<label class="use_switch btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
													<input type="radio" name="use" value="0" checked="checked"> &nbsp; NO &nbsp;
												</label>
											</c:if>
										
										<script type="text/javascript">
											$(".use_switch").click(function(){
												$(".use_switch").removeClass("btn-primary").addClass("btn-default");
												$(".use_switch").find("input").removeAttr("checked");
												$(this).addClass("btn-primary");
												$(this).find("input").attr("checked","checked");
											})
										</script>
									</div>
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
