const successMessage = document.querySelector('.success-message');

function showMessageAndHide(element) {
    if (element.textContent.trim() !== '') {
        element.style.display = 'block';

        setTimeout(() => {
            element.style.display = 'none';
        }, 2000);
    }
}

showMessageAndHide(successMessage);
