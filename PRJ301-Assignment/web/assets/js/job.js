/*
 * Validate Form Job
 */

window.addEventListener("load", function () {
    document.getElementById("sub_button").onclick = runSubmit;
    document.getElementById("job_title").oninput = validateJobTitle;
    document.getElementById("min_salary").oninput = validateMinSalary;
    document.getElementById("max_salary").oninput = validateMaxSalary;

});

function runSubmit() {
    validateJobTitle();
    validateMinSalary();
    validateMaxSalary();
}

function validateJobTitle() {
    var jobTitle = document.getElementById("job_title");
    var validateTitle = jobTitle.validity;

    if (validateTitle.valueMissing || document.forms["formJob"]["job_title"].value.trim() == "") { // check empty
        jobTitle.setCustomValidity("This field is required! You must enter letters.");
    } else if (validateTitle.patternMismatch) { // Check if firstname matches with pattern or not 
        jobTitle.setCustomValidity("Invalid! This field should only contain letters. Please try again!");
    } else {
        jobTitle.setCustomValidity('');
    }
    jobTitle.reportValidity();
}

function validateMinSalary() {
    var minSalary = document.getElementById("min_salary");

    var validateMinSal = minSalary.validity;
    if (validateMinSal.valueMissing || document.forms["formJob"]["min_salary"].value.trim() == "") { // check empty
        minSalary.setCustomValidity("This field is required! you must enter a decimal number.");
    } else if (validateMinSal.patternMismatch) { // Check if firstname matches with pattern or not 
        minSalary.setCustomValidity("Invalid! This field should only contain a decimal number. Please try again!");
    } else {
        minSalary.setCustomValidity('');
    }
    minSalary.reportValidity();

}

function validateMaxSalary() {
    var maxSalary = document.getElementById("max_salary");

    var validateMaxSal = maxSalary.validity;
    if (validateMaxSal.valueMissing || document.forms["formJob"]["max_salary"].value.trim() == "") { // check empty
        maxSalary.setCustomValidity("This field is required! you must enter a decimal number.");
    } else if (validateMaxSal.patternMismatch) { // Check if firstname matches with pattern or not 
        maxSalary.setCustomValidity("Invalid! This field should only contain a decimal number. Please try again!");
    } else {
        maxSalary.setCustomValidity('');
    }
    maxSalary.reportValidity();

}

function goBack() {
    window.location = "/PRJ301-Assignment/job/listAllJobs";
}