package com.theMoon.moon.service;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.theMoon.moon.dao.MemberDAO;
import com.theMoon.moon.vo.Member;

@Service
public class MemberService {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberDAO dao;
	
	
	/**
	 * Register Member
	 * 
	 * @param member
	 * @return
	 */
	public boolean registerMember(Member member) {
		return dao.registerMember(member) > 0 ? true : false;
	}
	
	/**
	 * Login
	 * 
	 * @param member
	 * @return
	 */
	public boolean login(Member member) {
		boolean isValid = false;
		Member loginUser = dao.login(member);
		
		if (loginUser != null) {
			session.setAttribute("loginFirstName", loginUser.getFirstName());
			session.setAttribute("loginLastName", loginUser.getLastName());
			session.setAttribute("loginEmail", loginUser.getEmail());
			
			isValid = true;
		}
		
		return isValid;
	}
	
	
	/**
	 * Logout
	 * 
	 * @return
	 */
	public String logout(String referer) {
		session.removeAttribute("loginFirstName");
		session.removeAttribute("loginLastName");
		session.removeAttribute("loginEmail");
		return "redirect:" + referer;
	}
	
	
	/**
	 * google login
	 */
	public boolean googleTokenSignIn(String idtoken) throws GeneralSecurityException, IOException{
		boolean isValid = false;
		
		final String CLIENT_ID = "891963316360-vkp34ieqktbbhba0le9i4unkggkv50nn.apps.googleusercontent.com";
		
		HttpTransport transport = Utils.getDefaultTransport();
		JsonFactory jsonFactory = Utils.getDefaultJsonFactory();
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
											// Specify the CLIENT_ID of the app that accesses the backend:
											.setAudience(Collections.singletonList(CLIENT_ID))
											.build();
		
		// (Receive idTokenString by HTTPS POST)
		GoogleIdToken idToken = verifier.verify(idtoken);
				
		if (idToken != null) {
			Payload payload = idToken.getPayload();
			
			// get profile information from payload
			String email = payload.getEmail();
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			
			// store profile information in Session
			session.setAttribute("loginEmail", email);
			session.setAttribute("loginFirstName", givenName);
			session.setAttribute("loginLastName", familyName);
						
			// set isValid : true
			isValid = true;
		} 
		
		return isValid;
	}
}
