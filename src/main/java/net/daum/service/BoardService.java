package net.daum.service;

import java.util.List;

import net.daum.vo.BoardVO;

public interface BoardService {

	public void insertBoard(BoardVO b);
	public int getTotalCount();
	public List<BoardVO> getBoardList(BoardVO b);
	public BoardVO getBoardCont(int bno);
	public BoardVO getBoardCont2(int bno);
	public void editBoard(BoardVO eb);
	public void delBoard(int bno);

}
