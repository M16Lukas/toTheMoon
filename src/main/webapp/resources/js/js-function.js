/**
 * 
 */

 
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
