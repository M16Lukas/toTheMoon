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

    <title>${info.symbol } - ${info.name}</title>

	<!-- Navigation -->

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
    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
        <a class="navbar-brand" href="/">TTM</a>
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
           <a class="btn btn-primary" href="/member/register">SIGN UP</a>
           <a class="btn btn-primary" href="/member/login">LOG IN</a>
       	</div>

        <!-- Topbar Navbar -->
        <ul class="navbar-nav ml-auto">
        	<!-- Nav Item - User Information -->
          	<li class="nav-item dropdown no-arrow">
          		<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                	<span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
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
                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                    	<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        Logout
                   	</a>
               	</div>
       		</li>
		</ul>
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
	
	<!-- start Main Part -->
	
	<!-- start main navigation-->
	<nav class="navbar navbar-light bg-white static-top ">
    	<div>
        	<a class="navbar-brand" href="">Summary</a>
	        <a class="navbar-brand border-bottom-primary" href="">Conversations</a>
	        <a class="navbar-brand" href="history">Historical Data</a>
        </div>
    </nav>	
	<!-- end main navigation-->
		
	<!-- start container-fluid -->
	<div class="container-fluid">
		<!-- chart -->
		<div>
		</div>
		
		<!-- community -->
		<div>

		</div>
	</div>
	<!-- end container-fluid -->


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