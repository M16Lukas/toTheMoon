/*!
* Start Bootstrap - Landing Page v6.0.2 (https://startbootstrap.com/theme/landing-page)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-landing-page/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
function CheckEmail(str){
	var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
    return reg_email.test(str);
}       

function register(){
	var form = document.getElementById("registerAccount");

	if(form.firstName.value == null || form.firstName.value == "" || form.firstName.value == 'undefined'){
		alert("This is required.");
		form.firstName.focus();
		return false;
	}
	
	if(form.lastName.value == null || form.lastName.value == "" || form.lastName.value == 'undefined'){
		alert("This is required.");
		form.lastName.focus();
		return false;
	}
	
	if(form.inputEmail.value == null || form.inputEmail.value == "" || form.inputEmail.value == 'undefined'){
		alert("This is required.");
		form.inputEmail.focus();
		return false;
	} else if(!CheckEmail(form.inputEmail.value)){
		alert("This is required.");
		form.inputEmail.focus();
		return false;			
	}
	
	if(form.inputPassword.value == null || form.inputPassword.value == "" || form.inputPassword.value == 'undefined'){
		alert("This is required.");
		form.inputPassword.focus();
		return false;
	}

	if(form.inputPassword.value != form.repeatPassword.value){
		alert("This is required.");
		form.repeatPassword.focus();
		return false;
	}

	form.submit();
}

function login(){
	var inputEmail = document.getElementById("inputEmail");
	var inputPassword = document.getElementById("inputPassword");
	
	if(inputEmail.value == null || inputEmail.value == "" || inputEmail.value == 'undefined'){
		inputEmail.focus();
		return false;
	}
	
	if(inputPassword.value == null || inputPassword.value == "" || inputPassword.value == 'undefined'){
		inputPassword.focus();
		return false;
	}
	
	return true;
}

