// (1) 회원정보 수정
function update(userId, event) {
	
	event.preventDefault();
	
	let data = $("#profileUpdate").serialize();
	
	$.ajax({
		type: "put",
		url: `/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json"
	}).done(res=>{// httpstatus 상태 코드가 200번대일때
		console.log("성공", res);
		location.href=`/user/${userId}`
	}).fail(error=>{// httpstatus 상태 코드가 200번대가 아닐때
		if (error.data == null){
			alert(error.responseJSON.message);
		}else{
			alert(JSON.stringify(error.responseJSON.data));
			console.log("실패", error.responseJSON.data);	
		}
		
	})
}