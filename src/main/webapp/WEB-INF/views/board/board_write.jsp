<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게시판 글쓰기</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function check(){
		if($.trim($('#writer').val())==''){
			alert('글쓴이를 입력하세요!');
			$('#writer').val('').focus();
			return false;
		}
		if($.trim($('#title').val()).length==0){
			alert('글 제목을 입력하세요!');
			$('#title').val('').focus();
			return false;
		}
		
		if($.trim($('#content').val()).length==0){
			alert('글 내용을 입력하세요!');
			$('#content').val('').focus();
			return false;
		}
	}

</script>
</head>
</head>
<body>
<form method="post" onsubmit="return check();"> 
<%--action 속성을 생략하면 이전 매핑주소인 board_write 가 액션 매핑주소가 된다. 
	같은 컨트롤러 동일 매핑주소가 사용되면 method방식인 get or post 등으로 구분 --%>
  <h2>스프링 MVC 게시판 입력폼</h2>
  글쓴이 : <input name = "writer" id="writer" size="14" ><br><br>
  글제목 : <input name = "title" id="title" size="36" ><br><br>
  글내용 : <textarea name = "content" id="content" rows="10" size="36" ></textarea>
  <hr>
  <input type="submit" value="입력">
  <input type="reset" value="취소" onclick="$(writer).focus();" > 
  <input type="button" value="목록" 
  onclick= "location='/controller/board/board_list?page=${page}';"> 
  <%--페이징에서 책갈피 기능을 구현하기 위해 board_list?page=쪽번호를 get방식으로 전달하면
  	  내가 본 페이지 번호로 바로 이동 --%>
</form>
</body>
</html>