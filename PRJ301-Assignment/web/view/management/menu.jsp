<!-- Menu -->
<ul class="components">
    <li>
        <a href="../../index.html">
            <i class="fas fa-home"></i> Home</a>
    </li>
    <li class="active">
        <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Employees</a>
        <ul class="list-unstyled" id="homeSubmenu">
            <li>
                <a class="${tag eq "listAllEmployees" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/listAllEmployees">All Employees</a>
            </li>
            <li>
                <a class="${tag eq "addEmployee" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/addEmployee">Add Employee</a>
            </li>
            <li>
                <a class="${tag eq "editEmployee" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/editEmployee">Edit Employee</a>
            </li>
        </ul>
    </li>
    <li class="active">
        <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Departments</a>
        <ul class="list-unstyled" id="homeSubmenu">
            <li>
                <a class="${tag eq "listAllDepartments" ? "active" : ""}" href="${pageContext.request.contextPath}/department/listAllDepartments">All Departments</a>
            </li>
            <li>
                <a class="${tag eq "addDepartment" ? "active" : ""}" href="${pageContext.request.contextPath}/department/addDepartment">Add Departments</a>
            </li>
            <li>
                <a class="${tag eq "addDepartment" ? "active" : ""}" href="${pageContext.request.contextPath}/department/addDepartment">Edit Departments</a>
            </li>
        </ul>
    </li>
    <li>
        <a href="#">About</a>
    </li>
    <li>
        <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Pages</a>
        <ul class="collapse list-unstyled" id="pageSubmenu">
            <li>
                <a href="#">Page 1</a>
            </li>
            <li>
                <a href="#">Page 2</a>
            </li>


        </ul>
    </li>
    <li>
        <a href="#">Policy</a>
    </li>
    <li>
        <a href="#">Contact Us</a>
    </li>
</ul>
<!-- #End Menu -->