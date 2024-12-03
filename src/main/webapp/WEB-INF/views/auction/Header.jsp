<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html lang="ko">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메이플옥션</title>
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	background-color: #f4f4f4;
}

/* 타이틀 */
.title {
	font-size: 24px;
	font-weight: bold;
	margin: 10px;
}

/* 빠른 검색 */
.search {
	margin: 10px; /* 오른쪽 여백 */
	position: relative;
}


/* 네비게이션 스타일 */
.navigation {
    background-color: #333; /* 배경색 */
    padding: 10px 0; /* 위아래 여백 */
    border-bottom: 1px solid #ddd; /* 구분선 */
}

/* 메뉴 리스트를 가로로 정렬 */
.nav-menu {
    display: flex;
    justify-content: space-between; /* 버튼 간격을 균등하게 분배 */
    list-style: none;
    margin: 0 20px; /* 양쪽 여백 추가 */
    padding: 0;
}

/* 리스트 아이템 스타일 */
.nav-menu li {
    flex: 1; /* 각 버튼이 균등한 너비를 가짐 */
    text-align: center;
    margin-right : 10px;
}

/* 버튼 스타일 */
.nav-item {
    width: 100%; /* 버튼이 li를 가득 채움 */
    padding: 10px 15px;
    border: 1px solid black; /* 테두리 검은색 */
    border-radius: 5px; /* 모서리 둥글게 */
    background-color: #333; /* 기본 배경 검은색 */
    color: white; /* 기본 글자색 흰색 */
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s, color 0.3s;
}

/* 버튼에 마우스 올렸을 때 */
.nav-item:hover {
    background-color: #333; /* 호버 시 더 진한 검은색 */
    color: #fff; /* 글자색 유지 */
}
</style>
</head>




<body>
	<header class="header">

		<div class="main">
			<!-- 타이틀 -->
			<div class="title">메이플옥션</div>

			<!-- 빠른 검색 -->
			<div class="search">
				빠른 검색 : <input type="text">
			</div>
		</div>
		<!-- 유저 정보 (아이디, 캐시, 로그아웃) -->
		<div class="user-info">
		 <sec:authorize access="isAuthenticated()">
			<span class="user-name">아이디: ${user.user_id}</span>
		    <span class="cash">메소: ${user.cash}</span> 
		   
			<form action="/customLogout" method="post" class="logout">
				<button type="submit">로그아웃</button>
			</form>
			</sec:authorize>
			
			<sec:authorize access="isAnonymous()">
				<form action="/customLogin" method="get" class="logout">
				<button type="submit">로그인</button>
			</form>
			</sec:authorize>
			
		</div>
	</header>
	
	<nav class="navigation">
    <ul class="nav-menu">
        <li>
            <form action="search" method="get">
                <button class="nav-item" type="submit">검색</button>
            </form>
        </li>
        <li>
            <form action="market" method="get">
                <button class="nav-item" type="submit">시세</button>
            </form>
        </li>
        <li>
            <form action="wishlist" method="get">
                <button class="nav-item" type="submit">찜목록</button>
            </form>
        </li>
        <li>
            <form action="sell" method="get">
                <button class="nav-item" type="submit">판매</button>
            </form>
        </li>
        <li>
            <form action="completed" method="get">
                <button class="nav-item" type="submit">완료</button>
            </form>
        </li>
    </ul>
</nav>
	
	
	
</body>
</html>