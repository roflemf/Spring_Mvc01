package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.ReplyDAO;
import net.daum.vo.ReplyVO;

@Service//->스프링에 서비스라는 것 인식시킴
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO replyDao;

	@Override
	public void addReply(ReplyVO vo) {
		this.replyDao.addReply(vo);
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.replyDao.listReply(bno);
	}

	@Override
	public void editReply(ReplyVO vo) {
		this.replyDao.editReply(vo);
	}

	@Override
	public void deleteReply(int rno) {
		this.replyDao.deleteReply(rno);
	}

}
