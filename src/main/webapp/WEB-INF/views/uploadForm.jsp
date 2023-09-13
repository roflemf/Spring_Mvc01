<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>스프링 MVC 이진파일 업로드</h1>
	<form method="post" action="uploadFormAction" enctype="multipart/form-data">
	<%--파일 첨부기능인 자료실 기능 만들 때 주의사항 
		1. method=post방식으로 해야함. method 속성 생략하면 기본값이 get방식이다. 
		   get방식으로는 파일 첨부 기능 만들 수 없다. 
		   (자료실 기능 만들 수 X 때문에 , method 속성 생략 X, 반드시 post로 해야 자료실 기능인 파일 첨부 기능 만들 수 O)
		
		2. form태그 내에 enctype="multipart/form-data" 속성을 지정해야 자료실 기능인 파일 첨부 기능 만들 수 O
	--%>
	<input type="file" name="uploadFile" multiple>
	<%--최근 브라우저에서는 multiple속성을 지원 => 하나의 첨부파일 뿐 아니라 다른 첨부파일도 처리 가능 
		이 속성은 IE 10이상 버전에서만 지원--%>
		<input type="submit" value="파일 업로드" >
	</form>
</body>
</html>