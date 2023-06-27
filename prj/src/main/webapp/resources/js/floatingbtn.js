const popupClock = document.querySelector(".popup-clock");

  function getPopupClock() {
    const date = new Date();
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const seconds = String(date.getSeconds()).padStart(2, "0");

    popupClock.innerText = `${hours}:${minutes}:${seconds}`;
  }

  getPopupClock();
  setInterval(getPopupClock, 1000);
  
  
document.getElementById("popup-btn").addEventListener("click", function () {
  document.getElementById("popup-overlay").style.display = "block";
  document.getElementById("popup").style.display = "block";
});

document.getElementById("close-btn").addEventListener("click", function () {
  document.getElementById("popup-overlay").style.display = "none";
  document.getElementById("popup").style.display = "none";
});
