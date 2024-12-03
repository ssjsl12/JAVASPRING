console.log("Market Module..........");

let marketService = (function() {
    
    function update(data, callback, error) {
                        
		let url = '/auction/new?price='+data["price"]+'&count='
		+data["count"]+'&fkuser_no='+data['fkuser_no']
		+'&fkinfo_no='+data['fkinfo_no'];
        
              
        // AJAX 요청을 사용하여 서버에 데이터 전송
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
    };//update end
    
    function cancleItem(data ,callback ,error){
    	
    	let url = '/auction/cancle?fkuser_no='+data['fkuser_no']
		+'&fkinfo_no='+data['fkinfo_no'] +'&exitem_no='+data['exitem_no'];
    	
    	
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
    
    
    }


    // 모듈 반환
    return {
        update: update , cancleItem : cancleItem
    };
})();