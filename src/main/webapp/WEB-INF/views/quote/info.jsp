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
	<div class="container">
		<!-- bode-header -->
		<%@ include file="../includes/header.jsp" %>
		
			
		<!-- start container-fluid -->
		<div class="container-fluid">		
			<!-- start main navigation-->
			<nav class="navbar navbar-light bg-white static-top ">
		    	<div>
		        	<a class="navbar-brand border-bottom-primary" href="">Summary</a>
			        <a class="navbar-brand" href="/quote/${info.symbol }/community">Conversations</a>
			        <a class="navbar-brand" href="/quote/${info.symbol }/history">Historical Data</a>
		        </div>
		    </nav>	
			<!-- end main navigation-->
			<div class="row">
				<!-- summary -->
				<div class="col-lg-6">
					<div class="card shadow mb-4">
	                	<div class="card-header py-3">
	                   	</div>
	                    <div class="card-body">
	                    	<table class="table table-bordered" id="dataTable" style="width: 100%; cellspacing=0;">
	                    		<tbody>
									<tr>
										<td>Open</td>
										<td>${info.quote.open}</td>
										<td>Previous Close</td>
										<td>${info.quote.previousClose}</td>
									</tr>
									<tr>
										<td>High</td>
										<td>${info.quote.dayHigh}</td>
										<td>Low</td>
										<td>${info.quote.dayLow}</td>
									</tr>
									<tr>
										<td>Ask</td>
										<td>${info.quote.ask}</td>
										<td>Bid</td>
										<td>${info.quote.bid}</td>
									</tr>
									<tr>
										<td>PE Ratio(P/E)</td>
										<td>${info.stats.pe}</td>
										<td>EPS</td>
										<td>${info.stats.eps}</td>
									</tr>
									<tr>
										<td>Market Cap</td>
										<td>${info.stats.marketCap}</td>
										<td>dividend</td>
										<td>${info.dividend.annualYield}</td>
									</tr>
									<tr>
										<td>52 Week High</td>
										<td>${info.quote.yearHigh}</td>
										<td>52 Week Low</td>
										<td>${info.quote.yearLow}</td>
									</tr>
									<tr>
										<td>Volume</td>
										<td>${info.quote.volume}</td>
										<td>Avg. Volume</td>
										<td>${info.quote.avgVolume}</td>
									</tr>
								</tbody>
							</table>
	                   	</div>
	               	</div>
				</div>
				<!-- chart -->
				<div class="col-lg-6">
					<div class="card shadow mb-4">
	                	<div class="card-header py-3">
	                    	<a>3D</a>
	                    	<a>5D</a>
	                    	<a>1M</a>
	                    	<a>3M</a>
	                    	<a>1Y</a>
	                    	<a>5Y</a>
	                   	</div>
	                    <div class="card-body">
	                    	<div class="chart-area">
	                    		
	                        	<canvas id="myAreaChart"></canvas>
	                        </div>
	                   	</div>
	               	</div>
				</div>
			</div>
		</div>
		<!-- end container-fluid -->
	</div>
	

	<!-- footer -->
	<%@ include file="../includes/footer.jsp" %>
</body>
</html>