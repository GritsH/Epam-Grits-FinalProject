import {setEmailEventListeners, setPassword1EventListeners} from "./common.js";
import {validateEmail, validatePassword} from "./validation.js";

setEventListeners();

function setEventListeners() {
    setEmailEventListeners();
    setPassword1EventListeners();
    setSubmitListeners();
}

function setSubmitListeners() {
    const submitButton = document.querySelector('.card__submit');
    submitButton.addEventListener('click', (event) => {
        event.preventDefault();
        if (validateAllInputs()) {
            const form = document.querySelector('.card-form');
            form.submit();
        }
    });
}

function validateAllInputs() {
    const email = document.querySelector('#emailInput').value;
    const password = document.querySelector('#password1Input').value;

    const emailValid = validateEmail(email);
    const passwordValid = validatePassword(password);

    if (emailValid && passwordValid) {
        return true;
    }

    if (!emailValid) {
        const emailError = document.querySelector('#emailError');
        if (!emailError.classList.contains('input-error_active')) {
            emailError.classList.add('input-error_active');
        }
    }
    if (!passwordValid) {
        const password1Error = document.querySelector('#password1Error');
        if (!password1Error.classList.contains('input-error_active')) {
            password1Error.classList.add('input-error_active');
        }
    }
    return false;
}