<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    
    <!-- jquery ui -->
    <link href="/resources/css/jquery-ui.css" rel="stylesheet">    
    
</head>
<body>
	<div class="container">
		<!-- start container-fluid -->
		<div class="container-fluid">	
			<!-- body-header -->
			<%@ include file="../includes/header-topbar.jsp" %>
			<%@ include file="../includes/header-qoute.jsp" %>
			<%@ include file="../includes/body-menubar.jsp" %>
		
			<!-- hidden type -->
			<input type="hidden" id="frequency" value="${frequency }">
			<input type="hidden" id="countPerPage" value="${countPerPage }">
				
			<!-- 검색 기간 설정 -->
			<c:set var="from">
				<fmt:formatDate value="${period1}" pattern="yyyy-MM-dd" />
			</c:set>
	
			<c:set var="to">
				<fmt:formatDate value="${period2}" pattern="yyyy-MM-dd" />
			</c:set>
			
			
			
			<!-- DataTales -->
			<div class="card mb-4">
	        	<div class="card-header py-2">
	             	<form action="" method="get">
	             		<div class="row">
	             			<div class="col-sm-12 col-md-5">
								<span>Time Period: </span>
								<div class="dropdown" style="display: inline-block;">
	                               	<button class="btn btn-light dropdown-toggle" type="button"
	                                        id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
	                                        aria-expanded="false">
	                                    <span id="fromDate">${from }</span>
	                                    ~
	                                    <span id="toDate">${to }</span>
	                             	</button>
	                                <div class="dropdown-menu animated--fade-in" aria-labelledby="dropdownMenuButton">
	                             		<span>Start Date</span>
	                             		<br>
	                             		<input type="text" id="period1" name="period1">            
	                             		<div class="dropdown-divider"></div>
	                             		<span>End Date</span>
	                             		<br>
	                             		<input type="text" id="period2" name="period2">            
	                                 </div>
	                           	</div>
	             			</div>
	             			<div class="col-sm-12 col-md-2">
					         	<span>Show: </span>
					           	<div style="display: inline-block;">
						        	<select name="countPerPage" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm">
						           		<option value="10" <c:if test="${countPerPage == '10'}">selected</c:if>>10</option>
						           		<option value="25" <c:if test="${countPerPage == '25'}">selected</c:if>>25</option>
						           		<option value="50" <c:if test="${countPerPage == '50'}">selected</c:if>>50</option>
						           		<option value="100" <c:if test="${countPerPage == '100'}">selected</c:if>>100</option>
						           	</select>
					           	</div>
					       	</div>
					        <div class="col-sm-12 col-md-3">
					        	<div class="dataTables_length" id="dataTable_length">
					        		<span>Frequency: </span>
					           		<div style="display: inline-block;"> 
						           		<select name="frequency" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm">
						           			<option value="1D" <c:if test="${frequency == '1D'}">selected</c:if>>Daily</option>
						           			<option value="1W" <c:if test="${frequency == '1W'}">selected</c:if>>Weekly</option>
						           			<option value="1M" <c:if test="${frequency == '1M'}">selected</c:if>>Monthly</option>
						           		</select>
					           		</div>
					           	</div>
					      	</div>
					      	<div class="col-sm-12 col-md-1">
					        	<div class="dataTables_length" id="dataTable_length">
					        		<input type="submit" class="btn btn-primary" value="Apply">
					        	</div>
					        </div>
	             		</div>
				   	</form>
	           	</div>
				<div class="card-body">
		        	<div class="table-responsive">
		        		<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
		        			<div class="row">
				           		<div class="col-sm-10">Currency in USD</div>
				           		<div class="col-sm-2">
				           			<a class="nav-link" href="javascript:historyDownload();">
				           				<i class="fas fa-file-download"></i>
				           				<span>Download</span>
				           			</a>
				           		</div>
				           	</div>
				           	<div class="row">
				           		<div class="col-sm-12">
				           			<table class="table table-bordered" id="dataTable" style="width: 100%; cellspacing: 0;">
				                    	<thead>
				                        	<tr>
				                            	<th>Date</th>
				                                <th>Open</th>
				                                <th>High</th>
				                                <th>Low</th>
				                                <th>Close*</th>
				                                <th>Adj Close**</th>
				                                <th>Volume</th>
				                         	</tr>
				                     	</thead>
				                     	<tbody>
				                     		<c:forEach var="list" items="${lists }">
				                     			<tr>
				                     				<td>${list.date }</td>
				                     				<td>${list.open }</td>
				                     				<td>${list.high }</td>
				                     				<td>${list.low }</td>
				                     				<td>${list.close }</td>
				                     				<td>${list.adjClose }</td>
				                     				<td><fmt:formatNumber value="${list.volume }" pattern="#,###,###"/></td>
				                     			</tr>
				                     		</c:forEach>
				                     	</tbody>
				                   </table>
				           		</div>
				           	</div>
				           	<div class="row">
				           		<div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">
				           			<p>*Close price adjusted for splits.&nbsp;**Adjusted close price adjusted for both dividends and splits.</p>
				           		</div>
				           	</div>
				           	<div class="row">
				           		<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
				           			<ul class="pagination justify-content-center">
				           				<c:if test="${page.prev }">
				           					<li class="paginate_button page-item previous" id="dataTable_previous">
					          					<a href="javascript:historyPaging(${page.startPageGroup - page.pagePerGroup });" aria-controls="dataTable" class="page-link">Previous</a>
					           				</li>
				           				</c:if>
				           				<c:forEach var="num" begin="${page.startPageGroup }" end="${page.endPageGroup }">
				        	   				<li class="paginate_button page-item <c:if test='${page.currentPage eq num }' >active</c:if>">
					           					<a href="javascript:historyPaging(${num });" aria-controls="dataTable" aria-controls="dataTable" class="page-link">${num }</a>
					           				</li>
				           				</c:forEach>
				           				<c:if test="${page.next }">
				           					<li class="paginate_button page-item next" id="dataTable_next">
					           					<a href="javascript:historyPaging(${page.startPageGroup + page.pagePerGroup });" aria-controls="dataTable" class="page-link">Next</a>
					           				</li>
				           				</c:if>
				           			</ul>
				           		</div>
				           	</div>
		        		</div>
		        	</div>
		        </div>
		    </div>
		</div>
		<!-- end container-fluid -->
	</div>


	<!-- footer -->
	<%@ include file="../includes/footer.jsp" %>
	<script src="/resources/js/js-histroricalData.js"></script>
</body>
</html>