/*!
* Start Bootstrap - Landing Page v6.0.2 (https://startbootstrap.com/theme/landing-page)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-landing-page/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

/**
 * List of Error Message
 */
const emailDuplicationErrorMsg = "Email is Already Registered";
const emailInvalidErrorMsg = "Sorry, we don't recognize this account.";
const passwordUpadteErrorMsg = "Failed to update password";

/*
 * Verify that the email you entered matches the email format.
 */
function CheckEmail(str){
	var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
    return reg_email.test(str);
}       

/*
 * Warning Message format
 */
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

/*
 * Error Message format
 */
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

/*
 * Check Validation & Register new account
 */
function register(){
	var form = document.getElementById("registerAccount");

	/*
	 * Check Validation of firstName
	 */
	if(form.firstName.value == null || form.firstName.value == "" || form.firstName.value == 'undefined'){
		form.firstName.focus();
		toastWarning("first Name");
		return false;
	} else if(form.firstName.value.length > 10){
		alert("Maximum firstName Size is 10 words");
		form.inputEmail.focus();
		return false;
	}
	
	/*
	 * Check Validation of lastName
	 */
	if(form.lastName.value == null || form.lastName.value == "" || form.lastName.value == 'undefined'){
		form.lastName.focus();
		toastWarning("last Name");
		return false;
	} else if(form.lastName.value.length > 10){
		alert("Maximum lastName Size is 10 words");
		form.inputEmail.focus();
		return false;
	}
	
	/*
	 * Check Validation of email
	 */
	if(form.inputEmail.value == null || form.inputEmail.value == "" || form.inputEmail.value == 'undefined'){
		form.inputEmail.focus();
		toastWarning("email address");
		return false;
	} else if(!CheckEmail(form.inputEmail.value)){
		form.inputEmail.focus();
		toastWarning("correct email address");
		return false;			
	}
	
	if(form.inputEmail.value.length > 30){
		alert("Maximum Email Size is 30 words");
		form.inputEmail.focus();
		return false;
	}
	
	/*
	 * Check Validation of password
	 */
	if(form.inputPassword.value == null || form.inputPassword.value == "" || form.inputPassword.value == 'undefined'){
		form.inputPassword.focus();
		toastWarning("password");
		return false;
	} else if(form.inputPassword.value.length < 10 || form.inputPassword.value.length > 30){
		alert("The Password length must be between 10 to 30 words.");
		form.inputPassword.focus();
		return false;
	}

	if(form.inputPassword.value != form.repeatPassword.value){
		form.repeatPassword.focus();
		toastWarning("correct password");
		return false;
	}

	
	/*
	 * Register new account
	 */
	var formData = $('#registerAccount').serialize();
	
	$.ajax({
		url : "/member/register",
		type: "post",
		cache : false,
		data : formData,
		success : function(data) {
			if(data) {
				window.location.replace("/");
			} else {
				$("#registerAccount").each(function(){
					this.reset();
				});
				toastError(emailDuplicationErrorMsg);
			}
		},
		error : function(e){
			console.log(e);
		}
	});
}

/*
 * Check Validation & Request Login
 */
function login(){
	
	/*
	 * Check Validation of email
	 */
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
	
	/*
	 * Check Validation of password
	 */
	if(form.inputPassword.value == null || form.inputPassword.value == "" || form.inputPassword.value == 'undefined'){
		inputPassword.focus();
		toastWarning("password");
		return false;
	}
	
	/*
	 * Request Login
	 */
	var formData = $('#loginForm').serialize();
	
	$.ajax({
		url : "/member/loginProcess",
		type: "post",
		cache : false,
		data : formData,
		success : function(data) {
			if(data) {
				window.location.replace(document.referrer);
			} else {
				$("#loginForm").each(function(){
					this.reset();
				});
				toastError(emailInvalidErrorMsg);
			}
		},
		error : function(e){
			console.log(e);
		}
	});
}

/*
 * Request Logout
 */
function logOut(){
	
	// If you log in to Google Account
	if(gapi.auth2 != undefined){
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function () {
            console.log('User signed out.');
		});
		auth2.disconnect();
	}
	
	location.href = "/member/logout";
}

/*
 * Reset Password
 */
function forgotPassword(){
	
	var inputEmail = $("#findPwForm").serialize();

	$.ajax({
		url:"/member/forgot-password",
		type: "post",
		data: inputEmail,
		success: function(data){
			if(data){
				window.location.replace("/"); 
			} else {
				$("#findPwForm").each(function(){
					this.reset();
				});
				$("#findPwForm input[name='inputEmail']").focus();
				toastError(emailInvalidErrorMsg);
			}
		},
		error: function(e){ console.log(e); }
	});
	
}

/*
 * Check Validation & Register new Password
 */
function updatePassword(){
	
	var formData = $("#updatePwForm").serializeArray();
	var dataObj = {};
	
	$(formData).each(function(i, field){
		dataObj[field.name] = field.value;
	});
	
	/*
	 * Check Validation
	 */
	if(dataObj['newPassword'] == null || dataObj['newPassword'] == "" || dataObj['newPassword'] == 'undefined'){
		$("#updatePwForm input[name='newPassword']").focus();
		toastWarning("password");
		return;
	} else if(dataObj['newPassword'].length < 10 || dataObj['newPassword'].length > 30){
		alert("The Password length must be between 10 to 30 words.");
		$("#updatePwForm input[name='newPassword']").focus();
		return;
	}

	if(dataObj['newPassword'] != dataObj['repeatPassword']){
		$("#updatePwForm input[name='repeatPassword']").focus();
		toastWarning("correct password");
		return;
	}
	

	/*
	 * Register new Password 
	*/
	$.ajax({
		url:"/member/update-password",
		type: "patch",
		data: dataObj['newPassword'],
		success: function(data){
			if(data){
				window.location.replace(document.referrer);
			} else {
				$("#updatePwForm").each(function(){
					this.reset();
				});
				toastError(passwordUpadteErrorMsg);
			}
		}, 
		error: function(e){
			console.log(e);
		}
	});
}

/*
 * Google login function initialization 
 */
function onLoad() {
	gapi.load('auth2', function() {
		gapi.auth2.init();
	});
}

/*
 * Sign in with Google 
 */
function onSignIn(googleUser){
	var id_token = googleUser.getAuthResponse().id_token;
		
	$.ajax({
		url: '/member/google/auth',
		type: 'POST',
		data: 'idtoken=' + id_token,
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		},
		success: function(data) {
			if (data) {
				window.location.replace(document.referrer);
			} else {
				toastError("Sorry, we don't recognize this account.");
			}
		},
		error: function(e) { console.log(e); }
	});//ajax

}