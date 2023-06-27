// Summernote 
$(document).ready(function () {
	$('#community_content').summernote({
		toolbar: [
			['fontname', ['fontname']],
			['fontsize', ['fontsize']],
			['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			['color', ['forecolor','color']],
			['table', ['table']],
			['para', ['ul', 'ol', 'paragraph']],
			['height', ['height']],
			['view', ['fullscreen', 'help']]
		],
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
		placeholder: 'TODO 여러분의 활동 후기 또는 자유로운 이야기를 들려주세요!',
	    height: 250,
	    callbacks: {
	        onInit: function (c) {
	            c.editable.html('');
	        }
	    }
	});
});

$(document).ready(function () {
	$('#qna_content').summernote({
		toolbar: [
			['fontname', ['fontname']],
			['fontsize', ['fontsize']],
			['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			['color', ['forecolor','color']],
			['table', ['table']],
			['para', ['ul', 'ol', 'paragraph']],
			['height', ['height']],
			['view', ['fullscreen', 'help']]
		],
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
		placeholder: '사이트 이용시 불편하셨던 점 또는 문의사항을 작성해주세요.',
	    height: 250,
	    callbacks: {
	        onInit: function (c) {
	            c.editable.html('');
	        }
	    }
	});
});