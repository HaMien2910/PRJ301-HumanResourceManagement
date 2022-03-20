<div class="sidebar-header text-center">
    <h3 style="color: #181c32">PRJ301 Assignment</h3>
    <div>
        <image style="border-radius:12px; height: 70px; margin-top: 16px;box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);" src="${pageContext.request.contextPath}/assets/images/user/usrbig6.jpg">
    </div>
    <div>
        <h5 style="color: #181c32"><strong>${sessionScope.account.employee.e_last_name} ${sessionScope.account.employee.e_first_name}</strong></h5>
    </div>
</div>

<!--Menu--> 
<ul class="components">
    <li>
        <a href="../../index.html">
            <i class="fas fa-home"></i> Home</a>
    </li>
    <li class="active">
        <a href="#" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Employees</a>
        <ul class="list-unstyled" id="homeSubmenu">
            <li>
                <a class="${tag eq "listAllEmployees" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/listAllEmployees">All Employees</a>
            </li>
            <li>
                <a class="${tag eq "addEmployee" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/addEmployee">Add Employee</a>
            </li>
        </ul>
    </li>
    <li class="active">
        <a href="#" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Departments</a>
        <ul class="list-unstyled" id="homeSubmenu">
            <li>
                <a class="${tag eq "listAllDepartments" ? "active" : ""}" href="${pageContext.request.contextPath}/department/listAllDepartments">All Departments</a>
            </li>
            <li>
                <a class="${tag eq "addDepartment" ? "active" : ""}" href="${pageContext.request.contextPath}/department/addDepartment">Add Departments</a>
            </li>
        </ul>
    </li>
    <li class="active">
        <a href="#" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Jobs</a>
        <ul class="list-unstyled" id="homeSubmenu">
            <li>
                <a class="${tag eq "listAllJobs" ? "active" : ""}" href="${pageContext.request.contextPath}/job/listAllJobs">All Jobs</a>
            </li>
            <li>
                <a class="${tag eq "addJob" ? "active" : ""}" href="${pageContext.request.contextPath}/job/addJob">Add Job</a>
            </li>
        </ul>
    </li>
    <li>
        <a href="#">About</a>
    </li>
</ul>
<!--#End Menu--> 
