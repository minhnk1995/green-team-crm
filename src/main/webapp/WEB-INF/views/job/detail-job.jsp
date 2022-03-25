<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.time.Duration" %>
<%@ page import = "java.time.LocalDateTime" %>
<%@ page import = "java.time.LocalDate" %>
<%@ page import = "java.lang.Math" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Project detail</title>
		<style>
			.btn-custom-style {
				float:left;
				margin-left:1rem;
			}
		</style>
		<!-- Toastr -->
    	<link type="text/css" href="<c:url value="/assets/vendor/toastr.min.css"/>" rel="stylesheet">	  
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
		                <div class="btn-custom-style" ${job.getEnd_date().toLocalDate().isBefore(LocalDate.now()) ? "hidden" : ""}>
		                	<a href="" data-toggle="modal" data-target="#modal-large" class="btn btn-light">
			                	<i class="material-icons icon-30pt text-muted mr-1">build</i>
			    				Configure
			    			</a>
		                </div>
		                <div class="btn-custom-style">
		                	<a href="" data-toggle="modal" data-target="#modal-warning" class="btn btn-light">
			                	<i class="material-icons icon-30pt text-muted mr-1">delete</i>
			    				Remove
			    			</a>
		                </div>
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
	          		<div class="form-group">
		                <span style="font-weight: bolder;">From </span>		                
	              		<span>${job.getStart_date().toLocalDate() }</span>
	              		<span style="font-weight: bolder; margin:0 10px">to</span>		                
	              		<span>${job.getEnd_date().toLocalDate() }</span>
		            </div>
	          		<c:choose>
	    				<c:when test="${LocalDateTime.now().isAfter(job.getEnd_date())}">
	    					<div style="display:inline-block;">
			          			<span style="float:right;">Finished!</span>
			          		</div>
			          		<div class="progress" style="height:20px">
			  					<div class="progress-bar bg-success" role="progressbar" style="width:100%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">100%</div>
							</div>
	    				</c:when>
	    				<c:when test="${LocalDateTime.now().isBefore(job.getStart_date())}">
	    					<div style="display:inline-block;">
			          			<span style="float:right;">Not started yet!</span>
			          		</div>
			          		<div class="progress" style="height:20px">
			  					<div class="progress-bar bg-info" role="progressbar" style="width:0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">0%</div>
							</div>
	    				</c:when>
	    				<c:otherwise>
	    					<div style="display:inline-block;">
			          			<span style="float:right;">${Duration.between(LocalDateTime.now(), job.getEnd_date()).toDays() } days left</span>
			          		</div>
			          		<div class="progress" style="height:20px">
			  					<div class="progress-bar bg-info" role="progressbar" style="width: ${Math.round((Duration.between(job.getStart_date(), LocalDateTime.now()).toDays()*100/Duration.between(job.getStart_date(), job.getEnd_date()).toDays())*100)/100 }%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
			  					${Math.round((Duration.between(job.getStart_date(), LocalDateTime.now()).toDays()*100/Duration.between(job.getStart_date(), job.getEnd_date()).toDays())*100)/100 }%</div>
							</div>
	    				</c:otherwise>
    				</c:choose>	          		
                </div>                
                <div class="col-lg">
                	<div class="form-group">
		                <span style="font-weight: bolder;">Description:</span>
		                <p>${job.getDescription() }</p>
		            </div>
                </div>
            </div>
            <div class="container page__heading-container" style="margin-top:1rem">
		        <div class="d-flex align-items-center">
		        	<div class="ml-auto" ${user.getRole().getId()==3 ? "hidden" : ""}>
		                <a href="<%=request.getContextPath() + UrlConst.TASK_CREATE%>" class="btn btn-light">
		                	<i class="material-icons icon-30pt text-muted mr-1">add</i>
		    				Add task
		    			</a>
		            </div>
		        </div>
	        </div>
            <div class="row" style="padding: 10px; border-radius: 20px; border-style: solid; background: white; padding-bottom: 15px; margin-top: 1rem">
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
			                                <c:choose>
			                                	<c:when test="${lstTask.getStatus().getId()==1}">
			                                		<td><small class="text-muted">${Duration.between(LocalDateTime.now(), lstTask.getEnd_date()).toDays() } days left</small></td>
			                                		<td><span class="badge badge-warning">${lstTask.getStatus().getName()}</span></td>
			                                	</c:when>
			                                	<c:when test="${lstTask.getStatus().getId()==2}">
			                                		<td><small class="text-muted">${Duration.between(LocalDateTime.now(), lstTask.getEnd_date()).toDays() } days left</small></td>
			                                		<td><span class="badge badge-primary">${lstTask.getStatus().getName()}</span></td>
			                                	</c:when>
			                                	<c:when test="${lstTask.getStatus().getId()==3}">
			                                		<td><small class="text-muted">-</small></td>
			                                		<td><span class="badge badge-success">${lstTask.getStatus().getName()}</span></td>
			                                	</c:when>
			                                </c:choose>
			                                <td class="nav-item dropdown">
			                                	<input type="hidden" value="${lstTask.getId() }" name="jobID">	
			                                	<a href="alo" class="text-muted nav-link dropdown-toggle"  data-toggle="dropdown" data-caret="false"><i class="material-icons">more_vert</i></a>
			                                	<div class="dropdown-menu dropdown-menu-right">
												    <a class="dropdown-item" data-toggle="modal" data-target="#modal-edit-task" href="">Edit</a>
												    <div class="dropdown-divider"></div>
												    <a class="dropdown-item" data-toggle="modal" data-target="#modal-remove-task" href="">Remove</a>
										  		</div>
			                                </td>
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
        
        <!-- START MODALS -->
        
        <!-- EDIT PROJECT -->
        <div id="modal-large" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modal-large-title" aria-hidden="true">
	        <div class="modal-dialog modal-lg" role="document">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="modal-large-title">Edit Project</h5>
	                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                        <span aria-hidden="true">&times;</span>
	                    </button>
	                </div>
	                
	                <form action="<%=request.getContextPath() + UrlConst.JOB_EDIT%>" method="post"> 
		                <!-- // END .modal-header -->
		                <div class="modal-body">
	                    	<input type="hidden" value="${job.getId()}" name="jobID">	
	                    	<input type="hidden" value="${job.getName()}" name="jobName">	
	                    	<input type="hidden" value="${job.getDescription()}" name="jobDescrpt">
	                    	<input type="hidden" value="${job.getStart_date()}" name="jobStart">	
	                    	<input type="hidden" value="${job.getEnd_date()}" name="jobEnd">	
	                    	<input type="hidden" value="${job.getManager().getId()}" name="jobManager">	
				            <div class="form-group">
				                <label for="updated-name">Project Name:</label>
			                    <c:if test="${invalidName!=null}">
						        	<h6 style="color:red; margin:5px 0">${invalidName}</h6>
						        </c:if>
						        <c:if test="${blankName!=null}">
						        	<h6 style="color:red; margin:5px 0">${blankName}</h6>
						        </c:if>
				                <input type="text" class="form-control" id="updated-name" name="updated-name" placeholder="Project name" value="${job.getName() }">
				            </div>
				            <div class="form-group">
				                <label for="updated-descrpt">Description:</label>
				                <textarea class="form-control" id="updated-descrpt" name="updated-descrpt" rows="4">${job.getName() }</textarea>
				            </div>
				            <div class="form-group">
				                <label for="select01">Manager:</label>
				                <c:if test="${invalidManager!=null}">
						        	<h6 style="color:red; margin:5px 0">${invalidManager}</h6>
						        </c:if>
				                <select id="select01" name="updated-manager" data-toggle="select" class="form-control">
				                	<option value="0">My first option</option>
					                 <c:forEach var="lstManager" items="${lstManager}">
					                    <option value="${lstManager.getId() }" ${lstManager.getId() == job.getManager().getId() ? "selected" : "" }> ${lstManager.getName() }</option>
				                    </c:forEach>
				                </select>
				            </div>
				            <c:choose>
				            	<c:when test="${LocalDateTime.now().isAfter(job.getStart_date())}">
				            		<div class="col-lg-4" style="padding:0">
				                        <div class="card">
				                            <div class="card-body">
				                                <label class="text-label" for="dateRangePickerSample01">Deadline</label>
				                                <input id="dateRangePickerSample01" name="updated-deadline" type="text" class="form-control" placeholder="Date example" data-toggle="daterangepicker" data-daterangepicker-drops="up" data-daterangepicker-start-date="${job.getEnd_date().toLocalDate().toString() }" data-daterangepicker-single-date-picker="true">
				                            	<input type="hidden" value="1" name="dateStatus">	  
				                            </div>
				                        </div>
				                    </div>
				                    <small class="text-muted">Due to project already launched, you just only change the deadline</small>
				            	</c:when>
				            	<c:otherwise>
				            		<div class="col-lg-4" style="padding:0">
					                    <div class="card">
					                        <div class="card-body">
					                            <label class="text-label" for="dateRangePickerSample02">Project Period:</label>
					                            <c:if test="${invalidPeriod!=null}">
										        	<h6 style="color:red; margin:5px 0">${invalidPeriod}</h6>
										        </c:if>
					                            <input id="dateRangePickerSample02" name="updated-period" type="text" class="form-control" placeholder="Range example" data-toggle="daterangepicker" data-daterangepicker-drops="up" data-daterangepicker-start-date="${job.getStart_date().toLocalDate().toString() }" data-daterangepicker-end-date="${job.getEnd_date().toLocalDate().toString() }">
					                        	<input type="hidden" value="2" name="dateStatus">	  
					                        </div>
					                    </div>
					                </div>
				            	</c:otherwise>
				            </c:choose>
			                
			                <c:if test="${unable!=null}">
					        	<h6 style="color:red; margin:5px 0">${unable}</h6>
					        </c:if>
		                </div>
		                <!-- // END .modal-body -->
		                <div class="modal-footer">		                
		                    <button type="button" class="btn btn-light" data-dismiss="modal">Cancel</button>
		                	<button type="submit" class="btn btn-primary">Update</button>                    
		                </div>	                
                	</form>	
	                <!-- // END .modal-footer -->
	            </div>
	            <!-- // END .modal-content -->
	        </div>
	        <!-- // END .modal-dialog -->
	    </div>
	    <!-- // END EDIT PROJECT -->		
	    
	    <!-- REMOVE PROJECT -->
	    <div id="modal-warning" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog modal-dialog-centered modal-sm">
	            <div class="modal-content">
	                <div class="modal-body text-center p-4">
	                    <i class="material-icons icon-40pt text-warning mb-2">warning</i>
	                    <h4>Warning!</h4>
	                    <p>All of data will be removed permanently. Are you sure to continue?</p>	
	                    <form action="<%=request.getContextPath() + UrlConst.JOB_REMOVE%>" method="post">
	                    	<input type="hidden" value="${job.getId() }" name="jobID">	                                        
	                    	<button type="submit" class="btn btn-warning my-2">Delete</button>
	                    </form>
	                </div>
	                <!-- // END .modal-body -->
	            </div>
	            <!-- // END .modal-content -->
	        </div>
	        <!-- // END .modal-dialog -->
	    </div>
	    <!-- // END REMOVE PROJECT -->
	    
	    <!-- ---------------------------------------------------------------------------------- -->
	    
	    <!-- EDIT TASK -->
        <div id="modal-edit-task" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modal-large-title" aria-hidden="true">
	        <div class="modal-dialog modal-lg" role="document">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="modal-large-title">Edit Project</h5>
	                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                        <span aria-hidden="true">&times;</span>
	                    </button>
	                </div>
	                
	                <form action="<%=request.getContextPath() + UrlConst.TASK_EDIT%>" method="post"> 
		                <!-- // END .modal-header -->
		                <div class="modal-body">
	                    	<input type="hidden" value="${job.getId()}" name="jobID">	
	                    	<input type="hidden" value="${job.getName()}" name="jobName">	
	                    	<input type="hidden" value="${job.getDescription()}" name="jobDescrpt">
	                    	<input type="hidden" value="${job.getStart_date()}" name="jobStart">	
	                    	<input type="hidden" value="${job.getEnd_date()}" name="jobEnd">	
	                    	<input type="hidden" value="${job.getManager().getId()}" name="jobManager">	
				            <div class="form-group">
				                <label for="updated-name">Project Name:</label>
			                    <c:if test="${invalidName!=null}">
						        	<h6 style="color:red; margin:5px 0">${invalidName}</h6>
						        </c:if>
						        <c:if test="${blankName!=null}">
						        	<h6 style="color:red; margin:5px 0">${blankName}</h6>
						        </c:if>
				                <input type="text" class="form-control" id="updated-name" name="updated-name" placeholder="Project name" value="${job.getName() }">
				            </div>
				            <div class="form-group">
				                <label for="updated-descrpt">Description:</label>
				                <textarea class="form-control" id="updated-descrpt" name="updated-descrpt" rows="4">${job.getName() }</textarea>
				            </div>
				            <div class="form-group">
				                <label for="select01">Manager:</label>
				                <c:if test="${invalidManager!=null}">
						        	<h6 style="color:red; margin:5px 0">${invalidManager}</h6>
						        </c:if>
				                <select id="select01" name="updated-manager" data-toggle="select" class="form-control">
				                	<option value="0">My first option</option>
					                 <c:forEach var="lstManager" items="${lstManager}">
					                    <option value="${lstManager.getId() }" ${lstManager.getId() == job.getManager().getId() ? "selected" : "" }> ${lstManager.getName() }</option>
				                    </c:forEach>
				                </select>
				            </div>
				            <c:choose>
				            	<c:when test="${LocalDateTime.now().isAfter(job.getStart_date())}">
				            		<div class="col-lg-4" style="padding:0">
				                        <div class="card">
				                            <div class="card-body">
				                                <label class="text-label" for="dateRangePickerSample01">Deadline</label>
				                                <input id="dateRangePickerSample01" name="updated-deadline" type="text" class="form-control" placeholder="Date example" data-toggle="daterangepicker" data-daterangepicker-drops="up" data-daterangepicker-start-date="${job.getEnd_date().toLocalDate().toString() }" data-daterangepicker-single-date-picker="true">
				                            	<input type="hidden" value="1" name="dateStatus">	  
				                            </div>
				                        </div>
				                    </div>
				                    <small class="text-muted">Due to project already launched, you just only change the deadline</small>
				            	</c:when>
				            	<c:otherwise>
				            		<div class="col-lg-4" style="padding:0">
					                    <div class="card">
					                        <div class="card-body">
					                            <label class="text-label" for="dateRangePickerSample02">Project Period:</label>
					                            <c:if test="${invalidPeriod!=null}">
										        	<h6 style="color:red; margin:5px 0">${invalidPeriod}</h6>
										        </c:if>
					                            <input id="dateRangePickerSample02" name="updated-period" type="text" class="form-control" placeholder="Range example" data-toggle="daterangepicker" data-daterangepicker-drops="up" data-daterangepicker-start-date="${job.getStart_date().toLocalDate().toString() }" data-daterangepicker-end-date="${job.getEnd_date().toLocalDate().toString() }">
					                        	<input type="hidden" value="2" name="dateStatus">	  
					                        </div>
					                    </div>
					                </div>
				            	</c:otherwise>
				            </c:choose>
			                
			                <c:if test="${unable!=null}">
					        	<h6 style="color:red; margin:5px 0">${unable}</h6>
					        </c:if>
		                </div>
		                <!-- // END .modal-body -->
		                <div class="modal-footer">		                
		                    <button type="button" class="btn btn-light" data-dismiss="modal">Cancel</button>
		                	<button type="submit" class="btn btn-primary">Update</button>                    
		                </div>	                
                	</form>	
	                <!-- // END .modal-footer -->
	            </div>
	            <!-- // END .modal-content -->
	        </div>
	        <!-- // END .modal-dialog -->
	    </div>
	    <!-- // END EDIT TASK -->		
	    
	    <!-- REMOVE TASK -->
	    <div id="modal-remove-task" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog modal-dialog-centered modal-sm">
	            <div class="modal-content">
	                <div class="modal-body text-center p-4">
	                    <i class="material-icons icon-40pt text-warning mb-2">warning</i>
	                    <h4>Warning!</h4>
	                    <p>All of data will be removed permanently. Are you sure to continue?</p>	
	                    <form action="<%=request.getContextPath() + UrlConst.TASK_REMOVE%>" method="post">
	                    	                                        
	                    	<button type="submit" class="btn btn-warning my-2">Delete</button>
	                    </form>
	                </div>
	                <!-- // END .modal-body -->
	            </div>
	            <!-- // END .modal-content -->
	        </div>
	        <!-- // END .modal-dialog -->
	    </div>
	    <!-- // END REMOVE TASK -->
	</body>
</html>
