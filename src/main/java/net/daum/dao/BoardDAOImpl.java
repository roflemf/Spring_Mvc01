package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //-> 스프링에 모델 DAO로 인식되게 함
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession; //mybatis 쿼리문 수행할 sqlSession 자동의존성 주입(DI)
	
	
}
