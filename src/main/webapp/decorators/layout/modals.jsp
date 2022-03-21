<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<!-- modals -->
<!-- create New user modal -->
<div id="modal-create-user" class="modal fade" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-body">
				<div class="px-3">
					<div class="d-flex justify-content-center mt-2 mb-4 navbar-light">
						<a href="dashboard.html" class="navbar-brand" style="min-width: 0">
							<img class="navbar-brand-icon" src="<c:url value="/assets/images/logo.png"/>"
							width="250" alt="Cybersoft">
						</a>
					</div>

					<form action="<%=request.getContextPath() + UrlConst.USER_ADD%>"
						method="post">
						<h3 class="text-center">Create New Account</h3>
						<div class="form-group">
							<label for="username">Name:</label> <input
								class="form-control is-valid" type="text" id="username"
								required="" name ="name" placeholder="John Doe" />
						</div>
						<div class="form-group">
							<label for="email">Email Address:</label>
							<input
								class="form-control is-invalid" type="email" id="email"
								required="" name="email" placeholder="john@doe.com" />
						</div>
						<div class="form-group">
							<label for="password">Password:</label> <input
								class="form-control" name="password" type="password" required="" id="password"
								placeholder="Enter your password" />
						</div>
						<div class="form-group">
							<label for="address">Address:</label> <input class="form-control"
								type="text" name = "address" required="" id="address"
								placeholder="Enter your address" />
						</div>
						<div class="form-group">
							<label for="phone">Phone:</label> <input class="form-control"
								type="text" required="" id="phone" name="phone"
								placeholder="Enter your phone" />
						</div>
						<div class="form-group">
							<div class="row no-gutters">
								<div class="col-lg-8 card-form__body card-body">
									<div class="form-group">
										<label for="select01">Role</label> <select id="select01" name="role"
											data-toggle="select" class="form-control">
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
								<input type="checkbox" class="custom-control-input" id="terms" />
								<label class="custom-control-label" for="terms">I accept
									<a href="#">Terms and Conditions</a>
								</label>
							</div>
						</div>
						<div class="form-group text-center">
							<button class="btn btn-primary" type="submit">Create New
								Account</button>
						</div>
					</form>
				</div>
			</div>
			<!-- // END .modal-body -->
		</div>
		<!-- // END .modal-content -->
	</div>
	<!-- // END .modal-dialog -->
</div>
