function toggleShippingFeeInput() {
    const shippingFeeSelect = document.getElementById("shippingFeeSelect");
    const shippingFeeInput = document.getElementById("shippingFee");

    if (shippingFeeSelect.value === "O") {
        shippingFeeInput.disabled = false;
        shippingFeeInput.placeholder = "배송비를 입력하세요";
        shippingFeeInput.value = "";  // O 선택 시 입력 필드를 초기화
        shippingFeeInput.required = true;
    } else {
        shippingFeeInput.disabled = true;
        shippingFeeInput.value = "무료";  // X 선택 시 "무료"로 설정
        shippingFeeInput.placeholder = "배송비 없음";
        shippingFeeInput.required = false;
    }
}

function previewImage(event) {
    const file = event.target.files[0];
    const reader = new FileReader();
    const imagePreview = document.getElementById("imagePreview");

    reader.onload = function(e) {
        imagePreview.src = e.target.result;
        imagePreview.style.display = 'block';
    };

    if (file) {
        reader.readAsDataURL(file);
    } else {
        imagePreview.style.display = 'none';
    }
}

// 유효성 검사 함수
function validateForm() {
    // 입력 필드
    const title = document.getElementById("title");
    const category = document.getElementById("category");
    const postType = document.getElementById("postType");
    const content = document.getElementById("content");
    const shippingFeeSelect = document.getElementById("shippingFeeSelect");
    const shippingFee = document.getElementById("shippingFee");

    // 오류 메시지 초기화
    clearErrorMessages();

    // 순차적으로 검사를 수행하여 첫 번째로 발생한 오류만 처리
    if (title.value.trim() === "") {
        displayErrorMessage(title, "제목을 입력하세요.");
        return false;
    }

    if (category.value === "") {
        displayErrorMessage(category, "카테고리를 선택하세요.");
        return false;
    }

    if (postType.value === "") {
        displayErrorMessage(postType, "게시글 유형을 선택하세요.");
        return false;
    }

    if (content.value.trim() === "") {
        displayErrorMessage(content, "내용을 입력하세요.");
        return false;
    }

    if (shippingFeeSelect.value === "O" && shippingFee.value.trim() === "") {
        displayErrorMessage(shippingFee, "배송비를 입력하세요.");
        return false;
    }

    // 모든 검사가 통과되면 작성 완료 알림
    alert("작성 완료되었습니다.");
    return true;
}

// 오류 메시지를 표시하는 함수
function displayErrorMessage(element, message) {
    const error = document.createElement("div");
    error.className = "error-message";
    error.style.color = "red";
    error.style.fontSize = "12px";
    error.innerText = message;
    element.parentNode.appendChild(error);

    // 첫 번째 오류가 발생한 필드로 포커스 이동
    element.focus();
}

// 오류 메시지를 초기화하는 함수
function clearErrorMessages() {
    const errorMessages = document.querySelectorAll(".error-message");
    errorMessages.forEach(function (error) {
        error.remove();
    });
}

window.onload = function() {
    toggleShippingFeeInput(); // 페이지 로드 시 기본값 설정

    // 폼 제출 시 유효성 검사 실행
    const form = document.querySelector("form");
    form.onsubmit = function(event) {
        if (!validateForm()) {
            event.preventDefault(); // 유효성 검사 실패 시 폼 제출을 막음
        }
    };
};
