/*!
* Start Bootstrap - Landing Page v6.0.2 (https://startbootstrap.com/theme/landing-page)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-landing-page/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

// textarea size control

function contentUp(num){
	
	var id = "#" + num + "upCnt";
	
	$.ajax({
		url : "community/up",
		type : "post",
		data : { content_nm : num,
				upCnt : $(id).text()
		},
		dataType : "text",
		success : function(data){
			$(id).text(data);
		},
		error : function(e){
			alert("please sign up");
		}
	});
}

function contentDown(num){
	
	var id = "#" + num + "downCnt";
	
	$.ajax({
		url : "community/down",
		type : "post",
		data : { content_nm : num,
				downCnt : $(id).text()
		},
		dataType : "text",
		success : function(data){
			$(id).text(data);
		},
		error : function(e){
			alert("please sign up");
		}
	});
}


function resize(obj) {
  obj.style.height = "1px";
  obj.style.height = (12+obj.scrollHeight)+"px";
}