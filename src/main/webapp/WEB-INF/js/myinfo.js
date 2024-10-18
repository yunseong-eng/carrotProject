
//유효성 검사 함수
function myinfo() {
	let isValid = true;

	$('#nameDiv').empty();
	$('#userIdDiv').empty();
	$('#passwordDiv').empty();
	$('#nowpwdDiv').empty();
	$('#repwdDiv').empty()

	if ($('#name').val().trim() === '') {
		$('#nameDiv').html('이름').css('color','red');
		$('#name').focus();
		return false;
	}

	if ($('#userId').val().trim() === '') {
		$('#userIdDiv').html('아이디').css('color','red');
		$('#userId').focus();
		return false;
	}

	if ($('#nowpwd').val().trim() === '') {
		$('#nowpwdDiv').html('현재 비밀번호를 입력해주세요').css('color', 'red');
		$('#nowpwd').focus();
		return false;
	} else {
		if ($('#password').val().trim() === '') {
			$('#passwordDiv').html('수정할 비밀번호를 입력해주세요').css('color', 'red');
			$('#password').focus();
			return false;
		}
		if ($('#password').val() !== $('#repwd').val()) {
			$('#repwdDiv').html('수정할 비밀번호 췤!').css('color', 'red');
			$('#repwd').focus();
			return false;
		}
	}

	return isValid;
}

// 포커스 아웃 이벤트 추가
$('#name').blur(function() {
	if ($(this).val().trim() === '') {
		$('#nameDiv').html('이름을 입력해주세요.').css('color', 'red');
	} else {
		$('#nameDiv').empty(); // 올바르게 입력된 경우 메시지 제거
	}
});

$('#userId').blur(function() {
	if ($(this).val().trim() === '') {
		$('#userIdDiv').html('아이디를 입력해주세요.').css('color', 'red');
	} else {
		$('#userIdDiv').empty();
	}
});

$('#nowpwd').blur(function() {
	if ($(this).val().trim() === '') {
		$('#nowpwdDiv').html('현재 비밀번호를 입력해주세요.').css('color', 'red');
	} else {
		$('#nowpwdDiv').empty();
	}
});

$('#password').blur(function() {
	if ($(this).val().trim() === '') {
		$('#passwordDiv').html('수정할 비밀번호를 입력해주세요.').css('color', 'red');
	} else if ($('#password').val() !== $('#repwd').val() && $('#repwd').val() !== '') {
		$('#passwordDiv').html('비밀번호가 일치하지 않습니다.').css('color', 'red');
	} else {
		$('#passwordDiv').empty();
		$('#repwdDiv').empty();
	}
});

$('#repwd').blur(function() {
	if ($(this).val().trim() === '') {
		$('#repwdDiv').html('수정할 비밀번호를 다시 입력해주세요.').css('color', 'red');
	} else if ($('#password').val() !== $('#repwd').val()) {
		$('#repwdDiv').html('비밀번호가 일치하지 않습니다.').css('color', 'red');
	} else {
		$('#passwordDiv').empty();
		$('#repwdDiv').empty();
	}
});

// 숫자만 입력받기
$('#tel2, #tel3').on('input', function() {
    this.value = this.value.replace(/[^0-9]/g, ''); // 숫자가 아닌 문자는 제거
});
// 입력값에서 특수문자 제거
$('#name, #userId, #tel2, #tel3, #email1, #email2').on('input', function() {
    this.value = this.value.replace(/[^0-9a-zA-Z가-힣]/g, '');
});



//회원정보수정 버튼 클릭시
$('#myinfoBtn').click(function() {
	if (myinfo()) {
		let nowpwd = $('#nowpwd').val();
		let userId = $('#userId').val();

		//현재 비밀번호 확인용 AJax
		$.ajax({
			type: 'post',
			url: '/carrot/user/checkUserPwd',
			data: { 'userId': userId, 'nowpwd': nowpwd },
			success: function(response) {
				if (response == 'exist') { //비밀번호가 일치한다면
					let serializedData = $('#myinfoForm').serialize();

					//회원정보 수정 AJax
					$.ajax({
						type: 'post',
						url: '/carrot/user/update',
						data: serializedData,
						contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
						success: function(response) {
							console.log('Response:', response);
							
							// myinfoForm 데이터 출력
        					let formDataArray = $('#myinfoForm').serializeArray();
					        formDataArray.forEach(function(item) {
					            console.log(item.name + ': ' + item.value);
					        });
					        
							alert('회원정보 수정완료');
							location.href = '/carrot';
						},
						error: function(e) {
							console.log(e);
						}
					});
				} else {
					$('#nowpwdDiv').html('현재 비밀번호가 일치하지 않습니다.').css('color', 'red');
					$('#nowpwd').focus();
				}
			}, error: function(e) {
				console.log('비밀번호 확인 중 오류 발생 :', e);
			}
		});
	}
});

//이메일
$('#emailSelect').change(function() {
	$('#email2').val($('#emailSelect').val());
});

//우편번호 - Daum API
function checkPost() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('zipcode').value = data.zonecode;
			document.getElementById("addr1").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("addr2").focus();
		}
	}).open();
}


//회원탈퇴 이동
$('#deleteBtn').click(function(){
	location.href = '/carrot/user/delete'
});