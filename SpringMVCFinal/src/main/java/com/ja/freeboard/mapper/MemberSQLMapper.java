package com.ja.freeboard.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ja.freeboard.vo.MemberVo;

public interface MemberSQLMapper {
	
	@Select("select FB_member_seq.nextval from DUAL")
	public int createKey(); 
	
	@Insert("INSERT INTO FB_Member VALUES(#{member_no},#{member_id},#{member_pw},#{member_nick},#{member_sex},SYSDATE)")
	public void insert(MemberVo vo);
	
	@Select("SELECT * FROM FB_Member WHERE member_id=#{member_id} AND member_pw=#{member_pw}")
	public MemberVo selectByIdAndPw(MemberVo xxx);
	
	@Select("SELECT * FROM FB_Member WHERE member_no=#{no}")
	public MemberVo selectByNo(int no);
	
	
}
