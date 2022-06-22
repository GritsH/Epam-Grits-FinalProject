setEventListeners();

function setEventListeners() {
    setDeleteButtonEventListeners();
}

function setDeleteButtonEventListeners() {
    const deleteButton = document.querySelector('#deleteButton');
    deleteButton.addEventListener('click', function (event) {
        event.preventDefault();
        const choice = confirm("Are you sure you want to delete this news?");
        if (choice) {
            const form = document.querySelector('.admin-body-content');
            form.submit();
        }
    });
}
