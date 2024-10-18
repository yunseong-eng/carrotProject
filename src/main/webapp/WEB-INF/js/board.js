function toggleShippingFeeInput() {
    const shippingFeeSelect = document.getElementById("shippingFeeSelect");
    const shippingFeeInput = document.getElementById("shippingFee");

    if (shippingFeeSelect.value === "O") {
        shippingFeeInput.disabled = false; // 필드를 활성화
        shippingFeeInput.placeholder = "배송비를 입력하세요";
        shippingFeeInput.value = "";  // 선택 시 초기화
        shippingFeeInput.required = true; // 필수 입력 필드 설정
    } else {
        shippingFeeInput.disabled = true; // 필드를 비활성화
        shippingFeeInput.value = "무료";  // 선택 시 "무료"로 자동 설정
        shippingFeeInput.placeholder = "배송비 없음";
        shippingFeeInput.required = false; // 필수 입력 해제
    }
}

function validateForm() {
    const title = document.getElementById("title");
    const category = document.getElementById("category");
    const postType = document.getElementById("postType");
    const content = document.getElementById("content");
    const shippingFeeSelect = document.getElementById("shippingFeeSelect");
    const shippingFee = document.getElementById("shippingFee");

    // 이전 오류 메시지 초기화
    clearErrorMessages();

    // 제목 입력 확인
    if (title.value.trim() === "") {
        displayErrorMessage(title, "제목을 입력하세요.");
        return false;
    }

    // 카테고리 선택 확인
    if (category.value === "") {
        displayErrorMessage(category, "카테고리를 선택하세요.");
        return false;
    }

    // 게시글 유형 선택 확인
    if (postType.value === "") {
        displayErrorMessage(postType, "게시글 유형을 선택하세요.");
        return false;
    }

    // 내용 입력 확인
    if (content.value.trim() === "") {
        displayErrorMessage(content, "내용을 입력하세요.");
        return false;
    }

     // 배송비가 "O"일 경우 금액 입력 확인
    if (shippingFeeSelect.value === "O" && (shippingFee.value.trim() === "" || shippingFee.value === "무료")) {
        displayErrorMessage(shippingFee, "배송비를 입력하세요.");
        return false;
    }
   
    
	// 유효성 검사 통과 후 폼 제출 시 disabled 해제
    shippingFee.disabled = false;
	
	
    return true; // 모든 검사를 통과한 경우 true 반환
}

// 오류 메시지를 초기화하는 함수
function clearErrorMessages() {
    const errorMessages = document.querySelectorAll(".error-message");
    errorMessages.forEach(function (error) {
        error.remove();
    });
}

// 오류 메시지를 표시하는 함수
function displayErrorMessage(element, message) {
    const error = document.createElement("div");
    error.className = "error-message";
    error.style.color = "red"; // 오류 메시지 색상 설정
    error.style.fontSize = "12px"; // 오류 메시지 폰트 크기 설정
    error.innerText = message;
    element.parentNode.appendChild(error); // 오류 메시지를 해당 필드 아래에 추가

    // 오류가 발생한 필드에 포커스 설정
    element.focus();
}

// 페이지 로드 시 호출되는 함수
window.onload = function() {
    toggleShippingFeeInput(); // 페이지 로드 시 기본값 설정

    // 폼 제출 시 유효성 검사 실행
    const form = document.querySelector("form");
    form.addEventListener('submit', function(event) {
        if (!validateForm()) {
            event.preventDefault(); // 유효성 검사 실패 시 폼 제출을 막음
        }
    });
};

