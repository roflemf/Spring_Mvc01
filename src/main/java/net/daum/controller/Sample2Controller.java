package net.daum.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.daum.vo.SampleVO;

@RestController 
//스프링4.0이후부터는 @RestController 사용함으로써 jsp와 같은 뷰페이지 만들지 않고도 Rest방식의 데이터를 처리할 수 있게 되었다.
//만들어지는 데이터는 키,값 쌍의 json, 문자열 등이다.
public class Sample2Controller {
	
	@RequestMapping("rest_start") 
	//get or post 로 접근하는 매핑주소 처리, rest_start 매핑주소 등록
	public String rest_start() {
		return "REST Api begin"; //문자열 객체 반환
	}//rest_start()
	
	@RequestMapping(value = "/sendVO", produces="application/json")
	public SampleVO sendVO() {
		//메서드 리턴타입이 SampleVO이면  해당 클래스 변수명이 json데이터의 키이름이 된다.
		SampleVO vo = new SampleVO();
		vo.setMno(7);
		vo.setFirstName("홍");
		vo.setLastName("길동");
		
		return vo;
	}//sendVO()
	
	//컬렉션 List 타입의 JSON 데이터를 생성
	@RequestMapping(value = "/sendList", produces="application/json")//sendList매핑주소등록
	public List<SampleVO> sendList(){
		List<SampleVO> list=new ArrayList<>();
		
		for(int i=1; i<=5; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("강");
			vo.setLastName("감찬");
			
			list.add(vo); //컬렉션에 추가
			
		}
		return list;
	}//sendList()
	
	//키,값 쌍의 사전적 자료구조 컬렉션 Map 타입 JSON 생성
	@RequestMapping(value = "/sendMap", produces="application/json")
	public Map<Integer, SampleVO> sendMap(){
		Map<Integer,SampleVO> map = new HashMap<>();
		
		for(int i = 1; i<=3; i++) {
			SampleVO vo = new SampleVO();
			
			vo.setMno(i);
			vo.setFirstName("홍");
			vo.setLastName("길동");
			
			map.put(i, vo); //컬렉션 맵에 키,값 쌍으로 저장
		}
		return map;
	}//sendMap()
	
	@RequestMapping("/sendError")
	public ResponseEntity<Void> sendError(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		/* @RequestController 는 별도의 JSP 파일을 만들지 않고도 REST API 서비스를 실행하기 때문에
		 * 결과데이터에 대한 예외 오류가 많이 발생한다.
		 * 이런 경우는 스프링에서 제공하는 내장 API인 
		 * ResponseEntity타입을 사용해 개발자에게 문제가 되는 
		 * HTTP 에러 상태코드인 404(파일없음),500(오타에러)등과 같은 나쁜 상태코드를 데이터와 함께 브라우저로 전송되어져
		 * 좀 더 세밀한 에러제어를 할 수 있게 함.
		 * 
		 * 200상태코드는 정상 상태코드이고 BAD_REQUEST는 400 나쁜 상태코드를 브라우저로 전송한다.
		 * 
		 */
	}//sendError()
	
	//컬렉션 List 타입의 정상적인 json 데이터와 404(파일없음) 나쁜상태코드를 동시에 브라우저로 전송
	@RequestMapping(value = "/sendListNot", produces="application/json")
	public ResponseEntity<List<SampleVO>> sendListNot(){
		List<SampleVO> list = new ArrayList<>();
		
		for(int i = 1; i<=3; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("홍");
			vo.setLastName("길동");
			
			list.add(vo); //컬렉션에 추가
		}
		return new ResponseEntity<List<SampleVO>>(list, HttpStatus.NOT_FOUND);
		//NOT_FOUND 가 404 나쁜상태 코드 반환
	}

}
