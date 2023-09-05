package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;

@Repository // 스프링에 DAO로 인식하게 해줌
public class MemberDAOImpl implements MemberDao {

	@Autowired //자동의존성 주입(DI설정)
	private SqlSession sqlSession; //mybatis에서 쿼리문을 수행할 sqlSession 을 생성

	@Override
	public void insertMember(MemberVO m) {
		
		this.sqlSession.insert("mem_in",m);
		/* mybatis 에서 insert()메서드는 레코드를 시킨다.
		 * mem_in은 member_test.xml 매퍼태그에서 설정할 유일 아이디명이다.
		 * 
		 */
	}//회원저장
	
	
}
