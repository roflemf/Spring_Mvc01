package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.BoardDAO;
import net.daum.dao.ReplyDAO;
import net.daum.vo.ReplyVO;

@Service//->스프링에 서비스라는 것 인식시킴
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO replyDao;
	
	@Autowired
	private BoardDAO boardDao;
	
	//스프링의 AOP 를 통한 트렌젝션 적용대상 => 댓글이 추가되면 댓글 카운터 1증가
	@Transactional //트랜젝션 적용 => 데이터의 일관성 유지 (데이터 불일치 현상 제거)
	@Override
	public void addReply(ReplyVO vo) {
		this.replyDao.addReply(vo);//댓글 추가 insert
		this.boardDao.updateReplyCnt(vo.getBno(),1); 
		//새로운 댓글 하나가 추가되면 게시물 기준으로 댓글 개수 1증가 =>update 
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.replyDao.listReply(bno);
	}

	@Override
	public void editReply(ReplyVO vo) {
		this.replyDao.editReply(vo);
	}

	//댓글 삭제되면 댓글개수 1감소 => 스프링 AOP 통한 트랜잭션 적용
	@Override
	public void deleteReply(int rno) {
		int bno = this.replyDao.getBno(rno);//댓글이 삭제되기 전에 댓글 번호를 기준으로 게시판 번호 구함
		this.replyDao.deleteReply(rno);
		this.boardDao.updateReplyCnt(bno, -1);//댓글 하나 삭제되면 댓글갯수 1감소
	}
	
	

}
