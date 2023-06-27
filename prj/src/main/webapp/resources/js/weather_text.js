// 날씨 api - fontawesome 아이콘
var weatherIcon2 = {
  "01": "fas fa-sun", // 맑음 (실외)
  "02": "fas fa-cloud-sun", //조금 흐림 (실외)
  "03": "fas fa-cloud", // 흐림 (실외)
  "04": "fas fa-cloud-meatball", // 구름이 많은 날씨를 유머적으로 표현함 (실외)
  
  "09": "fas fa-cloud-sun-rain", // 반맑반우 (실내)
  10: "fas fa-cloud-showers-heavy", //폭우 (실내)
  
  11: "fas fa-poo-storm", //태풍  (실내)
  13: "far fa-snowflake", //눈 (실내)
  
  50: "fas fa-smog", //안개 (실외)
  
};

// 날씨 api - 서울
//let apiURI2 =

//let airAPIURI2 =

$.ajax({
  url: apiURI2,
  dataType: "json",
  type: "GET",
  async: "false",
  timeout: 60000,
  success: function (resp) {
    var $Icon = resp.weather[0].icon.substr(0, 2);
    var $weather_description = resp.weather[0].main;
    var $Temp = Math.floor(resp.main.temp - 273.15) + "º";
    var $humidity = "습도&nbsp;&nbsp;&nbsp;&nbsp;" + resp.main.humidity + " %";
    var $wind = "바람&nbsp;&nbsp;&nbsp;&nbsp;" + resp.wind.speed + " m/s";
    var $city = "서울";
    var $cloud = "구름&nbsp;&nbsp;&nbsp;&nbsp;" + resp.clouds.all + "%";
    var $temp_min =
      "최저 온도&nbsp;&nbsp;&nbsp;&nbsp;" +
      Math.floor(resp.main.temp_min - 273.15) +
      "º";
    var $temp_max =
      "최고 온도&nbsp;&nbsp;&nbsp;&nbsp;" +
      Math.floor(resp.main.temp_max - 273.15) +
      "º";

    $(".weather_icon").append(
      '<i class="' +
        weatherIcon2[$Icon] +
        ' fa-5x" style="height : 150px; width : 150px;"></i>'
    );
    $(".weather_description").prepend($weather_description);
    $(".current_temp").prepend($Temp);
    $(".humidity").prepend($humidity);
    $(".wind").prepend($wind);
    $(".city").append($city);
    $(".cloud").append($cloud);
    $(".temp_min").append($temp_min);
    $(".temp_max").append($temp_max);
  },
});

$.ajax({
  url: airAPIURI2,
  dataType: "json",
  type: "GET",
  async: "false",
  timeout: 60000,
  success: function (airResp) {
    let $pm25 = airResp.list[0].components.pm2_5 || 0; // 미세먼지(PM2.5) 값
    let $pm10 = airResp.list[0].components.pm10 || 0; // 미세먼지(PM10) 값
    let $pm25slice = $pm25.toString().split(".");
    let $airQuality1 = "미세먼지 " + $pm25slice[0] + "㎍/m³";
    $(".air-quality1").text($airQuality1);
  },
});
