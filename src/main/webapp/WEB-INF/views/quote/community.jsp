<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
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
	<!-- bode-header -->
	<%@ include file="../includes/header.jsp" %>
	
	<!-- start main navigation-->
	<nav class="navbar navbar-light bg-white static-top ">
    	<div>
        	<a class="navbar-brand" href="/quote/${info.symbol }">Summary</a>
	        <a class="navbar-brand border-bottom-primary" href="">Conversations</a>
	        <a class="navbar-brand" href="history">Historical Data</a>
        </div>
    </nav>	
	<!-- end main navigation-->
		
	<!-- start container-fluid -->
	<div class="container-fluid">
		<!-- community -->
		<div class="community-body">
			<div class="row">
			</div>
			<div class="row">
			</div>
			<div class="row">
				<ul>
					
				</ul>
			</div>
		</div>
	</div>
	<!-- end container-fluid -->


	<!-- footer -->
	<%@ include file="../includes/footer.jsp" %>
</body>
</html>