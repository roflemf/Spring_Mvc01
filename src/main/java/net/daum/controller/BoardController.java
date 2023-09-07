package net.daum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.daum.service.BoardService;
import net.daum.vo.BoardVO;

@Controller //@Controller 애노테이션을 추가함으로써 웹에서 인식되는 스프링 컨트롤러가 된다.
@RequestMapping("/board/*") //경로 구분하기 위해 컨트롤러 자체 매핑주소 /board/* 등록
public class BoardController {//스프링 MVC 게시판 컨트롤러 
	
	@Autowired //자동 의존성 주입
	private BoardService boardService;
	
	//게시판 글쓰기 폼
	@RequestMapping(value = "/board_write", method=RequestMethod.GET)//get으로 접근하는 매핑주소 처리, 
																     //board_write 매핑주소 등록
	public void board_write(Model wm, HttpServletRequest request) {
		//리턴타입이 없는 void형이면 매핑주소가 뷰페이지 파일명이 된다.
		// 뷰리졸브 경로는 /WEB-INF/views/board/board_write.jsp
		
		int page = 1;
		if(request.getParameter("page")!=null) {//get으로 전달된 쪽번호가 있는 경우
			page=Integer.parseInt(request.getParameter("page"));
			//쪽번호인 페이지 번호를 정수 숫자로 변경해 저장
		}
		wm.addAttribute("page", page);
		//페이징에서 내가 본 쪽번호로 바로 이동하기 위한 책갈피 기능을 구현하기 위해
		//page키이름에 쪽번호를 저장해 전달
		
	}//board_write()
	
	//게시판 저장
	@RequestMapping(value="/board_write", method=RequestMethod.POST) 
	//동일 매핑주소 사용하면 메서드 방식으로 구분. post 방식으로 접근하는 매핑주소 처리
	public ModelAndView board_write(BoardVO b, RedirectAttributes rttr) {
	/* BoardVO b는 board_write.jsp의 네임 파라미터 이름과 BoardVO.java 의 변수명이 같으면 
	 * b객체에 글쓴이, 글제목, 글내용이 벌써 저장되어있다.
	 */
		this.boardService.insertBoard(b); //게시판 저장
		rttr.addFlashAttribute("result", "success");
		/*백엔드 서버단에서 다른 매핑주소로 이동시 result 키이름에 success 문자를 담아 전달
		 * 주소창에 노출 안되어 보안상 좋다
		 */
		
		return new ModelAndView("redirect:/board/board_list");
		/* ModelAndView 생성자 인자값에 올 수 있는 것)
		 *  1. 뷰페이지 경로
		 *  2. redirect:/매핑주소, 여기서는 매핑주소인 /board/board_list 게시판 목록보기 매핑주소로 이동
		 */
	}//board_write() =>post방식
	
	//게시판 목록
	@GetMapping("/board_list") //get으로 접근하는 매핑주소 처리 => board_list 매핑주소 등록
	public String board_list(Model listM, HttpServletRequest request, @ModelAttribute
			BoardVO b) {
		
		/*페이징 소스 시작*/
		int page =1; //현재 쪽 번호
		int limit =10; //한 페이지에 보여지는 목록 개수
		if(request.getParameter("page")!=null) {//get으로 전달된 쪽번호가 있는 경우
			page = Integer.parseInt(request.getParameter("page"));
			//쪽번호를 정수 숫자로 변경해 저장
		}
		b.setStartrow((page-1)*10+1);//시작 행번호
		b.setEndrow(b.getStartrow()+limit-1);//끝 행번호
		/*페이징 소스 끝*/
		
		int totalCount = this.boardService.getTotalCount(); //총레코드 개수
		List<BoardVO> blist = this.boardService.getBoardList(b); //게시물 목록
		
		/*페이징 연산*/
		int maxpage = (int)((double)totalCount/limit+0.95);//총페이지수
	       int startpage = (((int)((double)page/10+0.9))-1)*10+1;//현재 페이지에 보여질 시작페이지
	       int endpage = maxpage;//현재 페이지에 보여질 마지막 페이지
	        
	       if(endpage>startpage+10-1) endpage=startpage+10-1;
	       //마지막페이지>시작페이지+10-1     마지막페이지=시작페이지+10-1
	    /*페이징 연산 끝*/
		
		listM.addAttribute("totalCount", totalCount); //totalCount 키이름에 총 레코드 개수 젖ㅇ
		listM.addAttribute("blist", blist); //blist 키이름에 목록 저장
		listM.addAttribute("startpage", startpage);//시작 페이지
		listM.addAttribute("endpage", endpage);//마지막 페이지
		listM.addAttribute("maxpage", maxpage);//총 페이지
		listM.addAttribute("page", page);//현재 쪽 번호 =>페이징에서 내가 본 쪽번호로 바로 이동하는 책갈피 기능 구현
		
		return "board/board_list"; //뷰페이지 경로=>/WEB-INF/views/board/board_list.jsp
	}
}
