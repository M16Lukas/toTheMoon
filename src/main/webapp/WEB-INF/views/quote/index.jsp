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
    <link href="/resources/css/function-style.css" rel="stylesheet">

</head>
<body>
	<div class="container">
			
		<!-- start container-fluid -->
		<div class="container-fluid">		
			<!-- body-header -->
			<%@ include file="../includes/header-topbar.jsp" %>
			<%@ include file="../includes/header-qoute.jsp" %>
			<%@ include file="../includes/body-menubar.jsp" %>
			

			<!-- chart -->
			<input id="chartSymbol" type="hidden" value="${info.symbol }">
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					Chart (3 Month)
				</div>
				<div class="card-body">
					<div class="row chart-area">
						<div id="chartContainer" style="margin: 0 auto;"></div>
					</div>
				</div>
			</div>
			<!-- end chart -->


			<!-- summary -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					Summary
				</div>
				<div class="card-body">
					<table class="table table-bordered" id="dataTable" style="width: 100%;">
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
			<!-- end summary -->


			<!-- news -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					News (Sources © Google News 2021)</div>
				<div class="card-body">
					<c:forEach var="newsList" items="${newsLists}">
						<div class="mb-2">
							<div class="card h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="h5 mb-0 font-weight-bold text-gray-800">
												<a href="${newsList.link }" target="_blank">${newsList.title }</a>
											</div>
											${newsList.pubDate }
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<!-- end news -->
		</div>
		<!-- end container-fluid -->
	</div>
	<!-- end container -->
	

	<!-- footer -->
	<%@ include file="../includes/footer.jsp" %>
	<script src="/resources/js/js-chart.js"></script>
</body>
</html>