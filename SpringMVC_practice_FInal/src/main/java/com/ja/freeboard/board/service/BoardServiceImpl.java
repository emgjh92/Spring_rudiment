package com.ja.freeboard.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.freeboard.mapper.BoardSQLMapper;
import com.ja.freeboard.mapper.MemberSQLMapper;
import com.ja.freeboard.vo.BoardVo;
import com.ja.freeboard.vo.MemberVo;

@Service
public class BoardServiceImpl {
	
	@Autowired
	private BoardSQLMapper boardSQLMapper;
	
	@Autowired
	private MemberSQLMapper memberSQLMapper;
	
	public void writeContent(BoardVo boardVo) {
		boardSQLMapper.insert(boardVo);
	}
	
	//---------------게시물 갯수 세기 (검색된 결과물 갯수 세기) -------------------
	public int getBoardDataCount(String search_word) {
		
		if(search_word==null) {
			return boardSQLMapper.selectAllCount();
		}else {
			return boardSQLMapper.selectByTitleCount(search_word);
		}
		
	}
	//-------------------------------------------------------------
	

	public List<Map<String, Object>> getBoardList(String search_word, int currPage) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		List<BoardVo> boardList = null;
		//-----------검색기능 추가, 페이징 추가-------------------
		if(search_word == null) {
			boardList = boardSQLMapper.selectAll(currPage);
		}else {
			boardList = boardSQLMapper.selectByTitle(search_word, currPage);

		}
		//-------------------------------------------------

		
		for(BoardVo boardVo : boardList) {
			
			MemberVo memberVo = 
					memberSQLMapper.selectByNo(boardVo.getMember_no());
			
			Map<String,Object> map = new HashMap<String, Object>();
			
			map.put("memberVo",memberVo);
			map.put("boardVo",boardVo);
			
			list.add(map);
		}
		
		return list;
	}
	
	
	public Map<String,Object> getBoard(int board_no){
		Map<String,Object> map = new HashMap<String,Object>();
		
		boardSQLMapper.updateReadCount(board_no); //조회수 증가
		
		BoardVo boardVo = boardSQLMapper.selectByNo(board_no);
		MemberVo memberVo = memberSQLMapper.selectByNo(boardVo.getMember_no());
		
		map.put("memberVo",memberVo);
		map.put("boardVo",boardVo);

		return map;
	}
	
	public void deleteContent(int board_no) {
		
		boardSQLMapper.deleteByNo(board_no);
	} //글 삭제 
	
	public void updateContent(BoardVo boardVo) {
		
		boardSQLMapper.update(boardVo);
	} //글 수정
	
}
