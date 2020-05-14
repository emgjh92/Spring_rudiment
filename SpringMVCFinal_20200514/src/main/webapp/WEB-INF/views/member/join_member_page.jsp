<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%--bootstrap CSS 코드 --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
<!-- 유효성 검사 -->
<script>
	function frm_submit() {
		
		// form tag 가져오기
		var frm = document.getElementById("frm");	//document: javascript 내장 객체. 문법적인 변수 타입이 없음. frm이 태그이므로 태그가 받아지면서 object 타입이 됨
		var idTag = document.getElementById("member_id");	// ""안에 id입력. 태그 자체를 가져오므로 타입 object임
		
		// id 유효성 검사/ /^: 시작, (): 중간, *: 개수 상관x, @ 무조건 들어가야 함, {2,3}: 개수 제한(2글자 or 3글자)
		var reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;	// 정규 표현식
		
		if(idTag.value == "") {	// .value 해야 태그 안의 값을 가져올 수 있음
			alert("아이디를 입력해주세요");
			idTag.focus();	// 커서 나오게
			return;	// submit 발생 안되게 return으로 빠져나옴
		}
		
		// test()의 return type: true or false
		if(!reg.test(idTag.value)) {
			alert("아이디는 이메일 형식으로 입력해주세요");
			idTag.value = "";
			idTag.focus();
			return;
		}
		
		// $/: 끝
		reg = /^[A-Za-z0-9]{6,12}$/;
		var pwTag = document.getElementById("member_pw");
		
		if(pwTag.value == "") {
			alert("비밀번호를 입력해주세요");
			pwTag.focus();
			return;
		}
		
		if(!reg.test(pwTag.value)) {
			alert("비밀번호는 영문 및 숫자 조합 6~12자로 입력해야합니다.");
			pwTag.value = "";
			pwTag.focus();
		}
		
		reg = /^[A-Za-z0-9]{6,12}$/;
		var nickTag = document.getElementById("member_nick");
		
		if(nickTag.value == "") {
			alert("닉네임을 입력해주세요");
			nickTag.focus();
			return;
		}
		
		// input type="submit"
		frm.submit();	// 회원가입 버튼 눌렀을 때 전송됨 	
		
	}
</script>
</head>
<body>
<!-- navbar -->
<jsp:include page="../commons/global_nav.jsp"></jsp:include>

<form id="frm" action="join_member_process.do" method="post">
<div class="row">
	<div class="col-4"></div>	<!-- 왼쪽 여백 -->
	<div class="col-4 mt-5">			<!-- 가운데 내용 부분 -->
		<div class="row">	<!-- 회원가입 제목 -->
			<div class="col mb-3 text-center"><h1>회원 가입</h1></div>
		</div>	
		<div class="row">	<!-- ID -->
			<div class="col-3">ID(E-mail)</div>
			<div class="col"><input id="member_id" class="form-control" type="text" name="member_id" ></div>
		</div>	
		<div class="row mt-1">	<!-- PW -->
			<div class="col-3">PW</div>
			<div class="col"><input id="member_pw" class="form-control" type="password" name="member_pw"></div>
		</div>	
		<div class="row mt-1">	<!-- NICK -->
			<div class="col-3">Nickname</div>
			<div class="col"><input id="member_nick" class="form-control" type="text" name="member_nick"></div>
		</div>
		<!--  라디오 버튼 둘 중 하나 선택할 수 있게 하려면 name을 같게 해야함. 미리 기본값 설정: checked  -->
		<div class="row mt-1">	<!-- 성별 -->
			<div class="col-3">성별</div>
				<div class="col btn-group btn-group-toggle" data-toggle="buttons">
					<label class="btn btn-secondary">
						<input type="radio" name="member_sex" value="M" id="option1">남
					</label>
					<label class="btn btn-secondary active">
						<input type="radio" name="member_sex" value="W" id="option2" checked>여
					</label>
				</div>
			</div>	
		<!--  체크박스 name 같게해서 하나로 묶어줌. value는 sequence 번호(PK)  -->
		<div class="row mt-1">	<!-- 취미 -->
			<div class="col-3">취미</div>
			<div class="col btn-group-toggle" data-toggle="buttons">
				<label class="btn btn-secondary">
					<input type="checkbox" name="member_hobby" value="1">야구
				</label>
				<label class="btn btn-secondary">
				 	<input type="checkbox" name="member_hobby" value="2">축구 
				</label>
				<label class="btn btn-secondary">
				 	<input type="checkbox" name="member_hobby" value="3">농구
				 </label>
				 <label class="btn btn-secondary">
				 	<input type="checkbox" name="member_hobby" value="4">배구
			 	</label>
		 	</div>
		</div>
		<div class="row mt-3">	<!-- 회원가입 버튼 -->
			<div class="col">
				<input type="button" value="회원 가입" class="btn btn-success btn-block" onclick="frm_submit()">
			</div>
		</div>
	</div>
	<div class="col-4"></div>	<!-- 오른쪽 여백 -->
</div>
</form>
<%--bootstrap JS 코드 --%>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>