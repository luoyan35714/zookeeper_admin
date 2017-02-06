<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
 
        <div class="navbar nav_title" style="border: 0;">
            <a href="${pageContext.request.contextPath}" class="site_title">Zookeeper Admin!</a>
        </div>
 			
        <div class="profile">
            <hr style="height: 1px;width:100%; background-color: white;"/>
        </div>
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <ul class="nav side-menu">
                    <li><a><i class="fa fa-desktop"></i> Zookeeper 实例 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                        	<c:forEach items="${zkInstances }" var="zkInstance">
                            	<li><a href="javascript:void(0);" link="${zkInstance.id }">${zkInstance.name }</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-edit"></i> 配置 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a href="form.html"> 配置端口 </a></li>
                            <li><a href="form_advanced.html">Advanced Components</a></li>
                            <li><a href="form_validation.html">Form Validation</a></li>
                            <li><a href="form_wizards.html">Form Wizard</a></li>
                            <li><a href="form_upload.html">Form Upload</a></li>
                            <li><a href="form_buttons.html">Form Buttons</a></li>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-bar-chart-o"></i> 统计 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a href="chartjs.html">Chart JS</a></li>
                            <li><a href="chartjs2.html">Chart JS2</a></li>
                            <li><a href="morisjs.html">Moris JS</a></li>
                            <li><a href="echarts.html">ECharts </a></li>
                            <li><a href="other_charts.html">Other Charts </a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
 
        <div class="sidebar-footer hidden-small">
<!--             <a data-toggle="tooltip" data-placement="top" title="Settings"> -->
<!--                 <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> -->
<!--             </a> -->
<!--             <a data-toggle="tooltip" data-placement="top" title="FullScreen"> -->
<!--                 <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span> -->
<!--             </a> -->
<!--             <a data-toggle="tooltip" data-placement="top" title="Lock"> -->
<!--                 <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span> -->
<!--             </a> -->
<!--             <a data-toggle="tooltip" data-placement="top" title="Logout"> -->
<!--                 <span class="glyphicon glyphicon-off" aria-hidden="true"></span> -->
<!--             </a> -->
        </div>
    </div>
</div>