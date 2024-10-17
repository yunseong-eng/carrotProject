$(function() {
    //아이디 중복체크
    $('#userId').focusout(function() {
        $('#userIdDiv').empty();
        
        userId = $('#userId').val();
        
        if (userId == '') {
            $('#userIdDiv').html("<span style='color: red;'>먼저 아이디 입력</span>");
        } else {
            $.ajax({
                type: 'post',
                url: '/carrot/user/checkUserId',
                data: { 'userId': userId },
                dataType: 'text',
                success: function(data) {
                    if (data == 'exist') {
                        $('#userIdDiv').html('사용 불가능');
                        $('#userIdDiv').css('color', 'red');
                    }
                    else {
                        $('#userIdDiv').html('사용 가능');
                        $('#userIdDiv').css('color', 'blue');
                    }
                },
                error: function(e) {
                    console.log(e);
                }
            });
        }
    });
    
    //등록
    $('#registerBtn').click(function(){
    
    	$('#nameDiv').empty();
    	$('#userIdDiv').empty();
    	$('#passwordDiv').empty();
    	
    	if($('#name').val() == '')
    		$('#nameDiv').html('이름 입력');
    	else if($('#userId').val() == '')
    		$('#userIdDiv').html('아이디 입력');
    	else if($('#password').val() == '')
    		$('#passwordDiv').html('비밀번호 입력');
    	else
    		$.ajax({
    			type: 'post',
                url: '/carrot/user/register',
                data: $('#registerForm').serialize(),
                success: function() {
                    alert('회원가입 완료');
                    location.href = '/carrot/';
                },
                error: function(e) {
                    console.log(e);
                }
    		});
    });
});
