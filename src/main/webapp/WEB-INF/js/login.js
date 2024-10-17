$(function(){
	//아이디가 있는지 없는지 체크
	
	//로그인
	$('#loginBtn').click(function(){
	$('#userIdDiv').empty();
	$('#passwordDiv').empty();
	
	const userId = $('#userId').val();
	const password = $('#password').val();

	if($('#userId').val() =='')
		$('#userIdDiv').html('아이디 입력');
	else if($('#password').val() =='')
		$('#passwordDiv').html('비밀번호 입력');
	else
		$.ajax({
			type: 'post',
			url:'/carrot/user/login',
			data: { userId : userId, password:password },
			success: function(data){
				let result = data.trim();
				
				if(result == 'success'){
					alert('로그인 완료');
					window.location.href = '/carrot/';
				} else{
					alert('아이디나 비밀번호가 틀렸습니다');
					}
			},
			error:function(e) {
				console.log(e);
			}		
		});
	});
	
	//회원가입 이동
	$('#registerBtn').click(function(){
		location.href = '/carrot/user/register'
	});
});