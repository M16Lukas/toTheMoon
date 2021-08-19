<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			
			
			<!-- chart -->
			<input id="chartSymbol" type="hidden" value="${info.symbol }">
			<div class="card shadow mb-4">
				<div class="card-body">
					<!-- start Spinner -->
					<div id="chartPageStockChartSpinner" class="d-flex justify-content-center">
						<div class="spinner-grow text-primary m-5" role="status">
							 <span class="visually-hidden">Loading...</span>
						</div>
					</div>
					<!-- end spinner -->
					<!-- view Stock Chart -->
					<div class="row chart-area" style="height: 500px;">
						<div id="chartContainer" style="margin: 0 auto;"></div>
					</div>
				</div>
			</div>
			<!-- end chart -->
			
		</div>
		<!-- end container-fluid -->
	</div>


	<!-- footer -->
	<%@ include file="../includes/footer.jsp" %>
	<script src="/resources/js/js-chart.js"></script>
</body>
</html>