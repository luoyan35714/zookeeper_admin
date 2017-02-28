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
                        	<c:if test="${zkinstance!=null }">
                            	<li><a id="zk_instance_detail" href="${pageContext.request.contextPath}/zk/detail?id=${zkinstance.id }">${zkinstance.name }</a></li>
                            </c:if>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-edit"></i> 配置 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a id="zk_config_common" href="form.html"> 配置端口 </a></li>
                            <li><a id="zk_config_auth" href="form_advanced.html">Advanced Components</a></li>                            
                        </ul>
                    </li>
                    <li><a><i class="fa fa-bar-chart-o"></i> 统计 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a id="zk_statistics_node" href="chartjs.html">Chart JS</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	var current = $(".left_col").find('a').filter(function () {
	    return this.id == "<%= session.getAttribute("LEFT_TREE")%>";
	}).parent('li').addClass('current-page').parents('ul').slideDown(function() {e();}).parent().addClass('active');
</script>