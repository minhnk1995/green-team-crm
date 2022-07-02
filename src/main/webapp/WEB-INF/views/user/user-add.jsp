<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<!DOCTYPE html>
<meta charset="UTF-8">
<title>Add user</title>

<form action="<%=request.getContextPath() + UrlConst.USER_ADD%>"
	class="d-flex flex-column align-items-center" method="post">
	<div style="width: 300px">
		<div class="d-flex justify-content-center mt-2 mb-4 navbar-light">
			<a href="dashboard.html" class="navbar-brand" style="min-width: 0">
				<img class="navbar-brand-icon"
				src="<c:url value="/assets/images/logo.png"/>" width="250"
				alt="Cybersoft">
			</a>
		</div>
		<h3 class="text-center">Create New Account</h3>
		<div class="form-group">
			<label for="username">Name:</label> <input
				class="<c:if test="${userExists}">is-invalid</c:if> form-control"
				type="text" id="username" required="" name="name"
				placeholder="John Doe" value="${lastUserDto.name }"/>
			<c:if test="${userExists}">
				<div class="invalid-feedback">User already exists!</div>
			</c:if>
		</div>
		<div class="form-group">
			<label for="email">Email Address:</label> <input 
				class="<c:if test="${emailExists}">is-invalid</c:if> form-control"
				type="email" id="email" required="" name="email"
				placeholder="john@doe.com" value="${lastUserDto.email }" />
			<c:if test="${emailExists}">
				<div class="invalid-feedback">Email already exists!</div>
			</c:if>
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input
				class="<c:if test="${validPassword}">is-invalid</c:if> form-control"
				name="password" type="password" required="" id="password"
				placeholder="Enter your password" value="${lastUserDto.password }" />
			<c:if test="${validPassword}">
				<div class="invalid-feedback">Password does not match.</div>
			</c:if>
		</div>
		<div class="form-group">
			<label for="password">Repeat password:</label> <input
				class="<c:if test="${validPassword}">is-invalid</c:if> form-control"
				name="rPassword" type="password" required="" id="rPassword"
				placeholder="Enter your password" value="${lastUserDto.rPassword }" />
			<c:if test="${validPassword}">
				<div class="invalid-feedback">Password does not match.</div>
			</c:if>
		</div>
		<div class="form-group">
			<label for="address">Address:</label> <input class="form-control"
				type="text" name="address" required="" id="address"
				placeholder="Enter your address" value="${lastUserDto.address }" />
		</div>
		<div class="form-group">
			<label for="phone">Phone:</label> <input class="form-control"
				type="text" required="" id="phone" name="phone"
				placeholder="Enter your phone" value="${lastUserDto.phone }"/>
		</div>
		<div class="form-group">
			<div class="row no-gutters">
				<div class="col-lg-8 card-form__body card-body">
					<div class="form-group">
						<label for="select01">Role</label> <select id="select01"
							name="role" data-toggle="select" class="form-control">
							<option selected="" value="3">User</option>
							<option value="2">Leader</option>
							<option value="1">Admin</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="custom-control custom-checkbox">
				<input type="checkbox" class="custom-control-input" id="terms" /> <label
					class="custom-control-label" for="terms">I accept <a
					href="#">Terms and Conditions</a>
				</label>
			</div>
		</div>
		<div class="form-group text-center">
			<button class="btn btn-primary" type="submit">Create New
				Account</button>
		</div>
	</div>

</form>