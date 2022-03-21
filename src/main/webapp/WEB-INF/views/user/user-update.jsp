<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<meta charset="UTF-8">
<title>Chỉnh sửa thông tin</title>
<div class="container page__container">
	<!-- Page Content -->
	<div class="card card-form">
		<div class="row no-gutters">
			<div class="col-lg-4 card-body">
				<p>
					<strong class="headings-color">Basic Information</strong>
				</p>
				<p class="text-muted">Edit your account details and settings.</p>
			</div>
			<div class="col-lg-8 card-form__body card-body">
				<div class="row">
					<div class="col-md-6">
						<form
							action="<%=request.getContextPath() + UrlConst.USER_UPDATE%>"
							method="post">
							<input hidden value="<%= request.getParameter("id")%>" name="id"/>
							<div class="form-group">
								<label for="fname">Full name</label> <input id="fname"
									type="text"
									class="form-control"
									placeholder="Full name" value="${userUpdate.name}" name="name"
									required>
							</div>
							<div class="form-group">
								<label for="email">Email</label> <input id="email"
									type="text"
									class="form-control"
									placeholder="Full name" value="${userUpdate.email}" name="email"
									required>
							</div>
							<div class="form-group">
								<label for="address">Address:</label> <input
									class="form-control" name="address" type="text" required
									id="address" value="${userUpdate.address}" />
							</div>
							<div class="form-group">
								<label for="phone">Phone:</label> <input class="form-control"
									type="text" required="" id="phone" name="phone"
									value="${userUpdate.phone }" />
							</div>
							<div class="form-group">
								<div class="form-group">
									<label for="select01">Role</label> <select id="select01"
										data-toggle="select" class="form-control" name="roleId">
										<option selected="${userUpdate.role.id}" value="3">User</option>
										<option value="2">Leader</option>
										<option value="1">Admin</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="npass">New Password</label> <input
									style="width: 270px;" id="npass" type="password"
									class="form-control <c:if test="${validPassword}">is-invalid</c:if>"
									name="password" value="${lastPassword}">
							</div>
							 	
							<div class="form-group">
								<label for="cpass">Confirm Password</label> 
								<input
									style="width: 270px;" id="cpass" type="password"
									class="<c:if test="${validPassword}">is-invalid</c:if> form-control"
									name="repeatPassword" placeholder="Confirm password" value="${lastRPassword}">
								<c:if test="${validPassword}">
									<div class="invalid-feedback">Password does not match.</div>
								</c:if>
							</div>
							<div class="text-right mb-5">
								<button type="submit" class="btn btn-success">Save</button>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>

	</div>


	<!-- END Page Content -->
</div>
