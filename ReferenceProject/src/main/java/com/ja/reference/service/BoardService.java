package com.ja.reference.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.reference.mapper.BoardSQLMapper;
import com.ja.reference.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardSQLMapper boardSQLMapper;
	
	public BoardVo getContent(int b_no) {
		//DB연동...[MyBatis이용]
		return boardSQLMapper.selectByNo(b_no);
	
	}
}
