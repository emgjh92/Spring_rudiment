<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/board/write_content_process.do" method="post">
	닉네임 : ${sessionUser.member_nick }<br>
	제목 : <input type="text" name="board_title"><br>
	내용 : <br>
	<textarea rows="10" cols="40" name="board_content"></textarea>
	<input type="submit" value="글쓰기">
</form>

</body>
</html>