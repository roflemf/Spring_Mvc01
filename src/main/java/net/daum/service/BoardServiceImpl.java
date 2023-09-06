package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.BoardDAO;

@Service //-> 스프링에 서비스라는 것 인식시킴
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;
}
