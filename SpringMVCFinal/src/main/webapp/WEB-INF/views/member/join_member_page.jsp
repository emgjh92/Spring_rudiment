<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form action="./join_member_process.do" method="post">
	<div class="row">
		<div class="col-3">
		</div>
		<div class="col">
			<div class="row text-center" ><!-- 로고자리 -->
				<div class="col">
					<h1>회원가입</h1>
				</div>
			</div>
			<div class="row mt-5"><!-- id -->
				<div class="col-1">
					ID
				</div>
				<div class="col ml-1">
					<input placeholder="이메일을 입력해 주세요." name="member_id" type="text" class="form-control">
				</div>
			</div>
			<div class="row mt-1"><!-- pw -->
				<div class="col-1">
					PW
				</div>
				<div class="col ml-1">
					<input placeholder="비밀번호를 입력해 주세요."name="member_pw" type="password" class="form-control">
				</div>
			</div>
			<div class="row mt-1"><!-- Nick -->
				<div class="col-1">
					Nick
				</div>
				<div class="col ml-1">
					<input placeholder="닉네임을 입력해 주세요."name="member_nick" type="text" class="form-control">
				</div>
			</div>
			<div class="row mt-1"><!-- 성별 -->
				<div class="col-3">
				성별
				</div>
				<div class="col">
					<input name="member_sex" type="radio" value="M" checked>남
					<input name="member_sex" type="radio" value="w">녀
				</div>
			</div>
			<div class="row mt-1"><!-- 취미 -->
				<div class="col-3">
				취미
				</div>
				<div class="col">
					
					<input type="checkbox" name="member_hobby" value="1">농구
					<input type="checkbox" name="member_hobby" value="2">축구
					<input type="checkbox" name="member_hobby" value="3">야구
					<input type="checkbox" name="member_hobby" value="4">독서
					
				</div>
			</div>
			<div class="row mt-3"><!-- 가입 버튼 -->
				<div class="col ml-1">
					<input type="submit" value="회원가입" class="btn btn-outline-primary btn-block">
				</div>
				<div>
				</div>
			</div>
			
		</div>
		<div class="col-3">
		</div>
	</div>
</form>
</div>

<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>