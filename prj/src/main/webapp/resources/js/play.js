rangeInput = document.querySelectorAll(".range-input input");
priceInput = document.querySelectorAll(".price-input input")
progress = document.querySelector(".slider .progress");

rangeInput1 = document.querySelectorAll(".range-input1 input");
priceInput1 = document.querySelectorAll(".price-input1 input")
progress1 = document.querySelector(".slider1 .progress1");

let priceGap = 50000;
let priceGap1 = 1000;


priceInput.forEach(input =>{
	input.addEventListener("input",e =>{
		//getting two inputs value and parsing them to number
		let minVal = parseInt(priceInput[0].value),
			maxVal = parseInt(priceInput[1].value);

			if(maxVal - minVal >= priceGap && maxVal <=800000){
				if(e.target.className ==="input-min"){ //if active input is min input
					rangeInput[0].value = minVal;
					progress.style.left = (minVal/rangeInput[0].max)*100+"%";		
				}else{
					rangeInput[1].value = maxVal;
					progress.style.right = 100-(maxVal/rangeInput[1].max)*100+"%";		
				}
			}
		});
	});
	
	rangeInput.forEach(input =>{
	input.addEventListener("input",e =>{
		let minVal = parseInt(rangeInput[0].value),
			maxVal = parseInt(rangeInput[1].value);

			if(maxVal - minVal < priceGap){
				if(e.target.className ==="range-min"){
					rangeInput[0].value = maxVal-priceGap;
				}else{
					rangeInput[1].value = minVal+priceGap;
				}
				rangeInput[0].value = maxVal - priceGap;
			}else{
				priceInput[0].value = minVal;
				priceInput[1].value = maxVal;
				progress.style.left = (minVal/rangeInput[0].max)*100+"%";			
				progress.style.right = 100-(maxVal/rangeInput[1].max)*100+"%";			
			}
	});
});



priceInput1.forEach(input =>{
	input.addEventListener("input",e =>{
		//getting two inputs value and parsing them to number
		let minVal1 = parseInt(priceInput1[0].value),
			maxVal1 = parseInt(priceInput1[1].value);

			if(maxVal1 - minVal1 >= priceGap1 && maxVal1 <=60000){
				if(e.target.className ==="input-min1"){ //if active input is min input
					rangeInput1[0].value = minVal1;
					progress1.style.left = (minVal1/rangeInput1[0].max)*100+"%";		
				}else{
					rangeInput1[1].value = maxVal1;
					progress1.style.right = 100-(maxVal1/rangeInput1[1].max)*100+"%";		
				}
			}
		});
	});
	
rangeInput1.forEach(input =>{
	input.addEventListener("input",e =>{
		let minVal1 = parseInt(rangeInput1[0].value),
			maxVal1 = parseInt(rangeInput1[1].value);

			if(maxVal1 - minVal1 < priceGap1){
				if(e.target.className ==="range-min1"){
					rangeInput1[0].value = maxVal1-priceGap1;
				}else{
					rangeInput1[1].value = minVal1+priceGap1;
				}
				rangeInput1[0].value = maxVal1 - priceGap1;
			}else{
				priceInput1[0].value = minVal1;
				priceInput1[1].value = maxVal1;
				progress1.style.left = (minVal1/rangeInput1[0].max)*100+"%";			
				progress1.style.right = 100-(maxVal1/rangeInput1[1].max)*100+"%";			
			}
	});
});


 


/*DOMContentLoaded => HTML문서가 로드되고 해석 될 때 발생
  HTML문서의 모든 요소가 로드되고 DOM이 구성된 후 실행될 함수를 등록 할수 있다.
*/
document.addEventListener("DOMContentLoaded", function() {
        var travelCheckbox = document.getElementById("out2");
        var distanceInput = document.getElementById("distance-input");

        travelCheckbox.addEventListener("change", function() {
            if (this.checked) {
				/*투명도 0으로 조절*/
                distanceInput.style.opacity = "0";
                distanceInput.style.display = "block";
                setTimeout(function() {
					/*. setTimeout을 사용하여 10밀리초 후에 투명도를 1로 설정하여 부드럽게 나타나게 됨.*/
                    distanceInput.style.opacity = "1";
                }, 10);
            } else {
				/*체크박스가 체크 해제되면, distanceInput의 투명도를 0으로 설정하고 setTimeout을 
				사용하여 500밀리초 후에 display 속성을 "none"으로 변경하여 요소를 사라지게됨.*/
                distanceInput.style.opacity = "0";
                setTimeout(function() {
                    distanceInput.style.display = "none";
                }, 500);
            }
        });
    });

