document.addEventListener("DOMContentLoaded", function() {
    
    const btnLogin = document.querySelector('#btnLogin');

    btnLogin.addEventListener('click', () => {
        const username = document.querySelector('#username').value;
        const password = document.querySelector('#password').value;

        if (username.trim() === '' || password.trim() === '') {
            const spam = document.createElement('p');
            spam.textContent = 'Por favor complete todos los campos';
            spam.style.color = 'red'; 
            document.querySelector('.Login').appendChild(spam);
        } 
        if (username === "admin" && password === "admin") {

            window.location.href = "inicio.html";
        } 
    });

});
