document.getElementById("search-btn").addEventListener("click", function () {
  var searchForm = document.getElementById("search-form");
  if (searchForm.style.display === "inline-block") {
    searchForm.style.display = "none";
  } else {
    searchForm.style.display = "inline-block";
  }
});