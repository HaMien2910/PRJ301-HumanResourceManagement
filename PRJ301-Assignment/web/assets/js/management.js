/*
 * DELETE AN OBJECT
 */
function onDelete() {
    let confirmation = document.getElementById("confirmation");
    if (!confirmation.classList.contains("modal-open")) {
        confirmation.classList.add("modal-open");
    }
}

function onCancel() {
    let confirmation = document.getElementById("confirmation");
    confirmation.classList.remove("modal-open");
}

document.addEventListener("DOMContentLoaded", () => {
    document
            .getElementById("confirmation")
            .addEventListener("click", onCancel);
});

/*
 * Validate Form Employee
 */
function validateFormEmployee() {
    if (document.formEmployee.first_name.value == "") {
        alert("Please provide your name!");
        document.myForm.first_name.focus();
        return false;
    }
    return (true);
}
