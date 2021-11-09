/**
 * 
 */

var sctx = document.getElementById("stockChartContainer");
var ctx = document.getElementById("chartContainer");
var dataPoints = [];
var dataPoints1 = [], dataPoints2 = [], dataPoints3 = [];

$(function(){
	
	var symbol = $("#chartSymbol").val();
	var pathname = $(location).attr('pathname');
	
	// index Page
	if(pathname.search("chart") == -1){
		indexPageChart(symbol);
	} 
	// Chart Page
	else {
		chartPageChart();
	}
});

/*
 * CASE1 : Index Page 
 */
function indexPageChart(symbol) {
	$.ajax({
		url: symbol + "/chartDate",
		type: "get",
		dataType: "json",
		success: function(data) {
			$.each(data, function() {
				dataPoints.push({ x: new Date(this.date), y: this.close });
			});
			$("#indexPageStockChartSpinner").remove();
			indexChart.render();
		},
		error: function(e) { console.log(e); }
	});
}

var indexChart = new CanvasJS.StockChart(sctx, {
 	theme: "light2",
    colorSet: "colorSet1",
    exportEnabled: false,
    charts: [{
      axisX: {
        crosshair: {
          enabled: true
        }
      },
      data: [{
        type: "area",
        yValueFormatString: "##0.00",
        dataPoints : dataPoints
      }]
    }],
    rangeSelector: { enabled: false },
    navigator: { enabled: false }
  });
	
	
/*
 * CASE2 : Chart Page
 */	
function chartPageChart() {
	$.ajax({
		url: "chartDate",
		type: "get",
		data : {period : "5Y"},
		dataType: "json",
		success: function(data) {
			$.each(data, function() {
				dataPoints1.push({ x: new Date(this.date), y: [this.open, this.high, this.low, this.close] });
				dataPoints2.push({ x: new Date(this.date), y: this.volume });
				dataPoints3.push({ x: new Date(this.date), y: this.close });
			});
			$("#chartPageStockChartSpinner").remove();
			stockChart.render();
		},
		error: function(e) { console.log(e); }
	});	
}


var stockChart = new CanvasJS.StockChart(ctx, {
	theme: "light2",
	animationEnabled: true,
    exportEnabled: false,
    charts: [{
		// Chart Part 
	      toolTip: {
	        shared: true
	      },
	      axisX: {
	        lineThickness: 5,
	        tickLength: 0,
	        labelFormatter: function(e) {
	          return "";
	        }
	      },
	      data: [{
	        showInLegend: false,
	        name: "Stock Price (in USD)",
	        yValueFormatString: "##0.00",
	        type: "candlestick",
	        dataPoints : dataPoints1
	      }]
	    },
	    // Volume Part
	    {
	      height: 100,
	      toolTip: {
	        shared: true
	      },
	      axisY: {
	        labelFormatter: addSymbols
	      },
	      data: [{
	        showInLegend: false,
	        name: "Volume",
	        yValueFormatString: "#,###.##",
	        dataPoints : dataPoints2
	      }]
	}],
	navigator: {
      data: [{
        dataPoints: dataPoints3
      }],
       slider: {
        minimum: new Date().setDate(new Date().getDate() - 365),
        maximum: new Date()
      }
    }
});

 /*
  * 単位設定
  */
function addSymbols(e){
    var suffixes = ["", "K", "M", "B"];
    var order = Math.max(Math.floor(Math.log(e.value) / Math.log(1000)), 0);
    if(order > suffixes.length - 1)
      order = suffixes.length - 1;
    var suffix = suffixes[order];
    return CanvasJS.formatNumber(e.value / Math.pow(1000, order)) + suffix;
}

