package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.BoardDAO;
import net.daum.vo.BoardVO;

@Service //-> 스프링에 서비스라는 것 인식시킴
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;

	@Override
	public void insertBoard(BoardVO b) {
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

	//조회수 증가 + 내용보기 => 스프링의 AOP를 통한 트랜젝션 적용대상 (데이터 불일치 현상 제거)
	@Transactional(isolation = Isolation.READ_COMMITTED)
	//트랜잭션 분리(트랜잭션이 처리되는 중간에 외부 간섭 없앰, READ_COMMITTED; 커밋된(쿼리문성공적 반영된) 데이터에 대해 읽기허용
	@Override
	public BoardVO getBoardCont(int bno) {
		this.boardDao.updateHit(bno); //조회수 증가
		return boardDao.getBoardCont(bno);//번호에 해당하는 레코드 가져오기
	}

	@Override
	public BoardVO getBoardCont2(int bno) {
		return this.boardDao.getBoardCont(bno);
		
	}//조회수 증가 X, 내용보기만 처리

	@Override
	public void editBoard(BoardVO eb) {
		
		this.boardDao.editBoard(eb); 
	}

	@Override
	public void delBoard(int bno) {
		this.boardDao.delBoard(bno);
	}

	
	
	
	
}
