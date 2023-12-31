package net.daum.dao;

import java.util.List;

import net.daum.vo.BoardVO;

public interface BoardDAO {
	
	public void insertBoard(BoardVO b);
	public int getTotalCount();
	public List<BoardVO> getBoardList(BoardVO b);
	public BoardVO getBoardCont(int bno);
	public void updateHit(int bno);
	public void editBoard(BoardVO eb);
	public void delBoard(int bno);
	public void updateReplyCnt(int bno, int count);
}
