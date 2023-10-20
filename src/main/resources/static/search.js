document.addEventListener("click", function(event) {
    var searchResults = document.getElementById("search-results");
    var searchInput = document.getElementById("search-input");

    if (event.target !== searchResults && event.target !== searchInput) {
        searchResults.style.display = "none";
    }
});
