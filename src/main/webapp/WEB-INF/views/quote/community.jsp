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
	<!-- bode-header -->
	<%@ include file="../includes/header.jsp" %>
	
	<!-- start main navigation-->
	<nav class="navbar navbar-light bg-white static-top ">
    	<div>
        	<a class="navbar-brand" href="/quote/${info.symbol }">Summary</a>
	        <a class="navbar-brand border-bottom-primary" href="">Conversations</a>
	        <a class="navbar-brand" href="history">Historical Data</a>
        </div>
    </nav>	
	<!-- end main navigation-->
		
	<!-- start container-fluid -->
	<div class="container-fluid">
		<!-- community -->
		<div class="community-body">
			<div class="row">
				<div class="row text-lg">
					reactions on conversion
				</div>
				<div class="row">
					<c:choose>
						<c:when test="${empty sessionScope.loginEmail }">
							<div class="card mb-4">
                                <div class="card-body">
                                    <a target="_blank" href="/member/login">Sign in to post a message</a>
                                </div>
                            </div>	
						</c:when>
						<c:otherwise>
							<div class="input-wrapper">
								<form action="community/register" method="post">
									<div class="input">
										<textarea class="form-control bg-light border-0 small" name="content" onkeyup="resize(this)" 
		                                	maxlength="8000" required="required" style="width: 60%; height: 70px;"></textarea>
									</div>
									<div class="btn">
										<button type="submit" class="btn btn-info">Reigster</button>
									</div>
								</form>
                            </div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="row">
			</div>
			<div class="row">
				<c:choose>
					<c:when test="${empty lists }">
						<p>Nothing</p>
					</c:when>
					<c:otherwise>
						<ul>
							<c:forEach var="list" items="#{lists }" >
								<li>
			                        <div class="card shadow mb-4">
			                        	<!-- Card Header - Dropdown -->
			                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
			                            	<h6 class="m-0 font-weight-bold text-primary">${list.email }&nbsp;${list.content_indate }</h6>
			                                <div class="dropdown no-arrow">
			                                	<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
			                                    	data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                                   		<i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
			                                  	</a>
			                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
			                                        <a class="dropdown-item" href="#">Action</a>
			                                        <a class="dropdown-item" href="#">Another action</a>
			                                    </div>
			                               	</div>
			                          	</div>
			                            <!-- Card Body -->
			                            <div class="card-body">
			                            	${list.content }
			                            	<br>
			                            	${list.content_up }&nbsp; ${list.content_down }
			                           	</div>
			                      	</div>
								</li>
							</c:forEach>				
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<!-- end container-fluid -->


	<!-- footer -->
	<%@ include file="../includes/footer.jsp" %>
</body>
</html>