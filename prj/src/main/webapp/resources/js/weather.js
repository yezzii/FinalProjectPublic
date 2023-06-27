//$(fuction(){
let weatherIcon = {
  "01": "01-removebg-preview.png",
  "02": "02-removebg-preview.png",
  "03": "03-removebg-preview.png",
  "04": "04-removebg-preview.png",
  "09": "09-removebg-preview.png",
  10: "10-removebg-preview.png",
  11: "11-removebg-preview.png",
  13: "13-removebg-preview.png",
  50: "50-removebg-preview.png",
};

let temptemp;
let airair;
let rain;

// 날씨 api - 서울
//let weatherAPIURI =

//let airAPIURI =


$.ajax({
  url: weatherAPIURI,
  dataType: "json",
  type: "GET",
  async: "false",
  timeout: 60000,
  success: function (resp) {
    let $Icon = resp.weather[0].icon.substr(0, 2);
    let $weather_description = resp.weather[0].main;
    let $Temp = Math.floor(resp.main.temp - 273.15) + "º";
    temptemp = Math.floor(resp.main.temp - 273.15);

    let $humidity = "습도&nbsp;&nbsp;&nbsp;&nbsp;" + resp.main.humidity + " %";
    let $wind = "바람&nbsp;&nbsp;&nbsp;&nbsp;" + resp.wind.speed + " m/s";
    let $city = "서울";
    let $cloud = "구름&nbsp;&nbsp;&nbsp;&nbsp;" + resp.clouds.all + "%";
    let $temp_min =
      "최저 온도&nbsp;&nbsp;&nbsp;&nbsp;" +
      Math.floor(resp.main.temp_min - 273.15) +
      "º";
    let $temp_max =
      "최고 온도&nbsp;&nbsp;&nbsp;&nbsp;" +
      Math.floor(resp.main.temp_max - 273.15) +
      "º";

    let $precipitation = "강수량 : " + (resp.rain?.["1h"] || 0) + "mm"; // 강수량 정보 추가
    rain = resp.rain?.["1h"] || 0; // 강수량 정보 추가
    handleTempAir(temptemp, airair, rain);
    $("#popup-btn").prepend(
      "<img class='weather-img' src=./resources/assets/weather/" +
        weatherIcon[$Icon] +
        "/>"
    );

    $(".temp").text($Temp);
    $(".popup-temp").text($Temp);
    // 강수량 정보 추가;
    $(".precipitation").text($precipitation);
  },
});

$.ajax({
  url: airAPIURI,
  dataType: "json",
  type: "GET",
  async: "false",
  timeout: 60000,
  success: function (airResp) {
    let $pm25 = airResp.list[0].components.pm2_5 || 0; // 미세먼지(PM2.5) 값
    let $pm10 = airResp.list[0].components.pm10 || 0; // 미세먼지(PM10) 값
    let $pm25slice = $pm25.toString().split(".");
    let $airQuality = "미세먼지: " + $pm25slice[0]; 
    //+ "㎍/m³";
    airair = $pm25slice[0];
    handleTempAir(temptemp, airair, rain);
    // 미세먼지 정보 추가
    $(".air-quality").text($airQuality);

    if ($pm25 > 150) {
      $(".air-quality-ment").html("<span style='color:#ff5959;'>&nbsp;(매우나쁨)</span>");
    } else if ($pm25 > 80) {
      $(".air-quality-ment").html("<span style='color:#fd9b5a;'>&nbsp;(조금나쁨)</span>");
    } else if ($pm25 > 30) {
      $(".air-quality-ment").html("<span style='color:#00c73c;'>&nbsp;(좋아용)</span>");
    } else {
      $(".air-quality-ment").html("<span style='color:#32a1ff;'>&nbsp;(최고용)</span>");
    }
  },
});

function handleTempAir(temptemp, airair, rain) {
  if (rain === 0) {
    if (airair > 150) {
      $(".ment").text("미세먼지 주의!");
    } else {
      if (temptemp > 20) {
        $(".ment").text("덥다 나가지마!");
      } else if (10 < temptemp < 20) {
        $(".ment").text("나가기 좋은 날씨!");
      } else {
        $(".ment").text("냉동 개구리");
      }
    }
  } else {
    $(".ment").text("밖에 비와용");
  }
}
