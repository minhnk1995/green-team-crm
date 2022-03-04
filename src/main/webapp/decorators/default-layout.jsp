<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<title>
			<dec:title />
		</title>
		<jsp:include page="/decorators/layout/header.jsp" />
		<dec:head />
	</head>
	<body class="layout-fixed">
		<div class="preloader"></div>
		<!-- Header Layout -->
    	<div class="mdk-header-layout js-mdk-header-layout">
    		<jsp:include page="/decorators/layout/topbar.jsp" />
    		<div class="mdk-header-layout__content page">
    			<jsp:include page="/decorators/layout/navbar.jsp" />
    			<dec:body />
    		</div>
    	</div>
    	
		<jsp:include page="/decorators/layout/footer.jsp" />
		<dec:getProperty property="page.scripts" />
	</body>
</html>