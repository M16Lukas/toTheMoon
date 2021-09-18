package com.theMoon.moon.service;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.theMoon.moon.dao.MemberDAO;
import com.theMoon.moon.util.EmailServiceImpl;
import com.theMoon.moon.vo.EmailDTO;
import com.theMoon.moon.vo.Member;

@Service
public class MemberService {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * 세션에 저장된 모든 정보 삭제
	 */
	private void removeSessionAttr() {
		session.removeAttribute("loginFirstName");
		session.removeAttribute("loginLastName");
		session.removeAttribute("loginEmail");
		session.removeAttribute("loginByLocalAccount");
	}
	
	/**
	 * Register Member
	 * 
	 * @param member
	 * @return
	 */
	public boolean registerMember(Member member) {
		String encodedPassword = passwordEncoder.encode(member.getPw());
		member.setPw(encodedPassword);
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
		
		Member loginUser = dao.login(member.getEmail());

		if (loginUser != null) {
			// 비밀번호 일치 여부 확인
			boolean isRegisterd = passwordEncoder.matches(member.getPw(), loginUser.getPw());
			
			// 일치 하지 않을 경우
			if(!isRegisterd) { return isValid; }
			
			session.setAttribute("loginFirstName", loginUser.getFirstName());
			session.setAttribute("loginLastName", loginUser.getLastName());
			session.setAttribute("loginEmail", loginUser.getEmail());
			session.setAttribute("loginByLocalAccount", true);
			
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
		removeSessionAttr();
		return "redirect:" + referer;
	}
	
	
	/**
	 * forgot-Password
	 */
	public boolean forgotPassword(String inputEmail) {
		boolean isVaild = false;
		
		// 입력한 이메일로 등록된 계정이 있는지 확인
		int isRegisteredEmail = dao.findUserByEmail(inputEmail);
		
		// 등록된 계정이 있는 경우
		if (isRegisteredEmail == 1) {
			// 임의 비밀번호 생성 
			String randomPassword = generatePassword(10);
			
			// 임의 비밀번호 암호화
			String encodedRandomPassword = passwordEncoder.encode(randomPassword);
			
			// 임의 비밀번호 DB에 저장
			Member member = new Member();
			member.setEmail(inputEmail);
			member.setPw(encodedRandomPassword);
			int cnt = dao.updatePassword(member);
			
			if(cnt > 0) {
				// send reset Password by Email
				emailService.sendMail(new EmailDTO(inputEmail, randomPassword));
				isVaild = true;
			}
		} 
		
		return isVaild;
	}
	
	/**
	 * 비밀번호 랜덤 생성기
	 * 
	 * @param length
	 * @return
	 */
	private String generatePassword(int length) {
		final SecureRandom RANDOM = new SecureRandom();
	    final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        
        return new String(returnValue);
    }
	
	
	/**
	 * 
	 * 비밀번호 수정
	 * 
	 * @param updatePassword
	 * @return
	 */
	public boolean updatePassword(String updatePassword) {
		boolean isUpdated = false;
		String loginedEmail = (String)session.getAttribute("loginEmail"); 
		String encodedUpdatePassword = passwordEncoder.encode(updatePassword);
		
		Member member = new Member();
		member.setEmail(loginedEmail);
		member.setPw(encodedUpdatePassword);
		
		if (dao.updatePassword(member) > 0) {
			removeSessionAttr();
			isUpdated = true;
		}
		
		return isUpdated;
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
			session.setAttribute("loginByLocalAccount", false);
			
			// set isValid : true
			isValid = true;
		} 
		
		return isValid;
	}
}
