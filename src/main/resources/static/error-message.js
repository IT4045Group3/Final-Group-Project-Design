const errorMessage = document.querySelector('.error-message');

function showMessageAndHide(element) {
    if (element.textContent.trim() !== '') {
        element.style.display = 'block';

        setTimeout(() => {
            element.style.display = 'none';
            element.textContent = null;
        }, 5000);
    }
}

showMessageAndHide(errorMessage);
