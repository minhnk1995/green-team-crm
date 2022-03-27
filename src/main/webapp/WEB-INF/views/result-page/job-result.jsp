<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Result Page</title>
</head>
<body>
	<div class="container page__container" style="padding-top:2rem">		
        <div class="d-flex align-items-center">
        	<div class="mr-auto">
        	<c:choose>
        		<c:when test="${projectRemoveSuccess == true }">
        			<a href="<%=request.getContextPath() + UrlConst.HOME %>" class="btn btn-light">
	                	<i class="material-icons icon-30pt text-muted mr-1">arrow_back</i>
	    				BACK
	    			</a>
        		</c:when>
        		<c:when test="${taskRemoveSuccess == true }">
        			<a href="<%=request.getContextPath() + UrlConst.JOB_DETAIL + "?job="%>${jobID}" class="btn btn-light">
	                	<i class="material-icons icon-30pt text-muted mr-1">arrow_back</i>
	    				BACK
	    			</a>
        		</c:when>
        		<c:otherwise>
	                <a href="<%=request.getContextPath() + UrlConst.JOB_DETAIL + "?job="%>${jobID}" class="btn btn-light">
	                	<i class="material-icons icon-30pt text-muted mr-1">arrow_back</i>
	    				BACK
	    			</a>        	
	        	</c:otherwise>
        	</c:choose>
            </div>
        </div>
		<h4 style="margin-top:1rem">${msg }</h4>
	</div>
</body>
</html>