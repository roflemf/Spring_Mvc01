package net.daum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.daum.service.MessageService;
import net.daum.vo.MessageVO;

@RestController
@RequestMapping("/message")//컨트롤러 자체 매핑주소인 message 등록
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	//메세지 추가
	@RequestMapping(value = "/message_insert", method=RequestMethod.POST) 
	// POST 로 접근하는 매핑주소 처리, message_insert 매핑주소 등록
	public ResponseEntity<String> messageInsert(@RequestBody MessageVO vo){
		/* @RequestBody MessageVO vo라고 하면 전송된 JSON 키,값 쌍의 데이터는 MessageVO 객체 타입으로 변경된다.
		 * 
		 */
		
		ResponseEntity<String> entity = null;
		
		try {
			this.messageService.insertMessage(vo); //메세지 추가
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			//예외 에러가 발생하면 에러메세지와 나쁜상태 코드 반환
			
		}
		return entity;
		
	}//messageInsert()	
	

}
