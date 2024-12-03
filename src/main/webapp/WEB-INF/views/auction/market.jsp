<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="../auction/Header.jsp"%>


<html lang="ko">
<head>

<style>
.popup {
	display: flex;
}

.popup-container {
	display: flex;
	align-items: center;
	gap: 20px;
}

.popup-details {
	margin: 10px;
	display: flex;
	flex-direction: column;
}

#popupCount {
	width: 80px;
	padding: 8px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 5px;
}
</style>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Item Name</th>
				<th>Price</th>
				<th>count</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				
					<tr class="clickable-row">
						<td><img src="<spring:url value='/resources${item.url}' />"
							width="50" height="50"> ${item.name}</td>
						<td>${item.price}</td>
						<td>${item.count }</td>
						<td>${item.cidate}</td>
					</tr>
				
			</c:forEach>
		</tbody>
	</table>


</body>


</html>
