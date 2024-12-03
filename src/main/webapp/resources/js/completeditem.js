console.log("completeditem Module..........");

let compleitem = (function() {
    
    
    function collectmeso(data ,callback ,error){
    	
    	let url = '/auction/collectmeso?count='+data['count'] + '&price='+data['price']+'&fkexitem_no='+data['fkexitem_no'];
    	
    	
    	  $.ajax({
            type: 'post', // HTTP 메서드 (수정 작업에 적합)
            url: url,
            data: JSON.stringify(data), // 데이터를 JSON 문자열로 변환
            contentType: 'application/json; charset=utf-8', // 요청 헤더 설정
            success: function(result, status, xhr) {
                if (callback) {
                    callback(result); // 성공 시 콜백 호출
                };
            },
            error: function(xhr, status, err) {
                if (error) {
                    error(err); // 오류 시 콜백 호출
                } else {
                    console.error("Update failed: ", err); // 오류 디버깅
                };
            }
        });
    
    
    } // function end
    
    function collectAllMeso (dataList, callback ,error) {

    $.ajax({
        type: "POST",
        contentType: "application/json;charset=UTF-8",     
        url: "/auction/collectAllMeso",      
        data: JSON.stringify(dataList),
        dataType: 'json',
        
        success: function(result, status, xhr) {
                if (callback) {
                    callback(result); // 성공 시 콜백 호출
                };
            },
            error: function(xhr, status, err) {
                if (error) {
                	console.log(status);
                	console.log(err);
                	console.log(error);
                    error(err); // 오류 시 콜백 호출
                } else {
                    console.error("Update failed: ", err); // 오류 디버깅
                };
            }
    });
    
	};
    


    // 모듈 반환
    return {
       collectmeso : collectmeso , collectAllMeso : collectAllMeso
    };
})();