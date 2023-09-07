package net.daum.dao;

import java.util.List;

import net.daum.vo.BoardVO;

public interface BoardDAO {
	
	public void insertBoard(BoardVO b);
	public int getTotalCount();
	public List<BoardVO> getBoardList(BoardVO b);
}
