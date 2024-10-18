$(document).ready(function() {
    $('#withdrawBtn').prop('disabled', true);

    // 체크박스 상태에 따라 버튼 활성화/비활성화
    $('#withdrawbox').change(function() {
        console.log("Checkbox 상태 : ", this.checked); // 체크박스 상태
        $('#withdrawBtn').prop('disabled', !this.checked);
    });
    
    //포커스 아웃 이벤트 추가
    $('#nowpwd').blur(function() {
	if ($(this).val().trim() === '') {
		$('#nowpwdDiv').html('비밀번호를 입력해주세요.').css('color', 'red');
	} else {
		$('#nowpwdDiv').empty();
		}
	});

    $('#withdrawBtn').click(function() {
        // 비밀번호 입력 확인
        if ($('#nowpwd').val().trim() === '') {
            $('#nowpwdDiv').html('비밀번호를 입력해주세요').css('color', 'red');
            $('#nowpwd').focus();
            return; // 함수 종료
        }
        
		// 비밀번호 일치하는지 확인하는 로직
    	if($('#nowpwd').val() != $('#checkpwd').val()){
    		$('#nowpwdDiv').html('비밀번호가 일치하지 않습니다').css('color', 'red');
    		$('#nowpwd').focus();
    		return;
    	}

        // 체크박스가 선택되지 않은 경우 경고 메시지
        if (!$('#withdrawbox').is(':checked')) {
            alert('안내 사항에 동의해야 탈퇴할 수 있습니다.');
            return; // 함수 종료
        }

        // 확인 후 AJAX 호출
        if (confirm('그래도 탈퇴하시겠습니까?')) {
        	const userId = $('#userId').val();
			const nowpwd = $('#nowpwd').val();
        
            $.ajax({
            	type:'post',
                url: '/carrot/user/delete',
                data: {userId : userId, nowpwd:nowpwd },
                success: function(data) {
                	let result = data.trim();
                	
                    if (result == 'success') {
						alert("delete 회원탈퇴 완료")
                        location.href = '/carrot';
                    } else {
                        alert('오류: ' + data);
                    }
                },
                error: function(xhr, status, error) {
                    console.log('Error details:', xhr, status, error);
                    alert('서버와의 통신 중 오류가 발생했.');
                }
            });            
        }
    });
});