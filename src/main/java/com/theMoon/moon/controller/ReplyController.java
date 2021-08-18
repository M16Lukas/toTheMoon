package com.theMoon.moon.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theMoon.moon.service.ReplyService;
import com.theMoon.moon.vo.Reply;

@RestController
@RequestMapping("/quote/{symbol}/reply")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	// reply 출력
	@GetMapping("/{content_nm}")
	private ArrayList<Reply> getReply(@PathVariable String symbol, @PathVariable int content_nm) {
		return service.getReply(content_nm);
	}

	// reply 등록
	@PostMapping("/{content_nm}")
	private boolean insertReply(@PathVariable String symbol, @PathVariable int content_nm, String reply) {
		return service.insertReply(content_nm, reply);
	}
	
	// reply 수정
	@PatchMapping("/{reply_nm}")
	private int modifyReply(@PathVariable int reply_nm, @RequestBody String newReply) {
		return service.modifyReply(reply_nm, newReply);
	}
	
	// reply 삭제
	@DeleteMapping("/{reply_nm}")
	private int deleteReply(@PathVariable int reply_nm) {
		return service.deleteReply(reply_nm);
	}
}
