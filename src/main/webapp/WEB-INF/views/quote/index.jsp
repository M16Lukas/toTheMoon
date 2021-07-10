<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>
				symbol
			</th>
			<th>
				${info.symbol }
			</th>
		</tr>
		<tr>
			<th>
				name
			</th>
			<th>
				${info.name}
			</th>
		</tr>
		<tr>
			<th>
				stockExchange
			</th>
			<th>
				${info.exchange}
			</th>
		</tr>
		<tr>
			<th>
				price
			</th>
			<th>
				${info.price}
			</th>
		</tr>
		<tr>
			<th>
				change
			</th>
			<th>
				${info.change}
			</th>
		</tr>
		<tr>
			<th>
				peg
			</th>
			<th>
				${info.peg}
			</th>
		</tr>
		<tr>
			<th>
				dividend
			</th>
			<th>
				${info.dividend}
			</th>
		</tr>
	</table>
</body>
</html>