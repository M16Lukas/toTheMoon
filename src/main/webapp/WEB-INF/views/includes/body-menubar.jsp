<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<ul id="MenuTabs" class="nav m-2">
		<li class="nav-item">
			<a id="summaryMenuBtn" class="nav-link" aria-current="page" href="/quote/${info.symbol }">
				Summary
			</a>
		</li>
		<li class="nav-item">
			<a id="chartMenuBtn" class="nav-link active" aria-current="page" href="/quote/${info.symbol }/chart">
				Chart
			</a>
		</li>
		<li class="nav-item">
			<a id="conversationsMenuBtn" class="nav-link" aria-current="page" href="/quote/${info.symbol }/community">
				Conversations
			</a>
		</li>
		<li class="nav-item">
			<a id="historicalDataMenuBtn" class="nav-link" aria-current="page" href="/quote/${info.symbol }/history">
				Historical Data
			</a>
		</li>
	</ul>
</body>
</html>