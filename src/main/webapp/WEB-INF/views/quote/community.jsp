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
			
			
			<!-- community -->
			<div class="community-body">
				<div class="row">
					<div class="row">
						<p class="text-lg text-gray-900"> ${reactions } reactions on ${info.symbol } conversion</p> 
					</div>
					<div class="row">
						<c:choose>
							<c:when test="${empty sessionScope.loginEmail }">
								<div class="alert alert-light mb-4 col-lg-9">
	                            	<a href="/member/login">Sign in to post a message</a>
	                            </div>	
							</c:when>
							<c:otherwise>
								<form action="community/register" method="post">
									<div class="input-group">
										<textarea class="form-control bg-light border-1 small" name="content" onkeyup="resize(this)" 
				                                maxlength="8000" required="required" style="height: 70px;"></textarea>
										<div class="input-group-append">
											<button class="btn btn-info" type="submit">Register</button>
										</div>
									</div>
								</form>
								<div class="dropdown-divider"></div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				
				<div class="row">
				</div>
				<div>
					<c:forEach var="list" items="#{lists }">
						<!-- Start Content Card -->
						<div class="card mb-2">
							<!-- Card Header - Dropdown -->
							<div class="card-header d-flex bd-highlight flex-row align-items-center">
								<span class="h6 m-0 font-weight-bold text-primary flex-grow-1 bd-highlight">${list.email}</span>
								<span class="bd-highlight">${list.content_indate }</span>

								<c:if test="${sessionScope.loginEmail eq list.email }">
									<div class="dropdown no-arrow bd-highlight">
										<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
											<i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
										</a>
										<ul class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
											<li>
												<a class="dropdown-item" href="javascript:contentModefyForm(${list.content_nm });">modify</a>
											</li>
											<li>
												<a class="dropdown-item" href="community/remove?nm=${list.content_nm }">remove</a>
											</li>
										</ul>
									</div>
								</c:if>
							</div>
							<!-- Card Body -->
							<div class="card-body">
								<div id="${list.content_nm }content" class="row card-text">
									<span>${list.content }</span>
								</div>
								<div class="row">
									<div class="col">
										<button class="btn text-gray-900" type="button"
											data-bs-toggle="collapse" data-bs-target="#reply${list.content_nm }" aria-expanded="false" aria-controls="reply${list.content_nm }"
											onclick="viewReply(${list.content_nm });">
											<span>reply(${list.reply_cnt })</span>
										</button>
										<button class="btn text-gray-900" onclick="contentUp(${list.content_nm });">
											<i class="far fa-thumbs-up"></i> 
											<span id="${list.content_nm }upCnt">${list.content_up }</span>
										</button>
										<button class="btn text-gray-900" onclick="contentDown(${list.content_nm });">
											<i class="far fa-thumbs-down"></i> 
											<span id="${list.content_nm }downCnt">${list.content_down }</span>
										</button>
									</div>
								</div>
								<!-- Start Reply -->
								<div class="collapse" id="reply${list.content_nm }">
									<!-- Start register reply -->
									<div class="dropdown-divider"></div>
									<c:choose>
										<c:when test="${empty sessionScope.loginEmail }">
											<div class="alert alert-light">
												<a href="/member/login">Sign in to post a Reply</a>
											</div>
										</c:when>
										<c:otherwise>
											<form id="insertReply${list.content_nm }" method="post">
												<div class="input-group">
													<textarea class="form-control bg-light border-0 small" name="reply" onkeyup="resize(this)" maxlength="2000"
															  required="required" style="height: 50px;"></textarea>
													<div class="input-group-append">
														<button class="btn btn-info" type="button" onclick="clkInsertReplyBtn(${list.content_nm });">
															Register
														</button>
													</div>
												</div>
											</form>
											<div class="dropdown-divider"></div>
										</c:otherwise>
									</c:choose>
									<!-- end register reply -->
									<!-- start reply list -->
									<input type="hidden" id="loginEmail" value="${sessionScope.loginEmail }">
									<div class="reply-card"></div>
									<!-- end reply list -->
								</div>
								<!-- End Reply -->
							</div>
							<!-- end card body -->
						</div>
						<!-- End Content Card -->
					</c:forEach>
				</div>
			</div>
		</div>
		<!-- end container-fluid -->
	</div>


	<!-- footer -->
	<%@ include file="../includes/footer.jsp" %>
	<script src="/resources/js/js-community.js"></script>
</body>
</html>