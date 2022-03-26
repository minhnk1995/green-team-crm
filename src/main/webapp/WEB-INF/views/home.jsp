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
		<title>Homepage</title>
		<style>
			div.card:hover {
				transform: translate(-20px, -20px);
	    		transition: 0.5s;
	    		background: #effeff;
	    		
			}
		</style>	
	</head>
	<body>
		<div class="container page__heading-container">
		    <div class="page__heading">
		        <div class="d-flex align-items-center">
		            <div>
		                <nav aria-label="breadcrumb">
		                    <ol class="breadcrumb mb-0">
		                        <li class="breadcrumb-item"><a href="#">Home</a></li>
		                        <li class="breadcrumb-item active" aria-current="page">
		                            Dashboard
		                        </li>
		                    </ol>
		                </nav>
		                <h1 class="m-0">Dashboard</h1>
		            </div>
		            <div class="ml-auto" ${user.getRole().getId()==3 ? "hidden" : ""}>
		                <a href="<%=request.getContextPath() + UrlConst.JOB_CREATE%>" class="btn btn-light">
		                	<i class="material-icons icon-30pt text-muted mr-1">add</i>
		    				Create a new Project
		    			</a>
		            </div>
		        </div>
		    </div>
		</div>	
		<div class="container page__container">
		<c:choose>
			<c:when test="${lstJob!=null && lstJob.size()>0}">
				<c:forEach var="lstJob" items="${lstJob}" varStatus="i">
			   		<div class="card" style="padding:20px; border-radius: 25px">
		   				<a style="cursor: pointer; text-decoration: none;" href="<%=request.getContextPath() + UrlConst.JOB_DETAIL + "?job="%>${lstJob.getId()}">
					    	<h1 style="border-style: groove; width: fit-content; padding: 6px;">
								${lstJob.getName()}  					
							</h1>
					    	<c:choose>
					    		<c:when test="${LocalDateTime.now().isAfter(lstJob.getEnd_date())}">
				    				<h4 class="card-header__title flex mb-0" style="margin-top:10px">
				    					<span>Manager: ${lstJob.getManager().getName() }</span>
				    					<span style="margin-left: 10%">Number of Participant: ${numUser.size()>0 ? numUser[i.index] : 0}</span>
				    					<span style="float:right;">Finished!</span>
				    				</h4>     			
					    			<div class="progress" style="margin-top:20px; height:30px">
					  					<div class="progress-bar bg-success" role="progressbar" style="width: 100%;" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">100%</div>
									</div>
					    		</c:when>
					    		<c:when test="${LocalDateTime.now().isBefore(lstJob.getStart_date())}">
				    				<h4 class="card-header__title flex mb-0" style="margin-top:10px">
				    					<span>Manager: ${lstJob.getManager().getName() }</span>
				    					<span style="margin-left: 10%">Number of Participant: ${numUser.size()>0 ? numUser[i.index] : 0}</span>
				    					<span style="float:right;">Not started yet!</span>
				    				</h4>     			
					    			<div class="progress" style="margin-top:20px; height:30px">
					  					<div class="progress-bar bg-danger" role="progressbar" style="width: 0%;" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">0%</div>
									</div>
					    		</c:when>
					    		<c:otherwise>    				
				    				<h4 class="card-header__title flex mb-0" style="margin-top:10px;">
				    					<span>Manager: ${lstJob.getManager().getName() }</span>
				    					<span style="margin-left: 10%">Number of Participant: ${numUser.size()>0 ? numUser[i.index] : 0 }</span>
				    					<span style="float:right;">${Duration.between(LocalDateTime.now().minusDays(1), lstJob.getEnd_date()).toDays() } days left</span>
				    				</h4>    			
					    			<div class="progress" style="margin-top:20px; height:30px">
					  					<div class="progress-bar bg-info" role="progressbar" style="width:${Math.round((Duration.between(lstJob.getStart_date(), LocalDateTime.now().minusDays(1)).toDays()*100/Duration.between(lstJob.getStart_date(), lstJob.getEnd_date()).toDays())*100)/100 }%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">${Math.round((Duration.between(lstJob.getStart_date(), LocalDateTime.now().minusDays(1)).toDays()*100/Duration.between(lstJob.getStart_date(), lstJob.getEnd_date()).toDays())*100)/100 }%</div>
									</div>
					    		</c:otherwise>
					    	</c:choose>	 
					    </a>       			    		
			    	</div>			   				    		    
			    </c:forEach> 
			</c:when>
			<c:otherwise>
				<div>There is no project assigned to you!</div>
			</c:otherwise>
		</c:choose>
		   
		</div>			
	</body>
</html>
