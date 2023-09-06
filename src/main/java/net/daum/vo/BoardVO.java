package net.daum.vo;

import java.sql.Timestamp;

public class BoardVO {//테이블 컬럼명, 네임파라미터 이름, 빈클래스 변수명을 같게하는 데이터 저장빈 클래스
					 // => 스프링 MVC 게시판(tbl_board테이블)
	private int bno; //게시판 번호
	private String writer; //작성자
	private String title; //글제목
	private String content; //글내용
	private int viewcnt; //조회수
	private Timestamp regdate; //등록날짜
	
	//페이징 (쪽 나누기)
	private int startrow; //시작행 번호
	private int endrow; //끝행 번호
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getStartrow() {
		return startrow;
	}
	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}
	public int getEndrow() {
		return endrow;
	}
	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}
	
	
	

}
