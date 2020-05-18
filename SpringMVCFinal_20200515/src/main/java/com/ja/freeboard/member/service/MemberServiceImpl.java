package com.ja.freeboard.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.freeboard.mapper.AuthSQLMapper;
import com.ja.freeboard.mapper.HobbySQLMapper;
import com.ja.freeboard.mapper.MemberSQLMapper;
import com.ja.freeboard.util.FBMessageDigest;
import com.ja.freeboard.vo.*;

import java.security.*;

@Service
public class MemberServiceImpl {
	
	@Autowired
	private MemberSQLMapper memberSQLMapper;
	
	@Autowired
	private HobbySQLMapper hobbySQLMapper;
	
	@Autowired
	private AuthSQLMapper authSQLMapper; 
	
	public void joinMember(MemberVo memberVo, int[] member_hobby, AuthVo authVo) {
		
		//----------------비밀번호 해싱(com.ja.freeboard.util 에 넣어둠)-----------
		String hashCode = 
		FBMessageDigest.digest(memberVo.getMember_pw());
		
		memberVo.setMember_pw(hashCode);
		//------------------------------------------------------------------
		
		
		/*
		//==================비밀번호 해싱==================
		try {
			
			String hashCode="";
			
			StringBuilder sb = new StringBuilder();
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.reset();
			messageDigest.update(memberVo.getMember_pw().getBytes());
			
			byte[] chars = messageDigest.digest();
			
			for(int i=0; i<chars.length; i++) {
				
				String tmp = Integer.toHexString(chars[i]&0xff); //비트연산자 사용
				
				if(tmp.length()==1) {
					sb.append("0");
				} //항상 똑같은 길이의 문자를 만들기 위해
				
				sb.append(tmp);
				}
			
			hashCode = sb.toString();
			
			memberVo.setMember_pw(hashCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//================================================
		 */
		
		
		//DB연동...
		int member_key = memberSQLMapper.createKey();
		memberVo.setMember_no(member_key);
		memberSQLMapper.insert(memberVo);
		
		//++++++++++++회원가입 인증 메일링 기능 추가++++++++++++++++++
		//첫단계 - 인증키 생성
		authVo.setMember_no(member_key);
		authSQLMapper.insert(authVo);
		//+++++++++++++++++++++++++++++++++++++++++++++++++

		
		if(member_hobby == null) {
			return;
		}
		
		for(int hobby : member_hobby) {
			
			hobbySQLMapper.insert(member_key, hobby);
		}
	}
	
	public MemberVo login(MemberVo memberVo) {
		/*
		//==================비밀번호 해싱==================
				try {
					
					String hashCode = "";
					
					StringBuilder sb = new StringBuilder();
					
					MessageDigest messageDigest 
						 = MessageDigest.getInstance("SHA-1"); 
					
					messageDigest.reset();
					messageDigest.update(memberVo.getMember_pw().getBytes());
					
					byte[] chars = messageDigest.digest();
					
					for(int i = 0 ; i < chars.length ; i++) {
						
						String tmp = Integer.toHexString(chars[i] & 0xff);
						
						if(tmp.length() == 1) {
							sb.append("0");
						}
						
						sb.append(tmp);
					}

					hashCode = sb.toString();
					
					memberVo.setMember_pw(hashCode);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
		//================================================
		
		 */
		//----------------비밀번호 해싱(com.ja.freeboard.util 에 넣어둠)-----------
		String hashCode = 
		FBMessageDigest.digest(memberVo.getMember_pw());
		
		memberVo.setMember_pw(hashCode);
		//------------------------------------------------------------------

		return memberSQLMapper.selectByIdAndPw(memberVo);
	}
	
	public void certification(String key) {
		
		authSQLMapper.update(key);
	}
	
	public boolean confirmId(String id) {
		if(memberSQLMapper.selectById(id) ==null) {
			return true; //사용가능
		}else {
			return false; //사용 불가능 (아이디 중복됨)
			}
	}
}