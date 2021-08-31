package com.theMoon.moon.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
	private int member_num;
	private String firstName;
	private String lastName;
	private String email;	// primary key
	private String pw;
	private String indate;
}
