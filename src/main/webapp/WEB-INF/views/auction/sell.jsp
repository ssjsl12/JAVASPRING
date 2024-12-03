<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%@ include file="../auction/Header.jsp"%>

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<style>
.grid {
	display: flex;
}

.grid-container {
	display: grid;
	grid-template-columns: repeat(12, 1fr); /* 12 columns */
	grid-template-rows: repeat(8, 1fr); /* 8 rows */
	gap: 5px; /* Gap between cells */
	width: 60%;
	height: 500;
	aspect-ratio: 12/8; /* Maintain 12:8 aspect ratio */
	background-color: #fff;
	border: 2px solid #ccc;
}

.grid-item {
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: #d1e7dd;
	border: 1px solid #b0c4b1;
	font-size: 14px;
	color: #333;
}

.grid-exchangeitem {
	background-color: #fff;
	border: 2px solid #ccc;
	height: 30%;
}

.item-detail {
	width: 40%;
	border: 2px solid #ccc;
}
</style>

<html>
<head>
<meta charset="EUC-KR">

</head>
<body>

	<div class="grid">
		<div class="grid-container">
			<!-- Create 12 x 8 = 96 cells -->
			<c:forEach var="i" items="${list}" begin="0" end="96">
				<c:if test="${i.count > 0}">
					<div class="grid-item"
						onclick="showItemDetail('${i.url}', '${i.count}', '${i.name}', '${i.fkuser_no}', '${i.fkinfo_no}')">
						<img src="<spring:url value='/resources${i.url}'/>" width="50px"
							height="50px">
						<div>${i.count}</div>
					</div>
				</c:if>
			</c:forEach>
			<!-- Continue up to 96 items -->
		</div>

		<div class="item-detail" id="item-detail">
			<h3>Select an item to see details</h3>
			<p>Click on any item to view its details here.</p>
		</div>
	</div>



	<table class="table table-hover">
		<thead>
			<tr>

				<th>Item Name</th>
				<th>Price</th>
				<th>Count</th>

			</tr>
		</thead>
	
		<tbody>
		
			<tbody>
					
			<c:forEach var="i" items="${exlist}">
			<c:if test="${i.count > 0}">
			<tr>
				<td><img src="<spring:url value='/resources${i.url}'/>" width="50px"
					height="50px"></td>	

				<td>아이템 가격 : ${i.price}</td>
				<td>아이템 개수 : ${i.count}</td>
				<td>	<button type="button" class="cancleitem"
					data-userid="${i.fkuser_no}" data-itemid="${i.fkinfo_no}"
					data-itemno="${i.exitem_no}">거래 취소</button></td>
			</tr>
			</c:if>
			</c:forEach>	
				
					
		</tbody>
		
		</tbody>
	
	</table>

	<script src="/resources/js/market.js"></script>

	<script>
		
	$(document).ready(function(){
		
		let cancleBtn = $(".cancleitem")
		
		 cancleBtn.on("click" , function() {
			 
			 const userId = $(this).data("userid"); 
			 const itemid = $(this).data("itemid");
			 const itemNo = $(this).data("itemno");
	
			  let data = ({
				
				  fkuser_no : userId,
				  fkinfo_no : itemid,
				  exitem_no : itemNo
				    
			  });
			  
			 
			 marketService.cancleItem(data, function(result){
			        alert("아이템을 성공적으로 거래에서 취소하였습니다.");
			        location.reload(true);
			 },
			 function(error){
				  
				  alert("아이템 거래취소 실패");
			  });
			 
			 
		
	});
	})
	
		const itemDetail = $(".item-detail");
			
		 function showItemDetail(url, count, name , userid , infoid) {
	        
			 		let originCount = count;
			 
		      	    let str = "<h3>Name: "+(name)+"</h3>";
			        str += "<img src=<spring:url value='/resources" +url +"'/>>"
			        
			        str += "<div>";
			        str += "<label for='countInput'>Enter count : </label>";
			        str += "<input type='number' id='countInput' placeholder='Enter count' min='0' step='1' />";
			        str += "<div>";
			        str += "<div>";
			        str += "<label for='priceInput'>Enter Price : </label>";
			        str += "<input type='number' id='priceInput' placeholder='Enter price' min='0' step='1' />";
			        str += "<div>";
					str += "<br><button id= 'submitPrice'>거래소 올리기</button>";
				   
			        itemDetail.html(str); 
      

			        $('#countInput').on('input', function() {
			            const value = $(this).val();
			            if (!/^\d*$/.test(value)) {  // 숫자가 아니면 값 제거
			                $(this).val(value.replace(/[^\d]/g, ""));
			            }
			        });
			        
			        // 가격 입력 필드에서 숫자만 허용
			        $('#priceInput').on('input', function() {
			            const value = $(this).val();
			            if (!/^\d*$/.test(value)) {  // 숫자가 아니면 값 제거
			                $(this).val(value.replace(/[^\d]/g, ""));
			            }
			        });
			        
			        $('#submitPrice').click(function() {
			            const price = $('#priceInput').val();  // 입력된 가격 값 가져오기
			            const count = $('#countInput').val();
			            if (price === "" || parseInt(price) < 0) {
			                alert("Please enter a valid price.");  // 가격이 비어 있거나 음수인 경우 경고	            
			            } 
			            
			            if(count > 0 && count <= originCount)
			            {
			            	
			                putItemMarket(price , count , userid ,infoid);
			            	
			            }
			           
			            
			        });

			        
			    }
		 
		 
		 function putItemMarket(price, count, userid , infoid)
		 {
			console.log(price + " " + count + " " + userid , infoid); 
			
		    var data = ({
		        price: price,
		        count: count,
		        fkuser_no : userid,
		        fkinfo_no : infoid
		    });
			
			
		    
			  marketService.update(data, function(result){
			        alert("아이템을 성공적으로 거래에 올렸습니다");
			        location.reload(true);
			  },
			   
			  function(error){
				   alert("실패");
			   }); 
		    
		 }
			
	

    </script>


</body>
</html>