<%@page import="cybersoft.javabackend.crm.util.UrlConst"%>
<%@page import="cybersoft.javabackend.crm.util.ComConst"%>
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
	<style>
		.btn-custom-style {
			float:left;
			margin-left:1rem;
		}
		.link-name:hover {
			cursor: pointer;
		    color: blue;
		    text-decoration: underline;
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
		                        <li class="breadcrumb-item"><a href="<%=request.getContextPath() + UrlConst.HOME%>">Home</a></li>
		                        <li class="breadcrumb-item active" aria-current="page">
		                            Create Job
		                        </li>
		                    </ol>
		                </nav>
		                <h1 class="m-0">Task Detail</h1>
		            </div>
		            <c:choose>
		            	<c:when test="${user.getRole().getId()!=ComConst.ROLE_USER}">
		            		<div class="ml-auto">
				                <div class="btn-custom-style">
				                	<a href="" data-toggle="modal" data-target="#modal-edit-task" class="btn btn-light">
					                	<i class="material-icons icon-30pt text-muted mr-1">build</i>
					    				Edit
					    			</a>
				                </div>
				                <div class="btn-custom-style">
				                	<a href="" data-toggle="modal" data-target="#modal-remove-task" class="btn btn-light">
					                	<i class="material-icons icon-30pt text-muted mr-1">delete</i>
					    				Remove
					    			</a>
				                </div>
				            </div>
		            	</c:when>
		            </c:choose>
		            
		        </div>
		    </div>
		</div>
		<div class="container page__container">
	        <!-- Page Content -->
	        <div class="row" style="padding: 10px; border-radius: 20px; border-style: solid; background: white; padding-bottom: 15px">
	            <div class="col-lg">
                	<div class="form-group">
		                <span style="font-weight: bolder;">Task Name:</span>		                
	              		<span>${task.getName() }</span>
		            </div>	
    				<div class="form-group">
			              <span style="font-weight: bolder;">Employee:</span>
			              <a class="link-name" href="<%= request.getContextPath() + UrlConst.USER_PROFILE %>?id=${task.getUser().getId()}">${task.getUser().getName() }</a>
	          		</div>   
	          		<div class="form-group">
		                <span style="font-weight: bolder;">From </span>		                
	              		<span>${task.getStart_date().toLocalDate() }</span>
	              		<span style="font-weight: bolder; margin:0 10px">to</span>		                
	              		<span>${task.getEnd_date().toLocalDate() }</span>
		            </div>
	          		<c:choose>
	    				<c:when test="${LocalDateTime.now().isAfter(task.getEnd_date())}">
	    					<div style="display:inline-block;">
			          			<span style="float:right;">Finished!</span>
			          		</div>
			          		<div class="progress" style="height:20px">
			  					<div class="progress-bar bg-success" role="progressbar" style="width:100%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">100%</div>
							</div>
	    				</c:when>
	    				<c:when test="${LocalDateTime.now().isBefore(task.getStart_date())}">
	    					<div style="display:inline-block;">
			          			<span style="float:right;">Not started yet!</span>
			          		</div>
			          		<div class="progress" style="height:20px">
			  					<div class="progress-bar bg-info" role="progressbar" style="width:0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">0%</div>
							</div>
	    				</c:when>
	    				<c:otherwise>
	    					<div style="display:inline-block;">
			          			<span style="float:right;">${Duration.between(LocalDateTime.now().minusDays(1), task.getEnd_date()).toDays() } days left</span>
			          		</div>
			          		<div class="progress" style="height:20px">
			  					<div class="progress-bar bg-info" role="progressbar" style="width: ${Math.round((Duration.between(task.getStart_date(), LocalDateTime.now().minusDays(1)).toDays()*100/Duration.between(task.getStart_date(), task.getEnd_date()).toDays())*100)/100 }%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
			  					${Math.round((Duration.between(task.getStart_date(), LocalDateTime.now().minusDays(1)).toDays()*100/Duration.between(task.getStart_date(), task.getEnd_date()).toDays())*100)/100 }%</div>
							</div>
	    				</c:otherwise>
    				</c:choose>	       		
                </div> 
                <c:choose>		            	
		            	<c:when test="${user.getId()==task.getUser().getId() && task.getStatus().getId()==ComConst.STATUS_DANGTHUCHIEN}">
			            	<div class="ml-auto">
			            		<div class="btn-custom-style">
				                	<a href="" data-toggle="modal" data-target="#modal-commit-task" class="btn btn-light">
					                	<i class="material-icons icon-30pt text-muted mr-1">done</i>
					    				Commit
					    			</a>
				    			</div>
			                </div>
		            	</c:when>
		            </c:choose>
            </div>
        </div>
        
        <!-- EDIT TASK -->
        <div id="modal-edit-task" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modal-large-title" aria-hidden="true">
	        <div class="modal-dialog modal-lg" role="document">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="modal-large-title">Edit Task</h5>
	                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                        <span aria-hidden="true">&times;</span>
	                    </button>
	                </div>
	                
	                <form action="<%=request.getContextPath() + UrlConst.TASK_EDIT%>" method="post"> 
		                <!-- // END .modal-header -->
		                <div class="modal-body">
		                	<input type="hidden" value="${job.getId()}" name="jobID">	
	                    	<input type="hidden" value="${task.getId()}" name="taskID">	
	                    	<input type="hidden" value="${task.getStart_date()}" name="jobStart">	
				            <div class="form-group">
				                <label for="updated-name">Task Name:</label>
			                    <c:if test="${invalidName!=null}">
						        	<h6 style="color:red; margin:5px 0">${invalidName}</h6>
						        </c:if>
						        <c:if test="${blankName!=null}">
						        	<h6 style="color:red; margin:5px 0">${blankName}</h6>
						        </c:if>
				                <input type="text" class="form-control" id="updated-name" name="updated-name" placeholder="Project name" value="${task.getName() }">
				            </div>
				            <div class="form-group">
				                <label for="select01">Staff:</label>
				                <c:if test="${invalidManager!=null}">
						        	<h6 style="color:red; margin:5px 0">${invalidManager}</h6>
						        </c:if>
				                <select id="select01" name="updated-user" data-toggle="select" class="form-control">
				                	<option value="0">My first option</option>
					                 <c:forEach var="lstUser" items="${lstUser}">
					                    <option value="${lstUser.getId() }" ${lstUser.getId() == task.getUser().getId() ? "selected" : "" }> ${lstUser.getName() }</option>
				                    </c:forEach>
				                </select>
				            </div>
				            <c:choose>
				            	<c:when test="${LocalDate.now().isAfter(task.getStart_date().toLocalDate())}">
				            		<div class="col-lg-4" style="padding:0">
				                        <div class="card">
				                            <div class="card-body">
				                                <label class="text-label" for="dateRangePickerSample01">Deadline</label>
				                                <input id="dateRangePickerSample01" name="updated-deadline" type="text" class="form-control" placeholder="Date example" data-toggle="daterangepicker" data-daterangepicker-drops="up" data-daterangepicker-start-date="${task.getEnd_date().toLocalDate().toString() }" data-daterangepicker-single-date-picker="true">
				                            	<input type="hidden" value="1" name="dateStatus">	  
				                            </div>
				                        </div>
				                    </div>
				                    <small class="text-muted">Due to the task already launched, you just only change the deadline</small>
				            	</c:when>
				            	<c:otherwise>
				            		<div class="col-lg-4" style="padding:0">
					                    <div class="card">
					                        <div class="card-body">
					                            <label class="text-label" for="dateRangePickerSample02">Task Period:</label>
					                            <c:if test="${invalidPeriod!=null}">
										        	<h6 style="color:red; margin:5px 0">${invalidPeriod}</h6>
										        </c:if>
					                            <input id="dateRangePickerSample02" name="updated-period" type="text" class="form-control" placeholder="Range example" data-toggle="daterangepicker" data-daterangepicker-drops="up" data-daterangepicker-start-date="${task.getStart_date().toLocalDate().toString() }" data-daterangepicker-end-date="${task.getEnd_date().toLocalDate().toString() }">
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
	                    <p>Continue to remove this task?</p>	
	                    <form action="<%=request.getContextPath() + UrlConst.TASK_REMOVE%>" method="post">
	                    	<input type="hidden" value="${task.getId()}" name="taskID">	                                        
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
	    
	    <!-- COMMIT TASK -->
	    <div id="modal-commit-task" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog modal-dialog-centered modal-sm">
	            <div class="modal-content">
	                <div class="modal-body text-center p-4">
	                    <i class="material-icons icon-40pt text-success mb-2">done</i>
	                    <p>Confirm to commit this task?</p>	
	                    <form action="<%=request.getContextPath() + UrlConst.TASK_COMMIT%>" method="post">
	                    	<input type="hidden" value="${task.getId()}" name="taskID">	                                        
	                    	<button type="submit" class="btn btn-success my-2">Commit</button>
	                    </form>
	                </div>
	                <!-- // END .modal-body -->
	            </div>
	            <!-- // END .modal-content -->
	        </div>
	        <!-- // END .modal-dialog -->
	    </div>
	    <!-- // END COMMIT TASK -->
	</body>
</html>