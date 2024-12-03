<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
				<th><button type = "button" class = "allgetmeso">한번에 받기</button>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
					<c:if test="${item.getprice == 0}">
					<tr class="clickable-row">
					
						<td><img src="<spring:url value='/resources${item.url}' />"
							width="50" height="50"> ${item.name}</td>
						<td>${item.price}</td>
						<td>${item.count }</td>						
						<td>${item.cidate}</td>
						
						<td>	
							<button type="button" class="getmeso"		
								data-price ="${item.price}"
								data-count ="${item.count}"
								data-fkexitem_no ="${item.fkexitem_no}">
								메소회수
							</button>
						</td>
						
					</tr>
					</c:if>
			</c:forEach>
		</tbody>
	</table>
	
		<script src="/resources/js/completeditem.js"></script>


	<script>
	
	$(document).ready(function(){
		
		let getmeso = $(".getmeso")
		
		
		
		 getmeso.on("click" , function() {
			 
			 const price = $(this).data("price");
			 const count = $(this).data("count");
			 const fkexitem_no = $(this).data("fkexitem_no");
			  let data = ({
				
				  price : price,
				  count : count,
				  fkexitem_no:fkexitem_no
				   
			  });
			  
			  compleitem.collectmeso(data, function(result){
			        alert("메소를 성공적으로 회수하였습니다.");
			        location.reload(true);
			 },
			 function(error){
				  
				  alert("메소 회수 실패");
			  });
			  
		
		});
		
		
		  let collectAllBtn = $(".allgetmeso");
	
		  
		  collectAllBtn.on("click" , function() {
			
			  let dataList = [];
			    $(".getmeso").each(function () {
			        let data = {
			            price: $(this).data("price"),
			            count: $(this).data("count"),
			            fkexitem_no: $(this).data("fkexitem_no"),
			        };
			        dataList.push(data);
			    });
			  
			    compleitem.collectAllMeso(dataList, function (result) {
			        alert("모든 메소를 성공적으로 회수하였습니다.");
			        location.reload(true);
			    }, 
			    function (error) {
			        alert("메소 회수 실패");
			    });
			  
		  });
		  
		  
		
		
	});
	
	
	</script>


</body>


</html>
