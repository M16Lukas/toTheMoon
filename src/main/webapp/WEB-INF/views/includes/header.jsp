<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Topbar -->
	<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top">
		<div>
			<a class="navbar-brand" href="/">TTM</a>
	   	</div>
	    <div>
	    	<a class="navbar-brand" href="/">HOME</a>
		    <a class="navbar-brand" href="/quote">MARKET</a>
		    <a class="navbar-brand" href="/help">HELP</a>
	   	</div>
	   	<!-- Topbar Search -->
        <div class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
        	<div class="input-group">
                <input class="form-control bg-light border-0 small" id="symbol" type="text" placeholder="Symbol" aria-label="Search" aria-describedby="basic-addon2" onkeyup="sendSymbol();"/>
         	</div>
    	</div>
		<div>
			<c:choose>
				<c:when test="${empty sessionScope.loginEmail }">
					<div>
				    	<a class="btn btn-primary" href="/member/register">SIGN UP</a>
				        <a class="btn btn-primary" href="/member/login">LOG IN</a>
				    </div>
				</c:when>
				<c:otherwise>
					<!-- Topbar Navbar -->
				    <ul class="navbar-nav ml-auto">
				    	<!-- Nav Item - User Information -->
				        <li class="nav-item dropdown no-arrow">
				        	<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				            	<span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.loginFisrtName }&nbsp;${sessionScope.loginLastName }</span>
				                <img class="img-profile rounded-circle" src="/resources/pages/img/undraw_profile.svg">
				           	</a>
				            <!-- Dropdown - User Information -->
				            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
				            	<a class="dropdown-item" href="#">
					            	<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
					                Profile
				                </a>
				                <a class="dropdown-item" href="#">
				                	<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
				                </a>
				                <a class="dropdown-item" href="#">
				                	<i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
				                    Activity Log
				               	</a>
				                <div class="dropdown-divider"></div>
									<a class="dropdown-item" href="/member/logout">
				                	<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
				                    Logout
				              	</a>
				          	</div>
				       	</li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>
	<!-- End of Topbar -->
	
	<!-- start quote-header-info -->
	<div class="row font-weight-bold text-gray-100 bg-gradient-primary" id="quote-header-info">
		<div class="col">
			${info.symbol }
			<br>
			${info.name}
			<br>
			${info.exchange}
			<br>
			<div>
				<h2>${info.quote.price} </h2> 
				<c:set var="upDown" value="${info.quote.price - info.quote.previousClose}" />
			    <c:choose>
			    	<c:when test="${upDown gt 0}">
			        	+<c:out value="${upDown }"/>
			      	</c:when>
			        <c:otherwise>
			        	<c:out value="${upDown }" />
			       	</c:otherwise>
			 	</c:choose>
			</div>	
		</div>
	</div>
	<!-- end quote-header-info -->
		
</body>
</html>