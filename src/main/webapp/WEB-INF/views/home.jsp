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
	${serverTime }
	
	<br>
	
	<form action="quote/search">
		<input type="text" name="symbol">
		<input type="submit" value="티거 검색">
	</form>
</body>
</html>