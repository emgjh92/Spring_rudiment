<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>

<jsp:include page="../commons/global_nav.jsp"></jsp:include><!-- 글로벌 하게 선언해 불러와 쓰는 상단바 메뉴 -->



<div class="container mt-5">
	<div class="row">
		<div class="col-2"></div>
		<div class="col">
		
		<!-- 검색 기능 추가 -->
	<form action="./main_page.do" method="get">
		<div class="row my-3">
			<div class="col"></div>
			<div class="col-8">
				<input name="search_word" type="text" class="form-control"> 
			</div>
			<div class="col-2"><input type="submit" class="btn btn-primary btn-block " value="검색"></div>
		</div>
	</form>
		
			<div class="row"><!-- 테이블 -->
				<div class="col">
					<table class="table table-hover">
					  <thead>
					   	<tr><td>글번호</td><td>제목</td><td>작성자</td><td>조회수</td><td>작성일</td>
					  </thead>
					  <tbody>
						<c:forEach items="${dataList }" var="xxx">
							<tr>
								<td>${xxx.boardVo.board_no}</td>
         						<td><a href="${pageContext.request.contextPath }/board/read_content_page.do?board_no=${xxx.boardVo.board_no}">${xxx.boardVo.board_title}</a></td>
        					    <td>${xxx.memberVo.member_nick}</td>
         						<td>${xxx.boardVo.board_readcount}</td>
     						    <td><fmt:formatDate value="${xxx.boardVo.board_writedate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</c:forEach>
					  </tbody>
					</table>
				</div>
			</div>
				<div class="row mt-3"><!-- 버튼들... -->
					<div class="col-8"> <!-- 페이지 버튼 -->
					
					<nav aria-label="Page navigation example">
					  <ul class="pagination">
					    	<li class="page-item<c:if test="${beginPage-1 <=0}"> disabled</c:if>"><a class="page-link" href="./main_page.do?currPage=${beginPage-1}&search_word=${param.search_word}">이전</a></li>
					    <c:forEach begin="${beginPage }" end="${endPage}" var="i">
					    	<li class="page-item<c:if test="${currPage==i}"> active </c:if>"><a class="page-link" href="./main_page.do?currPage=${i}&search_word=${param.search_word}">${i}</a></li>
					    </c:forEach>
					    	<li class="page-item<c:if test="${endPage+1 > (totalCount-1)/10+1}"> disabled</c:if>"><a class="page-link" href="./main_page.do?currPage=${endPage+1}&search_word=${param.search_word}">다음</a></li>
	
					  </ul>
					</nav>

					</div>
					<div class="col"><a class="btn btn-primary btn-block " href="./write_content_page.do">글쓰기</a></div>
				</div>
		</div>
		<div class="col-2"></div>
	</div>
</div>

<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>