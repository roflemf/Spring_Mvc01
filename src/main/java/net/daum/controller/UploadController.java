package net.daum.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {//파일첨부(이진파일 업로드) 스프링 컨트롤러 클래스
	
	@GetMapping("/uploadForm") //get으로 접근하는 매핑주소 처리, uploadForm매핑주소 등록
	public void uploadForm() {
		//리턴 타입이 없는  void 형이면 매핑주소가 뷰페이지 파일명이 된다. => 뷰 리졸브 경로는 /WEB-INF/views/
		//uploadForm.jsp
	}//uploadForm()
	
	//동기식 이진파일 업로드
	@PostMapping("/uploadFormAction") //post로 접근하는 매핑주소 처리
	public void uploadFormAction(MultipartFile[] uploadFile, HttpServletRequest request) {
		/* MultipartFile 스프링 api를 사용해 업로드 되는 파일 데이터를 쉽게 처리, 
		 * 다중 업로드 파일은 배열로 받음. 
		 * 매개변수명 (전달인자명)uploadFile 은 <input type="file" name="uploadFile">
		 * 네임파라미터 이름(네임속성)과 같아야 함
		 */
		String uploadFolder = request.getRealPath("/resources/upload");
		//첨부 파일 업로드 실제경로
		System.out.println("첨부 파일 업로드 실제 경로  : " + uploadFolder);
		
		for(MultipartFile multi:uploadFile) {
			System.out.println("=================================>");
			System.out.println("Upload file name : " + multi.getOriginalFilename());
			//첨부된 실제 원본 파일명
			System.out.println("Upload file size : " + multi.getSize());//업로드 파일 크기
			
			File saveFile=new File(uploadFolder, multi.getOriginalFilename());
			
			try {
				multi.transferTo(saveFile);//업로드 폴더에 첨부파일 실제 원본파일명으로 업로드 됌.
			}catch (Exception e) {e.printStackTrace();}
		}//for
		
		/* 문제)
		 * Tomcat 서버에 의해 실제 upload 폴더에 첨부된 파일이 업로드 되게 만들어보기.
		 * 한개 첨부파일 뿐 아니라 다중 파일 첨부 확인. 에러시 디버깅. 정상적 프로그램 되게하기
		 * 
		 * 
		 */
	}//uploadFormAction()
	
	//비동기식 아작스(화면전환 X)이진파일 폼 뷰페이지 작성 
	@GetMapping("/uploadAjax")
	public ModelAndView uploadAjax() {
		return new ModelAndView("uploadAjaxForm");//생성자 인자값으로 뷰페이지 파일명이 들어감
		//뷰리졸브 경로; /WEB-INF/views/uploadAjaxForm.jsp
	}//uploadAjax

}
