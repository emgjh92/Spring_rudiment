package com.ja.freeboard.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import com.ja.freeboard.vo.BoardVo;

public interface BoardSQLMapper {

	public void insert(BoardVo boardVo);
	
	public BoardVo selectByNo(int no);
	//------------------총 갯수 및 검색한 결과 갯수----------------------------
	public int selectAllCount();

	public int selectByTitleCount(String title);
	//---------------------------------------------------------------

	public List<BoardVo> selectAll(int currPage);
	
	public List<BoardVo> selectByTitle(
				@Param("title") String title, 
				@Param("currPage") int currPage
				);

	public void deleteByNo(int no);
	
	public void update(BoardVo vo);
	
	public void updateReadCount(int no);
}
