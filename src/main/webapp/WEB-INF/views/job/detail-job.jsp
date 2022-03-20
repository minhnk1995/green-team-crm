<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.time.Duration" %>
<%@ page import = "java.time.LocalDateTime" %>
<%@ page import = "java.lang.Math" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Project detail</title>	  
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
		                            Create Job
		                        </li>
		                    </ol>
		                </nav>
		                <h1 class="m-0">Project Detail</h1>
		            </div>
		            <div class="ml-auto" ${user.getRole().getId()==3 ? "hidden" : ""}>
		                <a href="<%=request.getContextPath() + UrlConst.TASK_CREATE%>" class="btn btn-light">
		                	<i class="material-icons icon-30pt text-muted mr-1">add</i>
		    				Add task
		    			</a>
		            </div>
		        </div>
		    </div>
		</div>
		<div class="container page__container">
	        <!-- Page Content -->
	        <div class="row" style="padding: 10px; border-radius: 20px; border-style: solid; background: white; padding-bottom: 15px">
	            <div class="col-lg">
                	<div class="form-group">
		                <span style="font-weight: bolder;">Project Name:</span>		                
	              		<span>${job.getName() }</span>
		            </div>
		            <div class="form-group">
			              <span style="font-weight: bolder;">Manager:</span>
			              <a>${job.getManager().getName() }</a>
	          		</div>
	          		<div style="display:inline-block;">
	          			<span style="float:right;">${Duration.between(LocalDateTime.now(), job.getEnd_date()).toDays() } days left</span>
	          		</div>
	          		<div class="progress" style="height:20px">
	  					<div class="progress-bar bg-info" role="progressbar" style="width: ${Math.round((Duration.between(job.getStart_date(), LocalDateTime.now()).toDays()*100/Duration.between(job.getStart_date(), job.getEnd_date()).toDays())*100)/100 }%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">${Math.round((Duration.between(job.getStart_date(), LocalDateTime.now()).toDays()*100/Duration.between(job.getStart_date(), job.getEnd_date()).toDays())*100)/100 }%</div>
					</div>
                </div>                
                <div class="col-lg">
                	<div class="form-group">
		                <span style="font-weight: bolder;">Description:</span>
		                <p>${job.getDescription() }</p>
		            </div>
                </div>
            </div>
            <div class="row" style="padding: 10px; border-radius: 20px; border-style: solid; background: white; padding-bottom: 15px; margin-top: 2rem">
            	<div class="table-responsive border-bottom" data-toggle="lists" data-lists-values='["js-lists-values-employee-name"]'>
	                <div class="search-form search-form--light m-3">
	                    <input type="text" class="form-control search" placeholder="Search">
	                    <button class="btn" type="button"><i class="material-icons">search</i></button>
	                </div>

                    <table class="table mb-0 thead-border-top-0">
                        <thead>
                            <tr>
                                <th style="width: 30%;">Task's name</th>
                                <th style="width: 25%;">Staff</th>
                                <th style="width: 15%;">Deadline</th>
                                <th style="width: 15%;">Time remain</th>
                                <th style="width: 10%;">Status</th>
                                <th style="width: 5%"></th>
                            </tr>
                        </thead>
                        <tbody class="list" id="staff02">
	                        <c:choose>
								<c:when test="${lstTask!=null && lstTask.size()>0}">
									<c:forEach var="lstTask" items="${lstTask}">
										<tr>
			                                <td>
			                                    <span class="js-lists-values-employee-name">${lstTask.getName()}</span>
			                                </td>
			                                <td>${lstTask.getUser().getName()}</td>
			                                <td><small>${lstTask.getEnd_date().toLocalDate()}</small></td>
			                                <td><small class="text-muted">${Duration.between(LocalDateTime.now(), lstTask.getEnd_date()).toDays() } days left</small></td>
			                                
			                                <c:choose>
			                                	<c:when test="${lstTask.getStatus().getId()==1}">
			                                		<td><span class="badge badge-warning">${lstTask.getStatus().getName()}</span></td>
			                                	</c:when>
			                                	<c:when test="${lstTask.getStatus().getId()==2}">
			                                		<td><span class="badge badge-primary">${lstTask.getStatus().getName()}</span></td>
			                                	</c:when>
			                                	<c:when test="${lstTask.getStatus().getId()==3}">
			                                		<td><span class="badge badge-success">${lstTask.getStatus().getName()}</span></td>
			                                	</c:when>
			                                </c:choose>
			                                <td><a href="" class="text-muted"><i class="material-icons">more_vert</i></a></td>
			                            </tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div>There is no available task!</div>
								</c:otherwise>
							</c:choose>                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>		
	</body>
</html>
