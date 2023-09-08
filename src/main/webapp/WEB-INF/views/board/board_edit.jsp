<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게시판 수정</title>
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
<form method="post" action="board_edit_ok" onsubmit="return check();"> 
 <input type="hidden" name = "bno" value="${b.bno}">
 <%--hidden은 브라우저 상에서 만들어지지 X
 	하지만 값을 네임파미터 이름에 담아서 전달할 때 사용
 	브라우저 출력창에서 페이지 소스보기시; hidden 이 노출되기 때문에 보안상 중요한 비번같은 값은 히든으로 전달 X --%>
 <input type="hidden" name = "page" value="${page}">
 <%--책갈피 기능때문에 쪽 번호 전달 --%>

  <h2>스프링 MVC 게시판 수정폼</h2>
  글쓴이 : <input name = "writer" id="writer" size="14" value="${b.writer}"><br><br> <%--value="${b.writer};기존값 --%>
  글제목 : <input name = "title" id="title" size="36" value="${b.title}"><br><br>
  글내용 : <textarea name = "content" id="content" rows="10" size="36"  value="${b.content}" ></textarea>
  <hr>
  <input type="submit" value="수정">
  <input type="reset" value="취소" onclick="location='/controller/board/board_list?page=${page}';" > 
  <input type="button" value="목록" 
  onclick= "location='/controller/board/board_list?page=${page}';"> 
  <%--페이징에서 책갈피 기능을 구현하기 위해 board_list?page=쪽번호를 get방식으로 전달하면
  	  내가 본 페이지 번호로 바로 이동 --%>
</form>
</body>
</html>