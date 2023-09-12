package net.daum.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void updatePoint(String sender, int point) { //복수개 파라미터 전달시 MAP쓰기
		Map<String, Object> pm = new HashMap<>();
		pm.put("sender", sender); //sender 키이름에 보낸 사람 저장
		pm.put("point", point); //point.xml에서는 키이름을 참조해서 값을 가져온다.
		
		this.sqlSession.update("pointUp", pm); //pointUp은 point.xml에서 설정할 유일 아이디명
	}//메세지를 보낸사람에게 10점 업데이트
	
}
