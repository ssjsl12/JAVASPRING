<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="../auction/Header.jsp"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<html>
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

<meta charset="EUC-KR">
<title>Insert title here</title>
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
				<th>Count</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<c:if test="${item.state != 1}">
					<tr class="clickable-row" data-name="${item.name}"
						data-price="${item.price}" data-count="${item.count}" data-no ="${item.exitem_no}"
						data-fkuser="${item.fkuser_no}"
						data-img="<spring:url value='/resources${item.url}'/>">
						<td><img src="<spring:url value='/resources${item.url}' />"
							width="50" height="50"> ${item.name}</td>
						<td>${item.price}</td>
						<td>${item.count}</td>
						<td>${item.exDate}</td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>

	<!-- Popup Modal -->
	<div id="itemPopup"
		style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background-color: white; border: 1px solid #ccc; padding: 20px; z-index: 1000; width: 300px;">

		<h3 id="popupName"></h3>
		<div class="popup">
			<img id="popupImg" src="" alt="Item Image" width="100" height="100">

			<div class="popup-details">		
			
				<span id = "popupno"></span>
				<span id = "popupfkuser_no"></span>
				
				<div>
					<b>Price:</b> <span id="popupPrice"></span>
				</div>
				<div>
					<b>Count:</b> <input type="number" id="popupCount"  value="1" data-current-count="1" data-item-no="1"  oninput="validateCount()" />
				</div>
			</div>

		</div>
		<button class="buy-btn">구입</button>
		<button class="wish-btn" style ="margin-top: 10px">찜하기</button>
		<button id="closePopup" style="margin-top: 10px">Close</button>
	</div>

	<!-- Background Overlay -->
	<div id="popupOverlay"
		style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); z-index: 999;"></div>


<script src="/resources/js/exchangeItem.js"></script>

	<script>
	 document.addEventListener("DOMContentLoaded", function () {
		    const popup = document.getElementById("itemPopup");
		    const overlay = document.getElementById("popupOverlay");
		    const closePopup = document.getElementById("closePopup");
			
		    // Add click event to each tr with the class "clickable-row"
		    document.querySelectorAll(".clickable-row").forEach(function (row) {
		        row.addEventListener("click", function () {
		            // Set popup values using row attributes
		            document.getElementById("popupName").textContent = this.getAttribute("data-name");
		            document.getElementById("popupno").value = this.getAttribute("data-no");
		            document.getElementById("popupPrice").textContent = this.getAttribute("data-price");
		            document.getElementById("popupCount").textContent = this.getAttribute("data-count");
		            document.getElementById("popupImg").src = this.getAttribute("data-img");
		            document.getElementById("popupfkuser_no").value = this.getAttribute("data-fkuser");
		            document.getElementById('popupCount').value = 1;
		            // Show popup and overlay
		            popup.style.display = "block";
		            overlay.style.display = "block";
		        });
		    });

		    closePopup.addEventListener("click", function () {
		        popup.style.display = "none";
		        overlay.style.display = "none";
		    });

		    overlay.addEventListener("click", function () {
		        popup.style.display = "none";
		        overlay.style.display = "none";
		    });
		    
		    
		});

	 function validateCount() {
		    // 현재 선택한 아이템의 정보
		    const currentItem = {
		        count: document.getElementById("popupCount").textContent
		    };

		    const newCount = parseInt(document.getElementById("popupCount").value);

		    // 현재 개수를 초과하거나 음수값일 경우
		    if (newCount > currentItem.count) {
		        alert(`최대 개수는 ${currentItem.count}입니다.`);
		        document.getElementById("popupCount").value = currentItem.count; // 값을 현재 개수로 리셋
		    } else if (newCount < 1) {
		        alert("개수는 음수일 수 없습니다.");
		        document.getElementById("popupCount").value = 1; // 음수일 경우 값을 0으로 리셋
		    }
		}
	 
	 document.querySelectorAll('.wish-btn').forEach(function(button) {
		    button.addEventListener('click', function() {
		        // 클릭한 버튼의 데이터 속성 값 가져오기
		        var itemNo =  document.getElementById("popupno").value;
		        // 아이템 구입 요청
		         wishItem(itemNo); 
		    }); 
	 });
	 
	 document.querySelectorAll('.buy-btn').forEach(function(button) {
		    button.addEventListener('click', function() {
		        // 클릭한 버튼의 데이터 속성 값 가져오기
		        var itemNo =  document.getElementById("popupno").value;
		        var itemPrice = document.getElementById("popupPrice").textContent;
		        var itemCount = document.getElementById('popupCount').value;
		        var itemfkuser = document.getElementById('popupfkuser_no').value;
		        
		        // 아이템 구입 요청
		         purchaseItem(itemNo, itemCount ,itemfkuser); 
		    }); 
	 });
	 
	 function purchaseItem(itemNo, itemCount ,itemfkuser) {

		 
		    // 전송할 데이터 (구입하려는 아이템 정보)
		    var data = ({
		        exitem_no: itemNo,
		        count: itemCount,
		        fkuser_no : itemfkuser
		    });
		    
			  exchangeItemService.update(data, function(result){
			        alert("아이템이 성공적으로 구매되었습니다!");
			        location.reload(true);
			  },
			   
			  function(error){
				   alert("아이템 구매 실패");
			   });
	 };
		   
		function wishItem(itemNo)
		{

			var data = ({	
				exitem_no : itemNo,
			});
			
			
			  exchangeItemService.wish(data, function(result){
			        alert("찜 성공");
			        location.reload(true);
			  },
			   
			  function(error){
					alert("찜 실패");  
			   });
			
		
		};

		 
	 
	 
	 
    </script>

</body>
</html>


