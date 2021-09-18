/*
 * textarea sizing
 */
function resize(obj) {
  obj.style.height = "1px";
  obj.style.height = (12+obj.scrollHeight)+"px";
}


/*
 * modify textarea form
 */
function modifyForm(id){
	var form = '<div class="input-group">';
		form += '<textarea class="form-control bg-light border-0 small" name="newContent" onkeyup="resize(this)" maxlength="8000" style="height: 70px;">';
		form += $.trim($(id).text()) ;
		form += '</textarea>';
		form += '<div class="input-group-append">';
		form += '<button class="btn btn-info" type="submit">Modify</button>';
		form += '</div>';
		form += '</div>';
		
	return form;
}

/*
 * 댓글(Comments) 수정 폼
 */
function contentModefyForm(num){
	
	var id = "#" + num + "content";
	
	var content = "";
		content += '<form action="community/modify?nm=' + num +'" method="post">';
		content += modifyForm(id);
		content += '</form>';

	$(id).html(content);
}

/*
 * 추천 기능
 */
function contentUp(num){
	var id = "#" + num + "upCnt";
	var upCnt = Number($(id).text());
	
	$.ajax({
		url : "community/up",
		type : "get",
		data : { content_nm : num},
		success : function(data){
			if(data){ $(id).text(upCnt + 1); }
		},
		error : function(e){
			console.log(e);
		}
	});
}

/*
 * 비추천 기능
 */
function contentDown(num){
	var id = "#" + num + "downCnt";
	var downCnt = Number($(id).text());
	
	$.ajax({
		url : "community/down",
		type : "get",
		data : { content_nm : num },
		success : function(data){
			if(data){ $(id).text(downCnt + 1); }
		},
		error : function(e){
			console.log(e);
		}
	});
}


/*
 * 댓글(Comment)의 대댓글(reply) list
 */
function viewReply(num){
	var re = "#reply" + num + " .reply-card";
	var email = $("#loginEmail").val();
	
	$.ajax({
		url : "reply/"+ num,
		type : "get",
		success : function(data){
			var context = "";
			
			$.each(data, function(){
				context += "<div class='card'>";
				context += "<div class='card-header d-flex bd-highlight flex-row align-items-center'>";
				context += "<span class='h6 m-0 font-weight-bold text-primary flex-grow-1 bd-highlight'>" + this.replyer + "</span>";
				context += "<span class='bd-highlight'>" + this.reply_indate + "</span>";
				
				// reply 작성자와 로그인 정보가 일치하는 경우
				if(email == this.replyer){
					context += '<div class="dropdown no-arrow bd-highlight">';
					context += '<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">';
					context += '<i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i></a>';
					context += '<ul class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">';
					context += '<li><a class="dropdown-item" href="javascript:replymodifyForm(' + num + ',' + this.reply_nm + ');">modify</a></li>';
					context += '<li><a class="dropdown-item" href="javascript:replyDelete(' + num + ',' + this.reply_nm + ');">remove</a></li>';
					context += '</ul>';
					context += '</div>';
				}
								
				context += "</div>";
				context += "<div id='" + this.reply_nm + "reply' class='card-body'>"
				context += "<span>" + this.reply + "</span>";
				context += "</div>";
				context += "</div>";
			});
						
			$(re).html(context);
			
		},
		error : function(e){ console.log(e); }
	});
	
}

/*
 * reply 등록
 */

// 현재 댓글의 reply 갯수 추출
function getReplyCount(num){
	var cnt_str = $("#" + num + "reply_cnt").text();
	var cnt = cnt_str.substring(cnt_str.indexOf("(") + 1, cnt_str.indexOf(")"));
	
	return Number(cnt);
}

// reply 등록
function clkInsertReplyBtn(num){
	var reply = $("#insertReply" + num).serialize();
	var cnt = getReplyCount(num);
	
	$.ajax({
		url : "reply/"+ num,
		type : "post",
		data : reply,
		success : function(data){
			$("#insertReply" + num)[0].reset();	// textarea reset
			$("#" + num + "reply_cnt").text("reply(" + (cnt + 1)  + ")");
			viewReply(num);
		},
		error : function(e){ console.log(e); }
	});
}

// reply 수정
function replymodifyForm(num, reply_nm){
	var id = "#" + reply_nm + "reply";
	
	var content = "";
		content += '<form id="replyModifyForm" method="post">';
		content += modifyForm(id);
		content += '</form>';

	$(id).html(content);
	
	$("#replyModifyForm").on("submit", function(e){
		e.preventDefault();
		
		var newReply = $(this).serialize();
		
		newReply = newReply.substring(newReply.indexOf("=") + 1);
			
		$.ajax({
			url: "reply/"+ reply_nm,
			type: "patch",
			data: decodeURI(newReply),
			success: function(data){
				viewReply(num);
			},
			error: function(e) { consoel.log(e); }
		});
		
	});
}


// reply 삭제
function replyDelete(num, reply_nm){
	var cnt = getReplyCount(num);
	
	$.ajax({
		url: "reply/"+ reply_nm,
		type: "delete",
		success: function(data){
			$("#" + num + "reply_cnt").text("reply(" + (cnt - 1)  + ")");
			viewReply(num);
		},
		error: function(e){ consoel.log(e); }
	});
}
