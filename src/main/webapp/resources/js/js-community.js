/*!
* Start Bootstrap - Landing Page v6.0.2 (https://startbootstrap.com/theme/landing-page)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-landing-page/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

// textarea size control

function resize(obj) {
  obj.style.height = "1px";
  obj.style.height = (12+obj.scrollHeight)+"px";
}

function contentModefy(num){
	
	var id = "#" + num + "content";
	
	var content = "";
		content += '<form action="community/modify?nm=' + num +'" method="post">';
		content += '<div class="input-group">';
		content += '<textarea class="form-control bg-light border-0 small" name="newContent" onkeyup="resize(this)" maxlength="8000" style="height: 70px;">'
					+ $.trim($(id).text()) 
					+ '</textarea>';
		content += '<div class="input-group-append">';
		content += '<button class="btn btn-info" type="submit">Modify</button>';
		content += '</div>';
		content += '</div>';
		content += '</form>';

	$(id).html(content);
}


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

function viewReply(num){
	var re = "#reply" + num + " .reply-card";

	$.ajax({
		url : "community/"+ num,
		type : "get",
		dataType : "json",
		success : function(data){

			var context = "";
			
			$.each(data, function(i){
				context += "<div class='card'>";
				context += "<div class='card-header d-flex bd-highlight flex-row align-items-center'>";
				context += "<span class='h6 m-0 font-weight-bold text-primary flex-grow-1 bd-highlight'>" + this.firstName + "&nbsp;" + this.lastName + "</span>";
				context += "<span class='bd-highlight'>" + this.reply_indate + "</span>";
				context += "</div>";
				context += "<div class='card-body'>"
				context += "<span>" + this.reply + "</span>";
				context += "</div>";
				context += "</div>";
			});
						
			$(re).html(context);
			$("#" + num + "reply span").text("reply(" + Object.keys(data).length + ")");
		},
		error : function(e){ console.log(e); }
	});
	
}

function clkInsertReplyBtn(num){
	var reply = $("#insertReply" + num).serialize();
		
	$.ajax({
		url : "community/"+ num,
		type : "post",
		data : reply,
		success : function(data){
			$("#insertReply" + num + " .input-group textarea").val("");	// textarea reset
			viewReply(num);
		},
		error : function(e){ console.log(e); }
	});
}
