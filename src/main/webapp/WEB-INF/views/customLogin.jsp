<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* 전체 화면에 가운데 정렬 */
body {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	font-family: Arial, sans-serif;
	background-color: #f0f2f5;
	margin: 0;
}

/* 로그인 박스 스타일 */
.login-container {
	width: 350px;
	padding: 20px;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	text-align: center;
}

h2 {
	margin-bottom: 20px;
	color: #333;
}

/* 폼의 인풋 필드 스타일 */
.form-group {
	margin-bottom: 15px;
	text-align: left;
}

label {
	font-weight: bold;
	color: #555;
	display: block;
	margin-bottom: 5px;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	box-sizing: border-box;
}

/* 버튼 스타일 */
.button {
	width: 100%;
	padding: 10px;
	margin-top: 10px;
	font-size: 16px;
	font-weight: bold;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.login-button {
	background-color: #4CAF50;
	color: #fff;
}

.join-button {
	background-color: #2196F3;
	color: #fff;
}

/* 버튼 호버 효과 */
.login-button:hover {
	background-color: #45a049;
}

.join-button:hover {
	background-color: #1e88e5;
}
</style>
</head>
<%-- <body>
    <div class="login-container">
        <h2>로그인</h2>
        <form action="login" method="post">
            <div class="form-group">
                <label for="username">아이디</label>
                <input type="text" placeholder="username" id="username" name="username"  type="text" autofocus>
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" placeholder="Password" id="password" name="password" type = "password">
            </div>
            <button type="submit" class="button login-button">로그인</button>
        </form>
        
        <form action="join" method="get">
            <button type="submit" class="button join-button">회원가입</button>
        </form>

        <!-- 에러 메시지가 있으면 팝업 창 표시 -->
        <c:if test="${not empty errorMessage}">
            <script>
                alert("${errorMessage}");
            </script>
        </c:if>
    </div>
</body> --%>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Please Sign In</h3>
				</div>
				<div class="panel-body">
					<form role="form" action="login" method="post">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="username"
									name="username" type="text" value="user0">
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="Password"
									name="password" type="password" value="pw0">
							</div>
							<div class="checkbox">
								<label> <input name="remember" type="checkbox"
									value="Remember Me">Remember Me
								</label>
							</div>
							<!-- Change this to a button or input when using this as a form -->
							<!-- <a href="index.html" class="btn btn-lg btn-success btn-block">Login</a>
                                  -->
							<button type="submit" class="button login-button">로그인</button>
						</fieldset>
					</form>

					<form action="/auction/search" method="get">
						<button type="submit"  class="button login-button">로그인 하지않고 접속</button>
					</form>
					
					  <form action="/join" method="get">
          				  <button type="submit" class="button join-button">회원가입</button>
       				 </form>
				</div>
			</div>
		</div>
	</div>
</div>

</html>