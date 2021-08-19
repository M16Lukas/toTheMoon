<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
 	<%@ include file="../includes/head-quote.jsp" %>
</head>
<body>
	<div class="container">
			
		<!-- start container-fluid -->
		<div class="container-fluid">		
			<!-- body-header -->
			<%@ include file="../includes/header-topbar.jsp" %>
			<%@ include file="../includes/header-qoute.jsp" %>
			<%@ include file="../includes/body-menubar.jsp" %>
			
			<div class="row mb-4">
				<div class="col-lg-7">
					<!-- summary -->
					<div class="card">
						<div class="card-header py-2">
							Summary
						</div>
						<div class="card-body">
							<table class="table table-bordered" id="dataTable">
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
										<td><fmt:formatNumber value="${info.stats.marketCap}" pattern="#,###,###"/></td>
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
										<td><fmt:formatNumber value="${info.quote.volume}" pattern="#,###,###"/></td>
										<td>Avg. Volume</td>
										<td><fmt:formatNumber value="${info.quote.avgVolume}" pattern="#,###,###"/></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- end summary -->
				</div>
				<div class="col-lg-5">
					<!-- chart -->
					<input id="chartSymbol" type="hidden" value="${info.symbol }">
					<div class="card">
						<div class="card-header py-2">
							Chart (3 Month)
						</div>
						<div class="card-body">
							<!-- start Spinner -->
							<div id="indexPageStockChartSpinner" class="d-flex justify-content-center">
								<div class="spinner-grow text-primary m-5" role="status">
									 <span class="visually-hidden">Loading...</span>
								</div>
							</div>
							<!-- end spinner -->
							<div class="row chart-area">
								<div id="stockChartContainer" style="margin: 0 auto;"></div>
							</div>
						</div>
					</div>
					<!-- end chart -->
				</div>
			</div>

			<!-- news -->
			<div class="card mb-4">
				<div class="card-header py-2">
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