package net.daum.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class DataSourceTest {
	
	@Inject //자동 의존성 주입 => DI:Dependency Injection 설정 ; 
			//				참조변수에 객체주소를 주입해 실제 사용가능하게 함
	private DataSource ds;
	
	@Test
	public void testCon() throws Exception{
		try(Connection con = ds.getConnection()) {
			System.out.println(con);
		}catch(Exception e) {e.printStackTrace();}
	}

}
