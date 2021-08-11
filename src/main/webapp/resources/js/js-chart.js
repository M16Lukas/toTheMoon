/**
 * 
 */
 

var ctx = document.getElementById("chartContainer");
var dataPoints = [];

$(function(){
	
	var symbol = $("#chartSymbol").val();
	var pathname = $(location).attr('pathname');
	var url = "chartDate";	// default : Chart Site 
	
	// index Page 인 경우
	if(pathname.search("chart") == -1){
		url = symbol + "/chartDate";
	}
	
	$.ajax({
		url : url,
		type : "get",
		dataType : "json",
		success : function(data){
			$.each(data, function(){				
				dataPoints.push({ x: new Date(this.year, this.month, this.day), y: this.close });
				
				chart.render();
			});
		},
		error : function(e){ console.log(e); }
	});
});

var chart = new CanvasJS.Chart(ctx, {
	animationEnabled: false,
	zoomEnabled: true,
	axisX: {
		valueFormatString: "DD MMM",
		crosshair: {
			enabled: true,
			snapToDataPoint: true
		}
	},
	axisY: {
		includeZero: false,
		valueFormatString: "##0.00",
		crosshair: {
			enabled: true,
			snapToDataPoint: true,
			labelFormatter: function(e) {
				return "$" + CanvasJS.formatNumber(e.value, "##0.00");
			}
		}
	},
	data: [{
		type: "area",
		xValueFormatString: "DD MMM",
		yValueFormatString: "$##0.00",
		dataPoints: dataPoints
	}]
});
