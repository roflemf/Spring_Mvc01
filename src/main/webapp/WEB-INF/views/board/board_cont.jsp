<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
    
    <input type="button" value="삭제" 
    onclick="if(confirm('정말로 삭제할까요?')==true){
    	location='/controller/board/board_del?bno=${b.bno}&page=${page}';}else{return;}">
   <%--자바스크립트에서 confirm()내장함수는 확인/취소 버튼을 가진 경고창을 만들어줌.
   		확인 클릭시 true 반환, 취소 클릭시 false반환 
   		즉, 확인 클릭시 삭제로 이동, 취소 클릭시 return에 의해 종료 ->현재 내용보기에서 그대로
   		다시 한번 더 삭제 유무 확인하게 함--%>
   		
   		<input type="button" value="목록"
   		onclick="location='/controller/board/board_list?page=${page}';">
   
   </th>
  </tr>
 
 </table>

</body>
</html>