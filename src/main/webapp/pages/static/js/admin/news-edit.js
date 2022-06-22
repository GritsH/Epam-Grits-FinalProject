setDefaultDate();

function setDefaultDate() {
    const dateInput = document.querySelector('#newsDate');
    const date = new Date();
    dateInput.value = date.toISOString().split('T')[0];
}
