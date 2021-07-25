<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<!-- start quote-header-info -->
	<div class="row font-weight-bold text-gray-100 bg-gradient-primary" id="quote-header-info">
		<div class="col">
			${info.symbol }
			<br>
			${info.name}
			<br>
			${info.exchange}
			<br>
			<c:set var="upDown" value="${info.quote.price - info.quote.previousClose}" />
			<c:choose>
				<c:when test="${upDown gt 0}">
			    	<div class="text-lg" style="color: #66CC00;">
			    		<span class="h1">${info.quote.price}</span>
			    		<span>+<c:out value="${upDown }"/></span>
			    		<span>(+${(upDown / info.quote.previousClose) * 100}%)</span>
			    	</div>
			 	</c:when>
			    <c:otherwise>
			    	<div class="text-lg" style="color: #E85050;">
			    		<span class="h1">${info.quote.price}</span>
			    		<span><c:out value="${upDown }"/></span>
			    		<span>(${(upDown / info.quote.previousClose) * 100}%)</span>
			    	</div>
			   	</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- end quote-header-info -->
		
</body>
</html>