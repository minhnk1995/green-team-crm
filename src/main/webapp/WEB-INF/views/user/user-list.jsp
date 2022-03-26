<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Danh sách nhân viên</title>
<div class="mt-5 card card-form">
	<div class="row no-gutters">
		<div class="col-lg-2 card-body">
			<p>
				<strong class="headings-color">Danh sách nhân viên</strong>
			</p>
		</div>
		<div class="col-lg-10 card-form__body border-left">

			<div class="table-responsive border-bottom" data-toggle="lists"
				data-lists-values='["js-lists-values-employee-name"]'>

				<table class="table mb-0 thead-border-top-0">
					<thead>
						<tr>

							<th style="width: 18px;">
								<div class="custom-control custom-checkbox">
									<input type="checkbox"
										class="custom-control-input js-toggle-check-all"
										data-target="#staff" id="customCheckAll"> <label
										class="custom-control-label" for="customCheckAll"><span
										class="text-hide">Toggle all</span></label>
								</div>
							</th>

							<th>Nhân viên</th>


							<th style="width: 37px;">Role</th>
							<th style="width: 350px;"></th>
						</tr>
					</thead>
					<tbody class="list" id="staff">
						<c:forEach var="user" items="${users}">
							<tr class="selected">

								<td>
									<div class="custom-control custom-checkbox">
										<input type="checkbox"
											class="custom-control-input js-check-selected-row" checked=""
											id="customCheck1_1"> <label
											class="custom-control-label" for="customCheck1_1"><span
											class="text-hide">Check</span></label>
									</div>
								</td>

								<td>

									<div class="media align-items-center">
										<div class="avatar avatar-xs mr-2">
											<img src="assets/images/256_luke-porter-261779-unsplash.jpg"
												alt="Avatar" class="avatar-img rounded-circle">
										</div>
										<div class="media-body">

											<span class="js-lists-values-employee-name">${user.name}</span>

										</div>
									</div>

								</td>


								<td><c:choose>
										<c:when test="${user.role.id == 1}">
											<span class="badge badge-danger">ADMIN</span>
										</c:when>
										<c:when test="${user.role.id == 2}">
											<span class="badge badge-warning">LEADER</span>
										</c:when>
										<c:when test="${user.role.id == 3}">
											<span class="badge badge-success">USER</span>
										</c:when>

									</c:choose></td>
								<td>
									<a href="<%= request.getContextPath() + UrlConst.USER_UPDATE%>?id=${user.id}" class="btn btn-primary d-inline-flex text-white">
										<i class="material-icons">create</i>
									</a>
									
									<a href="<%= request.getContextPath() + UrlConst.USER_PROFILE %>?id=${user.id}" class="btn btn-success d-inline-flex text-white">
										<i class="material-icons">account_box</i>
									</a>
									<a href="<%= request.getContextPath() + UrlConst.USER_DELETE %>?id=${user.id}" class="btn btn-danger d-inline-flex text-white">
										<i class="material-icons">delete</i>
									</a>
									

								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
