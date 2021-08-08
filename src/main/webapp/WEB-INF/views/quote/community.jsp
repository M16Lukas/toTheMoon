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
		<!-- body-header -->
		<%@ include file="../includes/header-topbar.jsp" %>
		<%@ include file="../includes/header-qoute.jsp" %>
		
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
					<div class="row">
						<p class="text-lg text-gray-900"> ${reactions } reactions on ${info.symbol } conversion</p> 
					</div>
					<div class="row">
						<c:choose>
							<c:when test="${empty sessionScope.loginEmail }">
								<div class="card mb-4 col-lg-9">
	                                <div class="card-body">
	                                    <a href="/member/login">Sign in to post a message</a>
	                                </div>
	                            </div>	
							</c:when>
							<c:otherwise>
								<form action="community/register" method="post">
									<div class="input-group col-lg-9">
										<textarea class="form-control bg-light border-1 small" name="content" onkeyup="resize(this)" 
				                                maxlength="8000" required="required" style="height: 70px;"></textarea>
										<div class="input-group-append">
											<button class="btn btn-info" type="submit">Register</button>
										</div>
									</div>
								</form>
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
							<ul class="contents">
								<c:forEach var="list" items="#{lists }" >
									<!-- Start Content -->
									<li style="list-style: none;">
				                        <!-- Start Content Card -->
				                        <div class="card mb-4 col-lg-9">
				                        	<!-- Card Header - Dropdown -->
				                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
				                            	<h6 class="m-0 font-weight-bold text-primary">${list.firstName }&nbsp;${list.lastName}&nbsp;${list.content_indate }</h6>
				                                <c:if test="${sessionScope.loginEmail eq list.email }">
				                                	<div class="dropdown no-arrow">
					                                	<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
					                                    	data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					                                   		<i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
					                                  	</a>
					                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
					                                        <a class="dropdown-item" href="javascript:contentModefy(${list.content_nm });">modify</a>
					                                        <a class="dropdown-item" href="community/remove?nm=${list.content_nm }">remove</a>
					                                    </div>
					                               	</div>
				                                </c:if>
				                          	</div>
				                            <!-- Card Body -->
				                            <div class="card-body">
				                            	<div id="${list.content_nm }content" class="row">
				                            		${list.content }
				                            	</div>
				                            	<div class="row">
				                            		<div class="col">
				                            			<button id="${list.content_nm }reply" class="btn text-gray-900" type="button" data-bs-toggle="collapse" 
				                            					data-bs-target="#reply${list.content_nm }" aria-expanded="false" aria-controls="reply${list.content_nm }"
				                            					onclick="viewReply(${list.content_nm });">
				                            				<span>reply()</span>
				                            			</button>
				                            			<button id="${list.content_nm }contentUp" class="btn text-gray-900" onclick="contentUp(${list.content_nm });">
				                            				<i class="far fa-thumbs-up"></i>
				                            				<span id="${list.content_nm }upCnt">${list.content_up }</span>
				                            			</button>
				                            			<button id="${list.content_nm }contentDown" class="btn text-gray-900" onclick="contentDown(${list.content_nm });">
				                            				<i class="far fa-thumbs-down"></i>
				                            				<span id="${list.content_nm }downCnt">${list.content_down }</span>
				                            			</button>
				                            		</div>
				                            	</div>
				                            	<!-- Start Reply -->
												<div class="row collapse" id="reply${list.content_nm }">
													<!-- register reply -->
													<c:if test="${not empty sessionScope.loginEmail }">
														<div class="dropdown-divider"></div>
														<form id="insertReply${list.content_nm }" method="post">
															<div class="input-group">
																<textarea class="form-control bg-light border-0 small" name="reply" onkeyup="resize(this)" 
											                           		maxlength="2000" required="required" style="height: 50px;"></textarea>
																<div class="input-group-append">
																	<button class="btn btn-info" type="button" onclick="clkInsertReplyBtn(${list.content_nm });">
																		Register
																	</button>
																</div>
															</div>
														</form>
													</c:if>
													<div class="dropdown-divider"></div>
													<!-- reply list -->
													<div class="reply-card"></div>
												</div>
												<!-- End Reply -->
				                           	</div>
				                           	<!-- end card body -->
				                      	</div>
				                      	<!-- End Content Card -->
									</li>
									<!-- End Content -->
								</c:forEach>				
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<!-- end container-fluid -->
	</div>


	<!-- footer -->
	<%@ include file="../includes/footer.jsp" %>
</body>
</html>