import {passwordsMatch, validateEmail, validatePassword} from "./validation.js";
import {setEmailEventListeners, setPassword1EventListeners} from "./common.js";

setEventListeners();

function setEventListeners() {
    setEmailEventListeners();
    setPassword1EventListeners();
    setPassword2EventListeners();
    setSubmitEventListeners();
}

function setPassword2EventListeners() {
    const password2Input = document.querySelector('#password2Input');
    const password2Error = document.querySelector('#password2Error');
    password2Input.addEventListener('input', function (event) {
        const password1 = document.querySelector('#password1Input').value;
        const password2 = event.target.value;
        if (password2 === '') {
            password2Error.classList.remove('input-error_active');
            return;
        }
        if (!passwordsMatch(password1, password2)) {
            if (!password2Error.classList.contains('input-error_active')) {
                password2Error.classList.add('input-error_active');
            }
        } else {
            password2Error.classList.remove('input-error_active');
        }
    });
}

function setSubmitEventListeners() {
    const submitButton = document.querySelector('.card__submit');
    submitButton.addEventListener('click', function (event) {
        event.preventDefault();
        if (validateAllInputs()) {
            const form = document.querySelector('.card-form');
            form.submit();
        }
    });
}

function validateAllInputs() {
    const email = document.querySelector('#emailInput').value;
    const password1 = document.querySelector('#password1Input').value;
    const password2 = document.querySelector('#password2Input').value;

    const emailValid = validateEmail(email);
    const password1Valid = validatePassword(password1);
    const password2Valid = passwordsMatch(password1, password2);

    if (emailValid && password1Valid && password2Valid) {
        return true;
    }

    if (!emailValid) {
        const emailError = document.querySelector('#emailError');
        if (!emailError.classList.contains('input-error_active')) {
            emailError.classList.add('input-error_active');
        }
    }
    if (!password1Valid) {
        const password1Error = document.querySelector('#password1Error');
        if (!password1Error.classList.contains('input-error_active')) {
            password1Error.classList.add('input-error_active');
        }
    }
    if (!password2Valid) {
        const password2Error = document.querySelector('#password2Error');
        if (!password2Error.classList.contains('input-error_active')) {
            password2Error.classList.add('input-error_active');
        }
    }
    return false;
}
