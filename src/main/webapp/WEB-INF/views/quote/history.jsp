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
	        <a class="navbar-brand" href="community">Conversations</a>
	        <a class="navbar-brand border-bottom-primary" href="">Historical Data</a>
        </div>
    </nav>	
	<!-- end main navigation-->
		
	<!-- start container-fluid -->
	<div class="container-fluid">	
		<!-- DataTales -->
		<div class="card shadow mb-4">
        	<div class="card-header py-3">
             	<form action="" method="get">
             		<div class="row">
             			<div class="col-sm-12 col-md-3">
				         	<span>Show</span>
				           	<label>
					        	<select name="countPerPage" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm">
					           		<option value="10">10</option>
					           		<option value="25">25</option>
					           		<option value="50">50</option>
					           		<option value="100">100</option>
					           	</select>
				           	</label>
				       	</div>
				        <div class="col-sm-12 col-md-3">
				        	<div class="dataTables_length" id="dataTable_length">
				        		<span>Frequency</span>
				           		<label> 
					           		<select name="frequency" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm">
					           			<option value="1D">Daily</option>
					           			<option value="1W">Weekly</option>
					           			<option value="1M">Monthly</option>
					           		</select>
				           		</label>
				           	</div>
				      	</div>
				      	<div class="col-sm-12 col-md-3">
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
			           		<div class="col-sm-12 col-md-5">
			           			<div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">
			           				Currency in USD
			           			</div>
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
			                     				<td>${list.volume }</td>
			                     			</tr>
			                     		</c:forEach>
			                     	</tbody>
			                   </table>
			           		</div>
			           	</div>
			           	<div class="row">
			           		<div class="col-sm-12 col-md-5">
			           			<div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">
			           				*Close price adjusted for splits.&nbsp;**Adjusted close price adjusted for both dividends and splits.
			           			</div>
			           		</div>
			           		<div class="col-sm-12 col-md-7">
			           			<input type="hidden" id="frequency" value="${frequency }">
			           			<input type="hidden" id="countPerPage" value="${countPerPage }">
			           			<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
			           				<ul class="pagination">
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
	</div>
	<!-- end container-fluid -->


	<!-- footer -->
	<%@ include file="../includes/footer.jsp" %>
</body>
</html>