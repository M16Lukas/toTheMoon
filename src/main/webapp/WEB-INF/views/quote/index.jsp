<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- js -->
<script type="text/javascript">
	
</script>
</head>
<body>

	symbol : ${info.symbol }
	<br>
	name : ${info.name}
	<br>
	stockExchange : ${info.exchange}
	<br>
	<h2>price : ${info.quote.price} </h2> 
	<p>----------------------------------------------------</p>
	previousClose : ${info.quote.previousClose}
	<br>
	open : ${info.quote.open}
	<br>
	dayLow : ${info.quote.dayLow}
	<br>
	dayHigh : ${info.quote.dayHigh}
	<br>
	Avg.Volume : ${info.quote.avgVolume}
	<br>
	annualYield : ${info.dividend.annualYield}
	<br>
	annualYieldPercent : ${info.dividend.annualYieldPercent}
	
	

</body>
</html>