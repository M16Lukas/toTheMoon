<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<!-- Topbar -->
	<nav class="navbar navbar-expand navbar-light sticky-top bg-white topbar justify-content-between">
		<div class="nav-item">
			<a class="navbar-brand" href="/">
				<img alt="" src="/resources/img/company_icon.svg" width="40" height="40">
			</a>
	   	</div>


	   	<!-- Topbar Search -->
        <div class="nav-item navbar-search">
        	<div class="input-group">
                <input class="form-control bg-light border-2 small" id="symbol" type="text" placeholder="Search for symbols" aria-label="Search" aria-describedby="basic-addon2" onkeyup="sendSymbol();"/>
         	</div>
    	</div>
    	
		<c:choose>
			<c:when test="${empty sessionScope.loginEmail }">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="btn" href="/member/register">SIGN UP</a></li>
			    <li class="nav-item"><a class="btn btn-primary" href="/member/login">LOG IN</a></li>
			</ul>
			</c:when>
			<c:otherwise>
				<!-- Topbar Navbar -->
				<ul class="navbar-nav">
					<!-- Nav Item - User Information -->
				   	<li class="nav-item dropdown no-arrow">
				    	<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				           	<span class="mr-2 d-none d-lg-inline text-gray-900">${sessionScope.loginFirstName } ${sessionScope.loginLastName }</span>
			        	</a>
			       		<!-- Dropdown - User Information -->
				        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
							<a class="dropdown-item " href="#" onclick="logOut();">
				                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
				               	Logout
				            </a>
				    	</div>
					</li>
				</ul>
			</c:otherwise>
		</c:choose>
	</nav>
	<!-- End of Topbar -->
	
	<!-- start scroll to page top -->
	<button id="go-top" class="btn-circle btn-lg">
		<i class="fas fa-chevron-up"></i>
	</button>
	<!-- end scroll to page top -->
		
</body>
</html>