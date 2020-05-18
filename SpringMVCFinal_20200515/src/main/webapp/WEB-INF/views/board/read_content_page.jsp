<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>


<script>
	function refreshReplyList(){
		var boardNo = ${boardContent.boardVo.board_no};
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			   if(xmlhttp.readyState==4 && xmlhttp.status == 200){   
					//alert(xmlhttp.responseText);
					var resultData = JSON.parse(xmlhttp.responseText); //api 사용
					
					//자바스크립트로 동적 UI 꾸미기...
					var replyBox = document.getElementById("reply_box");
					
					//리플 박스 안에 내용 지우기 (댓글을 다는 순간 댓글 박스 내용은 사라지고 새 댓글이 추가되므로)
					var length=replyBox.childNodes.length;
					for(var i=0; i<length; i++){
						replyBox.removeChild(replyBox.childNodes[0]);
					}
					
					//동적 댓글 생성
					for(var data of resultData){
						var boxRow1 = document.createElement("div");
						boxRow1.setAttribute("class","row");
						
						var boxRow1_col1 = document.createElement("div");
						boxRow1_col1.setAttribute("class","col-8");
						
						var boxNick = document.createElement("div");
						boxNick.setAttribute("class","alert alert-secondary");
						
						boxNick.innerText = data.memberVo.member_nick;
						
						boxRow1_col1.appendChild(boxNick);
						boxRow1.appendChild(boxRow1_col1);
						
						var boxRow1_col2 = document.createElement("div");
						boxRow1_col2.setAttribute("class","col");
						
						var boxDate= document.createElement("div");
						boxDate.setAttribute("class","alert alert-secondary");
						
						//날짜 변환 ---------------------
					var milliseconds = data.replyVo.reply_writedate;
					var date = new Date(milliseconds);
					
					boxDate.innerText = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
					
					boxRow1_col2.appendChild(boxDate);
					boxRow1.appendChild(boxRow1_col2);
						//------------------------------
						var boxRow2 = document.createElement("div");
						boxRow2.setAttribute("class","row");
						
						var boxRow2_col1 = document.createElement("div");
						boxRow2_col1.setAttribute("class","col");
						
						var replyContentBox = document.createElement("div");
						replyContentBox.setAttribute("class","alert alert-primary");
						replyContentBox.innerText = data.replyVo.reply_content;
						
						boxRow2_col1.appendChild(replyContentBox);
						
						boxRow2.appendChild(boxRow2_col1);
						
						replyBox.appendChild(boxRow1);
						replyBox.appendChild(boxRow2);
					}
			   }
		};
		xmlhttp.open("get","./get_reply_list.do?board_no="+boardNo,true);
	    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	    xmlhttp.send();
		
	}

	function writeReply(){
		var boardNo = ${boardContent.boardVo.board_no};
		var replyContent = document.getElementById("reply_content").value;
		
		var xmlhttp = new XMLHttpRequest();
		
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				refreshReplyList();
			}
		};
		
		xmlhttp.open("post","./write_reply_process.do", true);
	    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	    xmlhttp.send("board_no=" + boardNo + "&reply_content=" + replyContent);
	}

</script>
</head>
<body onload="refreshReplyList()">
<div class="container">
	<div class="col">
	
	</div>
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
	<div class="row"><!-- 댓글 리스트 박스 -->
	<div id="reply_box" class="col"><!-- 리스트 컨텐트 박스-->
		<div class="row"><!-- 1번 댓글 -->
			<div class="col-8"><!-- 닉네임 -->
				<div class="alert alert-secondary">한조</div>
			</div>
			<div class="col"><!-- 날짜 -->
				<div class="alert alert-secondary">2020-05-15</div>
			</div>
		</div>
			<div class="row">
				<div class="col"><!-- 내용 -->
				<div class="alert alert-primary">안녕하세요. 반갑습니다</div>
				</div>
			</div>
		
	</div>
	</div>
	<div class="row"><!-- 댓글입력 -->
		<div class="col">댓글</div>
		<div class="col-8"><textarea id="reply_content" class="form-control"></textarea></div>
		<div class="col"><input onclick="writeReply()" type="button" class="btn-block btn-primary" value="작성"></div>
	</div>
</div>

	<c:if test="${!empty sessionUser && sessionUser.member_no == boardContent.memberVo.member_no }">
		<a href="${pageContext.request.contextPath }/board/modify_content_page.do?board_no=${boardContent.boardVo.board_no }">수정</a>
		<a href="${pageContext.request.contextPath }/board/delete_content_process.do?board_no=${boardContent.boardVo.board_no }">삭제</a>
	</c:if>
	<a href="${pageContext.request.contextPath }/board/main_page.do">목록</a>
	


<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	
</body>
</html>

