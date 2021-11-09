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
	 * セッションに貯蔵されたデータの削除
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
		//　パスワードの暗号化
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
			// パスワード確認
			boolean isRegisterd = passwordEncoder.matches(member.getPw(), loginUser.getPw());
			
			// パスワードが一致していない場合
			if(!isRegisterd) { return isValid; }
			
			session.setAttribute("loginFirstName", loginUser.getFirstName());	//　名
			session.setAttribute("loginLastName", loginUser.getLastName());		//　姓　
			session.setAttribute("loginEmail", loginUser.getEmail());			//　e-mail
			session.setAttribute("loginByLocalAccount", true);					//　会員登録したアカウントでログイン
			
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
		//セッション情報削除
		removeSessionAttr();
		return "redirect:" + referer;
	}
	
	
	/**
	 * forgot-Password
	 */
	public boolean forgotPassword(String inputEmail) {
		boolean isVaild = false;
		
		// 入力したe-mailがDBに登録されているか確認
		int isRegisteredEmail = dao.findUserByEmail(inputEmail);
		
		// 登録されている場合
		if (isRegisteredEmail == 1) {
			// ランダムパスワード生成
			String randomPassword = generatePassword(10);
			
			// ランダムパスワード暗号化
			String encodedRandomPassword = passwordEncoder.encode(randomPassword);
			
			// ランダムパスワードをDBに貯蔵
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
	 * ランダムパスワード生成
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
	 * パスワード変更
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
											// Specify the CLIENT_ID of the app that accesses the backend
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
			session.setAttribute("loginEmail", email);			//　e-mail
			session.setAttribute("loginFirstName", givenName);	//　名
			session.setAttribute("loginLastName", familyName);	//　姓　
			session.setAttribute("loginByLocalAccount", false);
			
			// set isValid : true
			isValid = true;
		} 
		
		return isValid;
	}
}
