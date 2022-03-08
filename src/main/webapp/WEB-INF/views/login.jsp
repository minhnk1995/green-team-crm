<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html dir="ltr">

<head>
    <meta charset="utf-8" />
    <title>Đăng nhập</title>

    <link rel="shortcut icon" href="/assets/images/favicon.ico" />

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

<body style="background-image: linear-gradient(to bottom right, #b45bc3, #0a8b14); padding: 0; margin: 0; width: 100%;">
    <div style="width:30%; margin:10% 35%;">
        <form action="<%=request.getContextPath() + UrlConst.LOGIN%>" method="post" novalidate style="background-color: aliceblue;padding:5%">
            <div><h3>Login</h3></div>
            <div class="form-group">
                <label class="text-label" for="email_2">Email:</label>
                <div class="input-group input-group-merge">
                    <input id="email" name="email" type="email" required="" class="form-control form-control-prepended"
                        placeholder="john@doe.com">
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
                    <input id="password" name="password" type="password" required="" class="form-control form-control-prepended"
                        placeholder="Enter your password">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="fa fa-key"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group text-center">
                <button class="btn btn-primary" type="submit">Login</button><br>
            </div>
            <c:if test="${wronglogin}">
            	<div>
	        		<h6 style="color:red; margin:5px 0">Sai Email hoặc mật khẩu</h6>
	        	</div>
	        </c:if>
        </form>
    </div>
    
</body>

</html>