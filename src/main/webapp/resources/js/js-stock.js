/*!
* Start Bootstrap - Landing Page v6.0.2 (https://startbootstrap.com/theme/landing-page)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-landing-page/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project


function sendSymbol(){
	var symbol = $("#symbol").val();
		
	if(window.event.keyCode === 13 && symbol !== ""){
		location.href = "/quote/" + symbol;	
	} else {
		locatio.herf = "redirect:/" + window.location.pathname;
	}
}


function historyPaging(p){
	var countPerPage = $("#countPerPage").val();
	var frequency 	 = $("#frequency").val();
	var period1 	 = $("#fromDate").text();
	var period2 	 = $("#toDate").text();
	
	
	location.href = "history?period1=" + period1 + "&period2=" + period2 + "&countPerPage=" + countPerPage + "&frequency=" + frequency + "&p=" + p;
}

// historical Quote download (format : Excel)
function historyDownload(){
	var frequency = $("#frequency").val();
	var period1 = $("#fromDate").text();
	var period2 = $("#toDate").text();
	
	location.href = "history/download?period1=" + period1 + "&period2=" + period2 + "&freq=" + frequency;
}
