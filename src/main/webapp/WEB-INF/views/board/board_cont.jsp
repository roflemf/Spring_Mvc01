<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게시판 내용보기와 아작스 댓글 프로그램</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<%--jQuery라이브러리 CDN--%>
<style type="text/css">
#modDiv { /* 댓글 수정폼 UI */
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute; /*절대위치 */
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000; /* position 속성이 absolute,fixed(고정위치) 로 설정된 곳에서 사용된다.
 					   요소가 겹쳐지는 순서를 제어 => 숫자값이 큰것이 앞에 나옴.*/
}
</style>
</head>
<body>
 <table border="1">
  <tr>
   <th colspan="2">스프링 MVC게시판 내용보기</th>
  </tr>
  <tr>
   <th>제목</th>
   <td>${b.title}</td>
  </tr>
  <tr>
   <th>내용</th>
   <td>${content}</td>
  </tr>
  <tr>
   <th>조회수</th>
   <td>${b.viewcnt}</td>
  </tr>
  <tr>
   <th colspan="2">
    <input type="button" value="수정" 
    onclick="location='/controller/board/board_cont?bno=${b.bno}&page=${page}&state=edit';">
    <%--stae=edit 구분값 때문에 동일 매핑주소를 사용해도 수정폼으로 이동 --%> 
    
    <c:if test="${b.replycnt ==0 }">
    <input type="button" value="삭제" 
    onclick="if(confirm('정말로 삭제할까요?')==true){
    	location='/controller/board/board_del?bno=${b.bno}&page=${page}';}else{return;}">
   <%--자바스크립트에서 confirm()내장함수는 확인/취소 버튼을 가진 경고창을 만들어줌.
   		확인 클릭시 true 반환, 취소 클릭시 false반환 
   		즉, 확인 클릭시 삭제로 이동, 취소 클릭시 return에 의해 종료 ->현재 내용보기에서 그대로
   		다시 한번 더 삭제 유무 확인하게 함--%>
   		</c:if>
   		
   		<input type="button" value="목록"
   		onclick="location='/controller/board/board_list?page=${page}';">
   
   </th>
  </tr>
 
 </table>
 
 <!-- 댓글 Start-->
 <br>
 <hr>
 <br>
 
 
 
	<%--댓글 수정화면 --%>
	<div id="modDiv" style="display: none;">
		<%--display:none; 은 댓글수정화면을 안나오게 한다. --%>
		<div class="modal-rno"></div>
		<%--댓글번호 출력부분 --%>
		<div>
			<textarea rows="3" cols="30" id="replytext"></textarea>
		</div>
		<div>
			<button type="button" id="replyModBtn">댓글수정</button>
			<button type="button" id="replyDelBtn">댓글삭제</button>
			<button type="closeBtn" id="closeBtn" onclick="modDivClose();">닫기</button>
		</div>


	</div>
	<h2>아작스 댓글 연습 페이지</h2>
	<div>
		<div>
			댓글 작성자 : <input name="replyer" id="newReplyWriter">
		</div>
		<br>

		<div>
			댓글내용:
			<textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
		</div>
		<br> <input type="button" id="replyAddBtn" value="댓글등록">
	</div>

	<br>
	<hr>
	[<strong>${b.replycnt}</strong> 개의 댓글]
	<br>

	<%--댓글목록 --%>
	<ul id="replies"></ul>

	
	<script>
		$bno = ${b.bno}; //게시판 번호, 자바스크립트에서 JSTL OR EL 가능

		getAllList();//댓글 목록함수 호출

		function getAllList() {
			$.getJSON("/controller/replies/all/" + $bno,function(data) {
			//get방식으로 JSON데이터 읽어오는 jQuery 비동기식 아작스 함수, 
			//가져온 데이터는 data매개변수에 저장
			$result = "";
			$(data).each(
			function() { //jQuery each()
			$result += "<li data-rno='" + this.rno + "' class='replyLi'>"
						+ this.rno
						+ " : <span class='com' style='color:blue; font-weight:bold;'>"
						+ this.replytext
						+ "</span> <button type='button'>댓글수정</button></li><br>";
						});
								$('#replies').html($result);
								//html() jQuery함수는 해당 replies 아이디 영역에 문자와 태그를 함께 변경 적용
							});
			//""; html 문자열처리 (''도 되지만 바깥쪽에 있을경우 ""해야)
			//'';  속성값

		}//getAllList()

		//댓글등록
		$('#replyAddBtn').on('click', function() {
			$replyer = $('#newReplyWriter').val();//댓글 작성자 ;  입력박스 입력값을 가져옴
			$replytext = $('#newReplyText').val();//댓글 내용

			$.ajax({
				type : 'post',//보내는 방식을 post로 지정
				url : '/controller/replies/reply_add',//서버 매핑주소
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text', //보내는 자료형
				data : JSON.stringify({ //JSON.stringify ; json데이터가 문자열로 넘어감
					bno : $bno, //bno 파라미터 이름에 게시판 번호값 담아 전달, 왼쪽; JSON데이터 키이름, 오른쪽;값
					replyer : $replyer, //댓글 작성자
					replytext : $replytext
				//댓글 내용

				}),//보내는 데이터
				success : function($data) {
					if ($data == 'SUCCESS') {
						alert('댓글이 등록되었습니다');
						location.reload(); //자바스크립트에서 새로고침(F5)
						getAllList();//댓글 목록 함수 호출
					}
				}
			});//jQuery 비동기식 아작스 함수
		});

		//댓글 수정화면
		$('#replies').on('click', '.replyLi button', function() {
			var reply = $(this).parent(); //부모 요소인 li태그를 구함
			var rno=reply.attr("data-rno"); //댓글 번호를 구함 =>li 태그의 data-rno 속성값 구함
			var replytext=reply.children('.com').text(); 
			//댓글 내용을 구함 => li태그 자식 요소 중 클래스 선택자.com의 댓글 내용 문자만 text()함수로 구함.
			
			//text함수; 해당영역 문자 ()있으면 ;변경/적용 / ()없으면; 반환
			
			$('.modal-rno').html(rno); //model-rno 클래스 선택자 영역에 댓글 번호를 변경 적용
			$('#replytext').val(replytext); //text area 입력박스에 val()함수로 댓글 내용 변경,적용
										    //(인자값 있으면);변경,적용  /(인자값)없으면; 입력박스에 댓글내용을 반환
			$('#modDiv').show(1000);//댓글 수정화면 보이는 속도(숫자입력해도 조절 가능; ex)'slow','fast',1000(1초) 숫자클수록 느림)
			
			
			
			
		});
		
		//댓글 수정 화면 닫기
		function modDivClose() {
			$('#modDiv').hide(1000);
		}//modDivClose()
		
		//댓글 수정 완료
		$('#replyModBtn').on('click',function(){
			$rno = $('.modal-rno').html(); //댓글번호
			$replytext = $('#replytext').val();//수정할 댓글 내용
			
			$.ajax({
				type:'put', //보내는 방식
				url:'/controller/replies/'+$rno,//서버 매핑주소
				headers:{
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "PUT"
				},
				data:JSON.stringify({
					replytext:$replytext
				}),
				dataType:'text',
				success:function(result){
					if(result == 'SUCCESS'){
						alert('댓글이 수정 되었습니다!');
						$('#modDiv').hide(300); //댓글 수정화면 닫기
						getAllList();//댓글 목록함수 호출
					}
				}
			});
		});
		
		//댓글삭제
		$('#replyDelBtn').on('click',function(){
			$rno=$('.modal-rno').html(); //댓글 번호
			
			$.ajax({
				type : 'delete',
				url : '/controller/replies/'+$rno,
				headers:{
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType: "text",
				success: function (data) {
					if(data == 'SUCCESS'){
						alert('댓글이 삭제되었습니다!');
						$('#modDiv').hide('1000');
						location.reload(); //자바스크립트에서 새로고침(F5)
						getAllList();//댓글 목록함수 호출
					}
				}
			});
		});
	</script>
	
 <!-- 댓글 End-->
 
 

</body>
</html>



