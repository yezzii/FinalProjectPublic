function random_img(){
	num = Math.floor(Math.random()*3) + 1;
	$("#randomImage").attr("src", "./resources/images/main_ad/main_ad_"+num+".png");
	
	if(num == 1) {
		$("#randomImage").attr("onclick", "location.href='member_main.do'");
	} else if(num == 2) {
		$("#randomImage").attr("onclick", "location.href='play_list.go'");
	} else {
		$("#randomImage").attr("onclick", "location.href='play_out_random.do'");
	}
}

