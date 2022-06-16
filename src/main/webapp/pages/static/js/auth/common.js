import {validateEmail, validatePassword} from "./validation.js";

export function setEmailEventListeners() {
    const emailInput = document.querySelector('#emailInput');
    const emailError = document.querySelector('#emailError');
    emailInput.addEventListener('input', function (event) {
        const email = event.target.value;
        if (!validateEmail(email)) {
            if (!emailError.classList.contains('input-error_active')) {
                emailError.classList.add('input-error_active');
            }
        } else {
            emailError.classList.remove('input-error_active');
        }
    });
}

export function setPassword1EventListeners() {
    const password1Input = document.querySelector('#password1Input');
    const password1Error = document.querySelector('#password1Error');
    password1Input.addEventListener('input', function (event) {
        const password1 = event.target.value;
        if (password1 === '') {
            password1Error.classList.remove('input-error_active');
            return;
        }
        if (!validatePassword(password1)) {
            if (!password1Error.classList.contains('input-error_active')) {
                password1Error.classList.add('input-error_active');
            }
        } else {
            password1Error.classList.remove('input-error_active');
        }
    });
}