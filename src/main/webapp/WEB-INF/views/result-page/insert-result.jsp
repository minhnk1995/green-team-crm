<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<title>Create user result</title>
<body>
	<div class="container page__container mt-5">
	<c:choose>
		<c:when test="${insertStatus}">
			<h4 class="status-heading text-success">Thêm nhân viên thành
				công!</h4>
		</c:when>
		<c:when test="${!insertStatus}">
			<h4 class="status-heading text-danger">Thêm nhân viên thất bại!Xin vui lòng thử lại!</h4>
		</c:when>
	</c:choose>
		<a class="btn btn-success d-inline-flex" style="width:200px;" href="<%= request.getContextPath() + UrlConst.HOME%>">
			<i class="material-icons mr-1">launch</i> 
			Go To Home
		</a>
		<a class="btn btn-warning mt-5 d-inline-flex" style="width:200px;" href="<%= request.getContextPath() + UrlConst.USER_DASHBOARD%>">
			<i class="material-icons mr-1">launch</i> 
			Danh Sách nhân viên
		</a>
		</div>
</body>