package com.ja.reference.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ja.reference.vo.BoardVo;

public interface BoardSQLMapper {
	
	@Select("SELECT * FROM FB_Board WHERE b_no = #{no}")
	public BoardVo selectByNo(int no);
	
	@Select("SELECT * FROM FB_Board")
	public ArrayList<BoardVo> selectAll();
	
	@Insert("INSERT INTO FB_Board VALUES(FB_Board.nextval,#{m_no},#{b_title},#{b_content},SYSDATE)")
	public void insert(BoardVo xxx);
	
}
