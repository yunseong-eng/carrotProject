$(function(){
	
	// 로그인 버튼 비활성화
    $('#loginBtn').prop('disabled', true);

    // 아이디와 비밀번호 입력을 체크해서 로그인 버튼을 활성화/비활성화
    $('#userId, #password').on('keyup', function() {
        const userId = $('#userId').val().trim();
        const password = $('#password').val().trim();
        
        if (userId !== '' && password !== '') {
            $('#loginBtn').prop('disabled', false); // 아이디, 비밀번호 모두 입력받으면 버튼 활성화
        } else {
            $('#loginBtn').prop('disabled', true);  // 하나라도 입력되지 않으면 비활성화
        }
    });
	
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
					//alert('로그인 완료');
					window.location.href = '/carrot/';
				} else{
					alert('아이디 또는 비밀번호가 틀립니다');
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