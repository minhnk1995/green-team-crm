<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.time.Duration" %>
<%@ page import = "java.time.LocalDateTime" %>
<%@ page import = "java.time.LocalDate" %>
<%@ page import = "java.lang.Math" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container page__heading-container">
	    <div class="page__heading">
	        <div class="d-flex align-items-center">
	            <div>
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb mb-0">
	                        <li class="breadcrumb-item"><a href="<%=request.getContextPath() + UrlConst.HOME%>">Home</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Create Task
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Create a new Task</h1>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="container page__container mt-5">
	   <form action="<%=request.getContextPath() + UrlConst.TASK_CREATE%>" method="post">
	   		<input type="hidden" value="${jobID }" name="jobID">
            <div class="form-group">
                <label for="name">Task Name:</label>
                   <c:if test="${invalidName!=null}">
		        	<h6 style="color:red; margin:5px 0">${invalidName}</h6>
		        </c:if>
		        <c:if test="${blankName!=null}">
		        	<h6 style="color:red; margin:5px 0">${blankName}</h6>
		        </c:if>
                <input type="text" class="form-control" id="name" name="name" placeholder="Project name">
            </div>
            <div class="form-group">
                <label for="select01">Staff:</label>
                <c:if test="${invalidManager!=null}">
		        	<h6 style="color:red; margin:5px 0">${invalidManager}</h6>
		        </c:if>
                <select id="select01" name="user" data-toggle="select" class="form-control">
                	<option value="0" selected="">My first option</option>
	                 <c:forEach var="lstUser" items="${lstUser}">
	                    <option value="${lstUser.getId() }">${lstUser.getName() }</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
            	<span>Please choose the period from ${job.getStart_date().toLocalDate() } to ${job.getEnd_date().toLocalDate()}</span>
            </div>
               <div class="col-lg-4" style="padding:0">
                   <div class="card">
                       <div class="card-body">
                           <label class="text-label" for="dateRangePickerSample02">Project Period:</label>
                           <c:if test="${invalidPeriod!=null}">
				        	<h6 style="color:red; margin:5px 0">${invalidPeriod}</h6>
				        </c:if>
                           <input id="dateRangePickerSample02" name="period" type="text" class="form-control" placeholder="Range example" data-toggle="daterangepicker" data-daterangepicker-drops="up" data-daterangepicker-start-date="${LocalDateTime.now().toString() }" data-daterangepicker-end-date="${LocalDateTime.now().plusDays(1).toString() }">
                       </div>
                   </div>
               </div>
               <c:if test="${unable!=null}">
	        	<h6 style="color:red; margin:5px 0">${unable}</h6>
	        </c:if>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
	</div>
</body>
</html>