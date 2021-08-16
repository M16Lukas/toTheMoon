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

function toastWarning(message){
	toastr.options = {
		"closeButton": false,
		"debug": false,
		"newestOnTop": false,
		"progressBar": false,
		"positionClass": "toast-bottom-left",
		"preventDuplicates": false,
		"onclick": null,
		"showDuration": "300",
		"hideDuration": "1000",
		"timeOut": "5000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	}
    toastr.warning("please input " + message, {timeOut: 5000});
}

function toastError(message){
	toastr.options = {
		"closeButton": false,
		"debug": false,
		"newestOnTop": false,
		"progressBar": false,
		"positionClass": "toast-top-full-width",
		"preventDuplicates": false,
		"onclick": null,
		"showDuration": "300",
		"hideDuration": "1000",
		"timeOut": "5000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	}
	toastr.error(message);
}

function register(){
	var form = document.getElementById("registerAccount");

	if(form.firstName.value == null || form.firstName.value == "" || form.firstName.value == 'undefined'){
		form.firstName.focus();
		toastWarning("first Name");
		return false;
	}
	
	if(form.lastName.value == null || form.lastName.value == "" || form.lastName.value == 'undefined'){
		form.lastName.focus();
		toastWarning("last Name");
		return false;
	}
	
	if(form.inputEmail.value == null || form.inputEmail.value == "" || form.inputEmail.value == 'undefined'){
		form.inputEmail.focus();
		toastWarning("email address");
		return false;
	} else if(!CheckEmail(form.inputEmail.value)){
		form.inputEmail.focus();
		toastWarning("correct email address");
		return false;			
	}
	
	if(form.inputPassword.value == null || form.inputPassword.value == "" || form.inputPassword.value == 'undefined'){
		form.inputPassword.focus();
		toastWarning("password");
		return false;
	}

	if(form.inputPassword.value != form.repeatPassword.value){
		form.repeatPassword.focus();
		toastWarning("correct password");
		return false;
	}

	
	var formData = $('#registerAccount').serialize();
	
	$.ajax({
		url : "/member/register",
		type: "post",
		cache : false,
		data : formData,
		success : function(data) {
			if(data) {
				window.location.replace("/member/login");
			} else {
				$("#registerAccount").each(function(){
					this.reset();
				});
				toastError("Email is Already Registered");
			}
		},
		error : function(e){
			console.log(e);
		}
	});
}

function login(){
	
	var form = document.getElementById("loginForm");
	
	if(form.inputEmail.value == null || form.inputEmail.value == "" || form.inputEmail.value == 'undefined'){
		inputEmail.focus();
		toastWarning("email address");
		return false;
	} else if(!CheckEmail(form.inputEmail.value)){
		form.inputEmail.focus();
		toastWarning("correct email address");
		return false;			
	}
	
	if(form.inputPassword.value == null || form.inputPassword.value == "" || form.inputPassword.value == 'undefined'){
		inputPassword.focus();
		toastWarning("password");
		return false;
	}
	
	var formData = $('#loginForm').serialize();
	
	$.ajax({
		url : "/member/login",
		type: "post",
		cache : false,
		data : formData,
		success : function(data) {
			console.log(document.referrer);
			if(data) {
				window.location.replace(document.referrer);
			} else {
				$("#loginForm").each(function(){
					this.reset();
				});
				toastError("Sorry, we don't recognize this account.");
			}
		},
		error : function(e){
			console.log(e);
		}
	});
}

