package net.daum.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.daum.dao.MemberDao;
import net.daum.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MemberDAOTest {
	
	@Autowired//자동 의존성 주입
	private MemberDao memberDao;
	
	@Test
	public void testInsertMember() throws Exception{
		MemberVO m = new MemberVO();
		
		m.setUserid("kkkkk");//회원 아이디 저장
		m.setUserpw("77777");//비번저장
		m.setUsername("홍길동");//회원이름
		m.setEmail("hong@gmail.com");//이메일 저장
		
		this.memberDao.insertMember(m); //회원 저장
		//this.는 생략가능
	}
	

}
