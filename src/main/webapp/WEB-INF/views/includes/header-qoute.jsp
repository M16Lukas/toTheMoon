<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>	
	<!-- start quote-header-info -->
	<div class="col font-weight-bold text-gray-100 bg-gradient-primary">
			<span id="symbol" class="h3">${info.symbol }</span>
			<br>
			${info.name}
			<br>
			${info.exchange}
			<br>
			<c:choose>
				<c:when test="${info.dailyReturn gt 0}">
			    	<div class="text-lg" style="color: #66CC00;">
			    		<span class="h1 font-weight-bold">${info.quote.price}</span>
			    		<span>+${info.dailyReturn } (+${info.fluctuationRate }%)</span>
			    	</div>
			 	</c:when>
			    <c:otherwise>
			    	<div class="text-lg" style="color: #E85050;">
			    		<span class="h1 font-weight-bold">${info.quote.price}</span>
			    		<span>${info.dailyReturn } (${info.fluctuationRate }%)</span>
			    	</div>
			   	</c:otherwise>
			</c:choose>
	</div>
	<!-- end quote-header-info -->
		
</body>
</html>