/**
 * 
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
 
 
var backToTop = () => {
	// Scroll & button show / hide
	window.addEventListener('scroll', () => {
		if(document.querySelector('html').scrollTop > 100){
			document.getElementById('go-top').style.display = "block";
		} else {
			document.getElementById('go-top').style.display = "none";
		}
	});
	
	// back to top
	document.getElementById('go-top').addEventListener('click', () => {
		window.scrollTo({
			top: 0,
			left: 0,
			behavior: 'smooth'
		});
	})
};

backToTop();
