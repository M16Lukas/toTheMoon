<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>TTM - To the Moon</title>
    
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" type="text/css" />
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/resources/home/css/styles.css" rel="stylesheet" />
    
    <!-- Page Content -->

    <!-- Custom fonts for this template-->
    <link href="/resources/pages/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/resources/pages/css/sb-admin-2.min.css" rel="stylesheet">

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
    
	
	<!-- Begin Page Content -->
    <div class="container-fluid">
    	<div class="row">
			<c:forEach var="stock" items="${stocks }">
				<div class="col-xl-3 col-md-6 mb-4">
	            	<div class="card border-left-primary shadow h-100 py-2">
	                	<div class="card-body">
	                    	<div class="row no-gutters align-items-center">
	                        	<div class="col mr-2">
	                            	<div class="font-weight-bold text-primary text-uppercase mb-1">
	                                	${stock.value.name}
	                                </div>
	                                <div class="h5 mb-0 font-weight-bold text-gray-800">
	                                	${stock.value.quote.price }
	                                </div>
	                                <div>
	                                	<c:set var="upDown" value="${stock.value.quote.price - stock.value.quote.previousClose}" />
		                                <c:choose>
		                                	<c:when test="${upDown gt 0}">
		                                		+<c:out value="${upDown }" />
		                                	</c:when>
		                                	<c:otherwise>
		                                		<c:out value="${upDown }" />
		                                	</c:otherwise>
		                                </c:choose>
	                                </div>
	                           	</div>
	                            <div class="col-auto">
	                            	 <c:choose>
		                                <c:when test="${upDown gt 0}">
		                               		<i class="fas fa-arrow-up fa-2x" style="color: #1cc88a;"></i>
		                               	</c:when>
		                               	<c:otherwise>
		                               		<i class="fas fa-arrow-down fa-2x" style="color: #e74a3b;"></i>
		                            	</c:otherwise>
		                        	</c:choose>
	                        	</div>
	                    	</div>
	                	</div>
	            	</div>
              	</div>
			</c:forEach>
     	</div>
     </div>
     <!-- /.container-fluid -->
	
	<!-- Bootstrap core JS-->
		
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="/resources/home/js/scripts.js"></script>
    <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
    <!-- * *                               SB Forms JS                               * *-->
    <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
    <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
    <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	
	<!-- Page Content -->

    <script src="/resources/pages/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/pages/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/pages/js/sb-admin-2.min.js"></script>

</body>
</html>