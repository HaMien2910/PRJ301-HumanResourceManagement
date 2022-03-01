
/*
 * Validate Form Employee
 */

window.addEventListener("load", function () {
    document.getElementById("sub_button").onclick = runSubmit;
    document.getElementById("first_name").oninput = validateFirstName;
    document.getElementById("last_name").oninput = validateLastName;
    document.getElementById("salary").oninput = validateSalary;
    document.getElementById("phone").oninput = validatePhone;
    document.getElementById("dob").oninput = validateDate;
});

function runSubmit() {
    validateFirstName();
    validateLastName();
    validateDepartment();
    validateSalary();
    validatePhone();
    validateDate();
}

function validateFirstName() {
    var firstName = document.getElementById("first_name");
    var validateName = firstName.validity;

    if (validateName.valueMissing || document.forms["formEmployee"]["first_name"].value.trim() == "") { // check empty
        firstName.setCustomValidity("This field is required! You must enter letters.");
    } else if (validateName.patternMismatch) { // Check if firstname matches with pattern or not 
        firstName.setCustomValidity("Invalid! This field should only contain letters. Please try again!");
    } else {
        firstName.setCustomValidity('');
    }
    firstName.reportValidity();
}

function validateLastName() {
    var lastName = document.getElementById("last_name");
    var validateName = lastName.validity;
    if (validateName.valueMissing || document.forms["formEmployee"]["last_name"].value.trim() == "") { // check empty
        lastName.setCustomValidity("This field is required! You must enter letters.");
    } else if (validateName.patternMismatch) { // Check if firstname matches with pattern or not 
        lastName.setCustomValidity("Invalid! This field should only contain letters. Please try again!");
    } else {
        lastName.setCustomValidity('');
    }
    lastName.reportValidity();
}

function validateSalary() {
    var salary = document.getElementById("salary");
    var validateSal = salary.validity;
    if (validateSal.valueMissing || document.forms["formEmployee"]["salary"].value.trim() == "") { // check empty
        salary.setCustomValidity("This field is required! you must enter a decimal number.");
    } else if (validateSal.patternMismatch) { // Check if firstname matches with pattern or not 
        salary.setCustomValidity("Invalid! This field should only contain a decimal number. Please try again!");
    } else {
        salary.setCustomValidity('');
    }
    salary.reportValidity();
}

function validatePhone() {
    var phone = document.getElementById("phone");
    var validatePhoneNum = phone.validity;
    if (validatePhoneNum.valueMissing || document.forms["formEmployee"]["phone"].value.trim() == "") { // check empty
        phone.setCustomValidity("This field is required! Phone number format(0xxxx...) with max length is 20.");
    } else if (validatePhoneNum.patternMismatch) { // Check if firstname matches with pattern or not 
        phone.setCustomValidity("Invalid! Phone number format(0xxxxxxxxx) with length [9-20]. Please try again!");
    } else {
        phone.setCustomValidity('');
    }
    phone.reportValidity();
}

function validateDate() {
    var dob = document.getElementById("dob");
    var validateDob = dob.validity;
    if (validateDob.valueMissing || document.forms["formEmployee"]["dob"].value.trim() == "") { // check empty
        dob.setCustomValidity("This field is required! Date format [dd/MM/yyyy]");
    } else if (validateDob.patternMismatch) { // Check if firstname matches with pattern or not 
        dob.setCustomValidity("Invalid! Date format [dd/MM/yyyy]. Please try again!");
    } else {
        dob.setCustomValidity('');
    }
    dob.reportValidity();
}

/*
 * Button Cancel
 */
function goBack(){
    window.location = "/PRJ301-Assignment/employee/listAllEmployees";
}
