    // 버튼 클릭 이벤트 핸들러
    function handleClick() {
        // 클릭된 상태인 요소면 함수 종료
        if (this.disabled) {
            return;
        }

        // 요소 비활성화
        this.disabled = true;

        // 요소 동작 수행
        // 여기에 클릭 시 수행할 동작을 추가하세요.

        // 일정 시간 후에 요소 활성화
        var element = this;
        setTimeout(function() {
            element.disabled = false;
        }, 1000); // 1초 후에 요소 활성화 (원하는 시간으로 수정 가능)
    }

    // 클래스가 'a'인 모든 요소에 이벤트 리스너를 추가
    var elements = document.querySelectorAll('.A');
    elements.forEach(function(element) {
        element.addEventListener('click', handleClick);
    });
