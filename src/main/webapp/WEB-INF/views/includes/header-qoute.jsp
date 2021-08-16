<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
$(function(){
	var dailyReturn = $("#dailyReturn").val();
	if (dailyReturn > 0) {
		$("#stockPrice").append("<span>+${info.dailyReturn } (+${info.fluctuationRate }%)</span>")
						.css("color", "#66CC00");
	} else {
		$("#stockPrice").append("<span>${info.dailyReturn } (${info.fluctuationRate }%)</span>")
						.css("color", "#E85050");		
	}
});
</script>
</head>
<body>	
	<!-- start quote-header-info -->
	<input type="hidden" id="dailyReturn" value="${info.dailyReturn }">
	<div class="col font-weight-bold text-gray-100 bg-gradient-primary">
		<span id="symbol" class="h3">${info.symbol }</span>
		<br>
		${info.name}
		<br>
		${info.exchange}
		<br>
		<div class="text-lg" id="stockPrice">
	  		<span class="h1 font-weight-bold">${info.quote.price}</span>
		</div>
	</div>
	<!-- end quote-header-info -->
		
</body>
</html>