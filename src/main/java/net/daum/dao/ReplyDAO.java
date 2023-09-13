package net.daum.dao;

import java.util.List;

import net.daum.vo.ReplyVO;

public interface ReplyDAO {

	void addReply(ReplyVO vo);
	List<ReplyVO> listReply(int bno);
	void editReply(ReplyVO vo);
	void deleteReply(int rno);
	int getBno(int rno);
	

}
