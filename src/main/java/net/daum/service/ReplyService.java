package net.daum.service;

import java.util.List;

import net.daum.vo.ReplyVO;

public interface ReplyService {

	void addReply(ReplyVO vo);
	List<ReplyVO> listReply(int bno);
	void editReply(ReplyVO vo);
	void deleteReply(int rno);

}
