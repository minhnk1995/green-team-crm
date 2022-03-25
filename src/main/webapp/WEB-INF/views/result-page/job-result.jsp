<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
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
                <a href="<%=request.getContextPath() + UrlConst.JOB_DETAIL + "?jobid="%>${jobID}" class="btn btn-light">
                	<i class="material-icons icon-30pt text-muted mr-1">arrow_back</i>
    				BACK
    			</a>
            </div>
        </div>
		<h4 style="margin-top:1rem">${msg }</h4>
	</div>
</body>
</html>