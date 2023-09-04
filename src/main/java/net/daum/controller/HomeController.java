package net.daum.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 스프링에서 @Controller 애너테이션을 등록하면 웹상에서 작동하는 컨트롤러 클래스로 인식
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * @RequestMapping 애노테이션에 의해서 method = RequestMethod.GET 방식에 의해 
	 * GET으로 접근하는 매핑 주소를 처리.
	 * value="/" /루트 경로 매핑주소 등록
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		//날짜 포멧 객체 생성
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		//serverTime 문자열 키이름에 날짜 포맷한 값을 저장
		
		return "home"; //뷰리졸브(뷰페이지)경로 /WEB-INF/views/home.jsp 
	}
	
}
