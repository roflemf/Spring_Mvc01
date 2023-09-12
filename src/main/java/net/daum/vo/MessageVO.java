package net.daum.vo;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageVO { //TBL_MESSAGE 테이블의 컬럼명과 일치하는 변수명을 가진 데이터 저장빈 클래스
	
	private int mid;
	private String targetid; //외래키 추가설정 -> tbl_user 테이블의 uid2컬럼 레코드인 회원 아이디값만 저장됌
	private String sender; //메세지 보낸 사람
	private String message; //보낸 메세지
	private Timestamp senddate; //보낸 날짜
	
	
}
