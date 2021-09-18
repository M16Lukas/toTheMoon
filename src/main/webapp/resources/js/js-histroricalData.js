/**
 * Set datepicker
 */
 $(function(){
	
	// default setting
	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd',
		changeMonth: true,
      	changeYear: true,
      	showOtherMonths: true,
	});
	
	// from
	var from = $("#period1").datepicker({
		setDate: '-1Y',
		maxDate: 'today'
	})
	.on("change", function(){
		to.datepicker( "option", "minDate", getDate( this ) );
		$("#fromDate").text($("#period1").val());
	}),
	
	// to
	to = $("#period2").datepicker({
		setDate: 'today',
      	maxDate: 'today'
	})
	.on("change", function(){
		from.datepicker( "option", "maxDate", getDate( this ) );
		$("#toDate").text($("#period2").val());
	});
	
	function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( 'yy-mm-dd', element.value );
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
});

/*
 * Request Prev/Next Page
 */
 function historyPaging(p){
	var countPerPage = $("#countPerPage").val();
	var frequency 	 = $("#frequency").val();
	var period1 	 = $("#fromDate").text();
	var period2 	 = $("#toDate").text();
	
	
	location.href = "history?period1=" + period1 + "&period2=" + period2 + "&countPerPage=" + countPerPage + "&frequency=" + frequency + "&p=" + p;
}

/*
 * historical Quote download (format : Excel)
 */
function historyDownload(){
	var frequency = $("#frequency").val();
	var period1 = $("#fromDate").text();
	var period2 = $("#toDate").text();
	
	$.ajax({
		url:"history/download",
		type: "get",
		data : {
			period1 : period1,
			period2 : period2,
			freq : frequency
		},
		success : function(data) {
			window.location = "history/download?period1=" + period1 + "&period2=" + period2 + "&freq=" + frequency;
		},
		error : function(e) { console.log(e); }
		
	});	
}
