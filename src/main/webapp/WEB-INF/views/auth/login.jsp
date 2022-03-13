<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html dir="ltr">

<head>
    <meta charset="utf-8" />
    <title>Đăng nhập</title>

    <link rel="shortcut icon" href="assets/images/favicon.ico" />

    <!-- Perfect Scrollbar -->
    <link type="text/css" href="assets/vendor/perfect-scrollbar.css" rel="stylesheet">

    <!-- App CSS -->
    <link type="text/css" href="assets/css/app.css" rel="stylesheet">
    <link type="text/css" href="assets/css/app.rtl.css" rel="stylesheet">

    <!-- Material Design Icons -->
    <link type="text/css" href="assets/css/vendor-material-icons.css" rel="stylesheet">
    <link type="text/css" href="assets/css/vendor-material-icons.rtl.css" rel="stylesheet">

    <!-- Font Awesome FREE Icons -->
    <link type="text/css" href="assets/css/vendor-fontawesome-free.css" rel="stylesheet">
    <link type="text/css" href="assets/css/vendor-fontawesome-free.rtl.css" rel="stylesheet">

</head>

<body  class="layout-login">
    <div class="layout-login__overlay"></div>
    <div class="layout-login__form bg-white" data-perfect-scrollbar>
        <div class="d-flex justify-content-center mt-2 mb-5 navbar-light">
            <a href="index.html" class="navbar-brand" style="min-width: 0">
                <img class="navbar-brand-icon" src="assets/images/logo.png" width="250" alt="Stack">
            </a>
        </div>

        <h4 class="m-0">Welcome to Green Team CRM!</h4>
        <p class="mb-5">Login to access your account </p>

        <form action="<%=request.getContextPath() + UrlConst.AUTH_LOGIN%>" method="post" >
            <div class="form-group">
                <label class="text-label" for="email_2" >Email Address:</label>
                <div class="input-group input-group-merge">
                    <input name="email" id="email_2" type="email" required="" value="${user}" class="<c:if test="${wronglogin}">is-invalid</c:if> form-control form-control-prepended" placeholder="john@doe.com">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="far fa-envelope"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="text-label" for="password_2">Password:</label>
                <div class="input-group input-group-merge">
                    <input id="password_2" type="password" value="${pass}"   required="" name="password" class="<c:if test="${wronglogin}">is-invalid</c:if> form-control form-control-prepended" placeholder="Enter your password">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="fa fa-key"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group mb-5">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input"  name="remember" checked="" id="remember">
                    <label class="custom-control-label" for="remember" >Remember me</label>
                </div>
            </div>
             <div class="form-group text-center">
                <button class="btn btn-primary mb-5" type="submit">Login</button><br>
            </div>
        </form>
    </div>
    

</body>

</html>
