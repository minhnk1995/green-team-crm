<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>
<!-- Breadcrumb -->
<div
	style="padding-bottom: calc(5.125rem/ 2); position: relative; margin-bottom: 1.5rem;">
	<div class="bg-primary" style="min-height: 150px;">
		<div class="d-flex align-items-end container page__container"
			style="position: absolute; left: 0; right: 0; bottom: 0;">
			<div class="avatar avatar-xl">
				<img src='<c:url value="/assets/images/avatars/tuanphan.jpg"/>' alt="avatar"
					class="avatar-img rounded" style="border: 2px solid white;">
			</div>
			<div class="card-header card-header-tabs-basic nav flex"
				role="tablist">
				<a href="#activity" class="active show" data-toggle="tab" role="tab"
					aria-selected="true">Activities</a> <a href="#purchases"
					data-toggle="tab" role="tab" aria-selected="false">Task</a> <a
					href="#emails" data-toggle="tab" role="tab" aria-selected="false">Emails</a>
				<a href="#quotes" data-toggle="tab" role="tab" aria-selected="false">Quotes</a>
			</div>
		</div>
	</div>
</div>
<div class="container page__container mt-5">

	<div class="row">
		<div class="col-lg-3">
			<h1 class="h4 mb-1">${userProfile.name}</h1>
			<p class="text-muted">@${userProfile.name}n</p>
			<p>CRM Admin For Green Team</p>
			<div class="text-muted d-flex align-items-center">
				<i class="material-icons mr-1">location_on</i>
				<div class="flex">${userProfile.address }</div>
			</div>
			<div class="text-muted d-flex align-items-center">
				<i class="material-icons mr-1">link</i>
				<div class="flex">
					<p>Email: ${userProfile.email }</p>
				</div>
			</div>
		</div>

	</div>
</div>

