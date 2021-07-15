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
	        <a class="navbar-brand" href="community">Conversations</a>
	        <a class="navbar-brand border-bottom-primary" href="">Historical Data</a>
        </div>
    </nav>	
	<!-- end main navigation-->
		
	<!-- start container-fluid -->
	<div class="container-fluid">	
		<!-- DataTales -->
		<div class="card shadow mb-4">
        	<div class="card-header py-3">
             	<div class="row">
			     	<div class="col-sm-12 col-md-6">
			        	<div class="dataTables_length" id="dataTable_length">
			           		<label>Show 
				           		<select name="dataTable_length" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm">
				           			<option value="10">10</option>
				           			<option value="25">25</option>
				           			<option value="50">50</option>
				           			<option value="100">100</option>
				           		</select>
			           		</label>
			           	</div>
			       	</div>
			        <div class="col-sm-12 col-md-6">
			        	<div class="dataTables_length" id="dataTable_length">
			           		<label>Frequency 
				           		<select name="dataTable_length" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm">
				           			<option value="DAILY">Daily</option>
				           			<option value="WEEKLY">Weekly</option>
				           			<option value="MONTHLY">Monthly</option>
				           		</select>
			           		</label>
			           	</div>
			      	</div>
			   	</div>
           	</div>
			<div class="card-body">
	        	<div class="table-responsive">
	        		<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
			           	<div class="row">
			           		<div class="col-sm-12">
			           			<table class="table table-bordered" id="dataTable" style="width: 100%; cellspacing=0;">
			                    	<thead>
			                        	<tr>
			                            	<th>Date</th>
			                                <th>Open</th>
			                                <th>High</th>
			                                <th>Low</th>
			                                <th>Close</th>
			                                <th>Adj Close</th>
			                                <th>Volume</th>
			                         	</tr>
			                     	</thead>
			                     	<tbody>
			                     		<c:forEach var="list" items="${lists }" varStatus="statusNm">
			                     			<tr>
			                     				<c:forEach var="date" items="${dates[statusNm.index] }" varStatus="status">
			                     					<td>${date }</td>
			                     				</c:forEach>
			                     				<td>${list.open }</td>
			                     				<td>${list.high }</td>
			                     				<td>${list.low }</td>
			                     				<td>${list.close }</td>
			                     				<td>${list.adjClose }</td>
			                     				<td>${list.volume }</td>
			                     			</tr>
			                     		</c:forEach>
			                     	</tbody>
			                   </table>
			           		</div>
			           	</div>
			           	<div class="row">
			           		<div class="col-sm-12 col-md-5">
			           			<div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">
			           				Showing 1 to 10 of 57 entries
			           			</div>
			           		</div>
			           		<div class="col-sm-12 col-md-7">
			           			<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
			           				<ul class="pagination">
			           					<li class="paginate_button page-item previous disabled" id="dataTable_previous">
			           						<a href="#" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
			           					</li>
			           					<li class="paginate_button page-item active">
			           						<a href="#" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">1</a>
			           					</li>
			           					<li class="paginate_button page-item ">
			           						<a href="#" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">2</a>
			           					</li>
			           					<li class="paginate_button page-item ">
			           						<a href="#" aria-controls="dataTable" data-dt-idx="3" tabindex="0" class="page-link">3</a>
			           					</li>
			           					<li class="paginate_button page-item ">
			           						<a href="#" aria-controls="dataTable" data-dt-idx="4" tabindex="0" class="page-link">4</a>
			           					</li>
			           					<li class="paginate_button page-item ">
			           						<a href="#" aria-controls="dataTable" data-dt-idx="5" tabindex="0" class="page-link">5</a>
			           					</li>
			           					<li class="paginate_button page-item ">
			           						<a href="#" aria-controls="dataTable" data-dt-idx="6" tabindex="0" class="page-link">6</a>
			           					</li>
			           					<li class="paginate_button page-item next" id="dataTable_next">
			           						<a href="#" aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link">Next</a>
			           					</li>
			           				</ul>
			           			</div>
			           		</div>
			           	</div>
	        		</div>
	        	</div>
	        </div>
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