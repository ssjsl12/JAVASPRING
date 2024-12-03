<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Hello world!</h1>

<P>  The time on the server is ${serverTime}. </P>

<%-- 로그인 상태를 JavaScript로 전달 --%>
<%
    // 현재 로그인된 사용자 정보를 확인
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    boolean isLoggedIn = false;
    if (principal instanceof UserDetails) {
        isLoggedIn = true;  // 로그인된 상태
    }
%>

<script>
    var isLoggedIn = <%= isLoggedIn %>;

    if (isLoggedIn) {
        // 로그인 된 경우 homePage로 리디렉션
        window.location.href = "/auction/search";  // 로그인 후 리디렉션할 주소
    } else {
        // 로그인 안 된 경우 customLogin 페이지로 리디렉션
        window.location.href = "customLogin";
    }
</script>

</body>
</html>