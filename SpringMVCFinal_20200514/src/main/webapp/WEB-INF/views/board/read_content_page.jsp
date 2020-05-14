<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	작성자: ${boardContent.memberVo.member_nick }<br><br>
	조회수: ${boardContent.boardVo.board_readcount }<br><br>
	제목: ${boardContent.boardVo.board_title }<br><br>
	내용:<br>
	
	<!-- 파일 업로드 기능 추가 -->
	<c:forEach items="${boardContent.fileVoList }" var="fileVo">
		<img src="/upload/${fileVo.file_link_path }"> 
		<br>	
	</c:forEach>
	<!-- ------------- -->
	
	${boardContent.boardVo.board_content }<br><br>
	<c:if test="${!empty sessionUser && sessionUser.member_no == boardContent.memberVo.member_no }">
		<a href="${pageContext.request.contextPath }/board/modify_content_page.do?board_no=${boardContent.boardVo.board_no }">수정</a>
		<a href="${pageContext.request.contextPath }/board/delete_content_process.do?board_no=${boardContent.boardVo.board_no }">삭제</a>
	</c:if>
	<a href="${pageContext.request.contextPath }/board/main_page.do">목록</a>
</body>
</html>

