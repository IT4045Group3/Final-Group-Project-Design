document.addEventListener("DOMContentLoaded", function() {
    let currentSlide = 0;
    const slides = document.querySelectorAll('.slide');

    function showNextSlide() {
        slides[currentSlide].style.left = "-100%";
        currentSlide = (currentSlide + 1) % slides.length;
        slides[currentSlide].style.left = "0";
    }

    setInterval(showNextSlide, 5000);
});
