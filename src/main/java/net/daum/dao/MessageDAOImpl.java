package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {
	
	@Autowired
	private SqlSession sqlSession; //my batis  쿼리문 수행 할  sqlSession 의존성(DI) 주입

	@Override
	public void insertMessage(MessageVO vo) {
		this.sqlSession.insert("message_in",vo); //message_in이 message.xml에서 설정할 유일 아이디명
						//insert()메서드가 레코드를 저장시킴
		
	}//메세지 추가
	
	

}
