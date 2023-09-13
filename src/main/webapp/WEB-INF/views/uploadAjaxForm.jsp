<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비동기식 Ajax 파일 첨부 연습</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
	 
 	$('#uploadBtn').on('click', function(e){
 		
 		var formData = new FormData(); //폼데이터 객체 생성
 		/*첨부 파일 업로드하는 또 다른 방식은 비동기식 아작스 이용하여 폼데이터 객체 이용하는 것.
 		이방식은 IE 10버전 이후에서만 지원
 		*/
 		
 		var inputFile = $("input[name = 'uploadFile']"); //아이디 선택자 말고 네임 파라미터 이름으로 
 													     //jQuery에서 input type="file"에 접근
 		var files = inputFile[0].files; //첫번쨰 input type="file"로 첨부한 것을 배열로 구함
 		
 		for(var i = 0; i<files.length; i++){
 			formData.append("uploadFile", files[i]); //첨부파일을 폼 데이터에 추가
 		}
 		
 		$.ajax({
 			
 			
 		});//비동기식 jQuery 아작스 함수
 	});
 });
 
 


</script>
</head>
<body>

</body>
</html>