<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
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
	    <link href="/resources/css/function-style.css" rel="stylesheet">
	    
	    <!-- google OAuth2.0 -->
		<meta name ="google-signin-client_id" content="891963316360-vkp34ieqktbbhba0le9i4unkggkv50nn.apps.googleusercontent.com">
    </head>
    <body>
        <!-- Topbar -->
	    <nav class="navbar navbar-expand navbar-light sticky-top bg-white topbar justify-content-between">
		<div class="nav-item">
			<a class="navbar-brand" href="/"> 
				<img alt=""src="/resources/img/company_icon.svg" width="40" height="40">
			</a>
		</div>

		<!-- start Account Part -->
		<c:choose>
			<c:when test="${empty sessionScope.loginEmail }">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="btn" href="/member/register">SIGN UP</a></li>
				<sec:authorize access="isAnonymous()">
					<li class="nav-item"><a class="btn btn-primary" href="<c:url value='/member/login' />">LOG IN</a></li>
				</sec:authorize>
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
		<!-- end Account Part -->
	</nav>

        <!-- Masthead-->
        <header class="masthead">
            <div class="container position-relative">
                <div class="row justify-content-center">
                    <div class="col-xl-6">
                        <div class="text-center text-white">
                            <!-- Page heading-->
                            <h1 class="mb-5">Enjoy Investing.</h1>
                            <!-- Email address input-->
                            <div class="form-subscribe" id="contactForm" >
                                <div class="row">
                                    <div class="col">
                                        <input class="form-control form-control-lg" id="symbol" type="text" placeholder="Symbol" onkeyup="sendSymbol();"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Icons Grid-->
        <section class="features-icons bg-light text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                            <div class="features-icons-icon d-flex"><i class="bi-window m-auto text-primary"></i></div>
                            <h3>Stocks</h3>
                            <p class="lead mb-0">Invest in thousands of companies using our trading tools and analytics to create your own financial portfolio.</p>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                            <div class="features-icons-icon d-flex"><i class="bi-layers m-auto text-primary"></i></div>
                            <h3>Options</h3>
                            <p class="lead mb-0">Options provide a strategic alternative to just investing in equity.</p>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="features-icons-item mx-auto mb-0 mb-lg-3">
                            <div class="features-icons-icon d-flex"><i class="bi-terminal m-auto text-primary"></i></div>
                            <h3>ETFs</h3>
                            <p class="lead mb-0">Diversify your holdings by investing into a group of stocks with the same convenience as trading a single stock.</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="footer bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 h-100 text-center text-lg-start my-auto">
                        <ul class="list-inline mb-2">
                            <li class="list-inline-item"><a href="#!">About</a></li>
                            <li class="list-inline-item">⋅</li>
                            <li class="list-inline-item"><a href="#!">Contact</a></li>
                            <li class="list-inline-item">⋅</li>
                            <li class="list-inline-item"><a href="#!">Terms of Use</a></li>
                            <li class="list-inline-item">⋅</li>
                            <li class="list-inline-item"><a href="#!">Privacy Policy</a></li>
                        </ul>
                        <p class="text-muted small mb-4 mb-lg-0">&copy; Your Website 2021. All Rights Reserved.</p>
                    </div>
                    <div class="col-lg-6 h-100 text-center text-lg-end my-auto">
                        <ul class="list-inline mb-0">
                            <li class="list-inline-item me-4">
                                <a href="#!"><i class="bi-facebook fs-3"></i></a>
                            </li>
                            <li class="list-inline-item me-4">
                                <a href="#!"><i class="bi-twitter fs-3"></i></a>
                            </li>
                            <li class="list-inline-item">
                                <a href="#!"><i class="bi-instagram fs-3"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
        
        <!-- start scroll to page top -->
		<button id="go-top" class="btn-circle btn-lg">
			<i class="fas fa-chevron-up"></i>
		</button>
		<!-- end scroll to page top -->
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Jquery -->
	    <script src="/resources/js/jquery-3.6.0.js"></script>
	    
        <!-- Google Login -->
		<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
	
        <!-- JS-->
	    <script src="/resources/js/js-member.js"></script>
	    <script src="/resources/js/js-function.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
        
        <script src="/resources/pages/vendor/jquery/jquery.min.js"></script>
	    <script src="/resources/pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	    <!-- Core plugin JavaScript-->
	    <script src="/resources/pages/vendor/jquery-easing/jquery.easing.min.js"></script>
	
	    <!-- Custom scripts for all pages-->
	    <script src="/resources/pages/js/sb-admin-2.min.js"></script>
    </body>
</html>
