<%@ page contentType="text/html; charset=UTF-8" %>
<%--웹 브라우저에 출력되는 문자, 태그 , 언어코딩 타입을 설정해야 HTML 태그, 
	자바스크립트가 잘 적용 되고 출력되는 한글이 깨지지 않음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<%--servertime 은 EL 즉 표현언어에서 HomeController.java 에 설정된 키이름을 참조해서 값을 가져옴--%>
</body>
</html>
