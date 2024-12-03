<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		
		<h2>회원 가입</h2>
    <form action="join" method="post">
        <div>
            <label for="userid">아이디</label>
            <input type="text" id="userid" name="userid" required>
        </div>
        <div>
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>
        </div>
        
        <div>
        	  <label for="username">닉네임</label>
            <input type="text" id="username" name="username" required>
        	
        </div>
        
        <button type="submit">회원가입</button>
    </form>
   
		
</body>
</html>