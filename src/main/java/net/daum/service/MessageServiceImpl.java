package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.MessageDAO;
import net.daum.dao.PointDAO;
import net.daum.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired 
	private PointDAO pointDAO;

	//메세지 insert + 메세지를 보낸 사람에게 포인트 점수 10점 업데이트 => 스프링 AOP 를 통한 트랜젝션 적용 대상 (데이터 불일치제거)
	@Transactional//트랜잭션 적용  => 데이터 불일치 현상 제거
	@Override
	public void insertMessage(MessageVO vo) {
		this.messageDAO.insertMessage(vo); //메세지 추가
		this.pointDAO.updatePoint(vo.getSender(),10); //메시지를 보낸 사람에게 포인트 점수 10점 UP
		
	}
}
