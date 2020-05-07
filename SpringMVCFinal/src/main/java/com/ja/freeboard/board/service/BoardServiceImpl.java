package com.ja.freeboard.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.freeboard.mapper.BoardSQLMapper;
import com.ja.freeboard.mapper.MemberSQLMapper;
import com.ja.freeboard.vo.BoardVo;
import com.ja.freeboard.vo.MemberVo;

import java.util.*;

@Service
public class BoardServiceImpl {
	
	@Autowired
	private BoardSQLMapper boardSQLMapper;
	
	@Autowired
	private MemberSQLMapper memberSQLMapper;
	
	public void writeContent(BoardVo boardVo) {
		boardSQLMapper.insert(boardVo);	
	}

	public List<Map<String, Object>> getBoardList(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		List<BoardVo> boardList = boardSQLMapper.selectAll();
		
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
}
