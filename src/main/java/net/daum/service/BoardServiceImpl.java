package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.BoardDAO;
import net.daum.vo.BoardVO;

@Service //-> 스프링에 서비스라는 것 인식시킴
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;

	@Override
	public void insertBoard(BoardVO b) {
		// TODO Auto-generated method stub
		boardDao.insertBoard(b);
	}

	@Override
	public int getTotalCount() {
		
		return this.boardDao.getTotalCount();//this.생략가능
	}
	
	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		
		return this.boardDao.getBoardList(b);//this.생략가능
	}
	
	
	
}
