package net.daum.service;

import java.util.List;

import net.daum.vo.BoardVO;

public interface BoardService {

	public void insertBoard(BoardVO b);
	public int getTotalCount();
	public List<BoardVO> getBoardList(BoardVO b);

}
