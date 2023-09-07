package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BoardVO;

@Repository //-> 스프링에 모델 DAO로 인식되게 함
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession; //mybatis 쿼리문 수행할 sqlSession 자동의존성 주입(DI)
	
	@Override
	public void insertBoard(BoardVO b) {
		this.sqlSession.insert("board_in",b);
		//this.은 생략 가능함. mybatis에서 insert() 메서드는 레코드를 저장한다.
		//board_in은 board.xml에서 설정할 유일 아이디명이다.
	}//스프링 MVC 게시판 저장

	@Override
	public int getTotalCount() {
		
		return sqlSession.selectOne("board_count");
		//mybatis에서 selectOne()메서드는 단 한개의 레코드값만 반환, 
		//board_count는 board.xml에서 설정할 유일한 아이디명
	}//총 레코드 개수(총 자료수)

	
	
	@Override
	public List<BoardVO> getBoardList(BoardVO b){
		return this.sqlSession.selectList("board_list", b);
		//mybatis에서 selectList()메서드는 하나 이상의 레코드를 검색해서 컬렉션List로 반환한다.
		//board_list는 board.xml에서 설정할 유일한 아이디명이다. 
	}
	
}