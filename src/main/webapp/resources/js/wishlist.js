console.log("wishlist Module..........");

let wishService = (function() {
    
    
    function cancleItem(data ,callback ,error){
    	
    	let url = '/auction/canclewish?fkexitem_no='+data['fkexitem_no'];
    	
    	
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
       cancleItem : cancleItem
    };
})();