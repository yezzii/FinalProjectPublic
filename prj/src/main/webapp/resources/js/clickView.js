document.querySelector('.toggle-button').addEventListener('click', function(event) {
  let content = document.querySelector('.hidden-content');
  let computedStyle = window.getComputedStyle(content);

//

  if (confirm("5 포인트를 사용하여 랜덤 추천을 받으시겠습니까?")) {
    if (computedStyle.display === 'none') {
      fetch('./RandomClickPoint.do', { //fetch()는 JavaScript에서 네트워크 요청을 보내는 기능을 제공하는 함수입니다. 
      //fetch() 함수를 사용하여 서버로 HTTP 요청을 보낼 수 있으며, 서버의 응답을 받아올 수도 있습니다.
      //fetch() 함수는 Promise를 반환하므로, 비동기적으로 요청을 처리하고 응답을 처리할 수 있습니다. 
      //일반적으로 .then() 메서드를 사용하여 성공적인 응답 처리와 .catch() 메서드를 사용하여 오류 처리를 합니다.
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        },
      })
      .then(response => response.json())
      .then(data => {
        if (data.result === 'InsufficientPoints') {
          alert('포인트가 부족합니다. 잔여 포인트 : ' + data.point);
        } else {
          content.style.display = 'block';
          document.querySelector('.toggle-button').style.display = 'none';
          alert('포인트를 사용하여, 랜덤 추첨을 보여줍니다.');
        }
      })
      .catch(error => {
        console.error('요청 처리 중 오류가 발생했습니다.', error);
      });
    } else {
      content.style.display = 'none';
    }
  }
});
