<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Xóa Nhân viên</title>
<jsp:include page="/layout/header.jsp" />
</head>
<body class="layout-fixed">
	<div class="preloader"></div>
	<!-- Header Layout -->
	<div class="mdk-header-layout js-mdk-header-layout">
		<jsp:include page="/layout/topbar.jsp" />
		<div class="mdk-header-layout__content page">
			<jsp:include page="/layout/navbar.jsp" />
			<div
				style="padding-bottom: calc(5.125rem/ 2); position: relative; margin-bottom: 1.5rem;">
				<div class="bg-primary" style="min-height: 150px;">
					<div class="d-flex align-items-end container page__container"
						style="position: absolute; left: 0; right: 0; bottom: 0;">
						<div class="avatar avatar-xl">
							<img src='<c:url value="/assets/images/avatars/tuanphan.jpg"/>'
								alt="avatar" class="avatar-img rounded"
								style="border: 2px solid white;">
						</div>
						<div class="card-header card-header-tabs-basic nav flex"
							role="tablist">
							<a href="#activity" class="active show" data-toggle="tab"
								role="tab" aria-selected="true">Activities</a> <a
								href="#purchases" data-toggle="tab" role="tab"
								aria-selected="false">Task</a> <a href="#emails"
								data-toggle="tab" role="tab" aria-selected="false">Emails</a> <a
								href="#quotes" data-toggle="tab" role="tab"
								aria-selected="false">Quotes</a>
						</div>
					</div>
				</div>
			</div>
			<div class="container page__container mt-5">
				<div class="row">
					<div class="col-lg-3">
						<h1 class="h4 mb-1">${curentUser.name}</h1>
						<p class="text-muted">@${curentUser.name}n</p>
						<p>CRM Admin For Green Team</p>
						<div class="text-muted d-flex align-items-center">
							<i class="material-icons mr-1">location_on</i>
							<div class="flex">${curentUser.address }</div>
						</div>
						<div class="text-muted d-flex align-items-center">
							<i class="material-icons mr-1">link</i>
							<div class="flex">
								<p>Email: ${curentUser.email }</p>
							</div>
						</div>
					</div>

				</div>
				<button class="btn btn-danger mt-3 d-inline-flex text-white"
					data-toggle="modal" data-target="#modal-delete-user">
					<i class="material-icons">delete</i> Xóa thành viên này!!
				</button>

				<a class="btn btn-success mt-3 d-inline-flex" style="width: 200px;"
					href="<%=request.getContextPath() + UrlConst.HOME%>"> <i
					class="material-icons mr-1">launch</i> Go To Home
				</a>
			</div>
		</div>
	</div>

	<jsp:include page="/layout/footer.jsp" />
	
	
	<div id="modal-delete-user" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="modal-standard-title"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<form
				action="<%= request.getContextPath() + UrlConst.USER_DELETE %>?id=<%=request.getParameter("id") %>"
				method="post" class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal-standard-title">Xóa Nhân
						Viên</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<!-- // END .modal-header -->
				<div class="modal-body">
					<p>Nhân viên này sẽ bị xóa vĩnh viễn không thể khôi phục! Bạn
						có chắc chắc muốn thực hiện hành động này!!</p>
				</div>
				<!-- // END .modal-body -->
				<div class="modal-footer">
					<button type="button" class="btn btn-light" data-dismiss="modal">Hủy</button>
					<button type="submit" class="btn btn-danger">Xóa</button>
				</div>
				<!-- // END .modal-footer -->
			</form>
			<!-- // END .modal-content -->
		</div>
		<!-- // END .modal-dialog -->
	</div>
</body>
</html>