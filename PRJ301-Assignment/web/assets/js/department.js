/*
 * Validate Form Department
 */

window.addEventListener("load", function () {
    document.getElementById("sub_button").onclick = runSubmit;
    document.getElementById("department_name").oninput = validateDepartmnetName;
    document.getElementById("phone").oninput = validatePhone;
});


function runSubmit() {
    validateDepartmnetName();
    validatePhone();
}

function validateDepartmnetName() {
    var departmentName = document.getElementById("department_name");
    var validateName = departmentName.validity;

    if (validateName.valueMissing || document.forms["formDepartment"]["department_name"].value.trim() == "") { // check empty
        departmentName.setCustomValidity("This field is required!");
    } else {
        departmentName.setCustomValidity('');
    }
    departmentName.reportValidity();
}

function validatePhone() {
    var phone = document.getElementById("phone");
    var validatePhoneNum = phone.validity;
    if (validatePhoneNum.patternMismatch) { // Check if firstname matches with pattern or not 
        phone.setCustomValidity("Invalid! Phone number format(0xxxxxxxxx) with length [9-20]. Please try again!");
    } else {
        phone.setCustomValidity('');
    }
    phone.reportValidity();
}

/*
 * Button Cancel
 */
function goBack(){
    window.location = "/PRJ301-Assignment/department/listAllDepartments";
}