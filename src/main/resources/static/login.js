const loginsec=document.querySelector('.login-section')
const loginlink=document.querySelector('.login-link')
const registerlink=document.querySelector('.register-link')
registerlink.addEventListener('click',()=>{
    loginsec.classList.add('active')
})
loginlink.addEventListener('click',()=>{
    loginsec.classList.remove('active')
})

$('register-form').submit(function (e) {
    if(!$('#agreeCheckBox').is(':checked')){
        alert('You have to agree statement.');
        e.preventDefault();
    }
});

