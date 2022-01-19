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
                <a class="${tag eq "listAll" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/listAll">All Employees</a>
            </li>
            <li>
                <a class="${tag eq "add" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/add">Add Employee</a>
            </li>
            <li>
                <a class="${tag eq "edit" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/edit">Edit Employee</a>
            </li>
        </ul>
    </li>
    <li class="active">
        <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Departments</a>
        <ul class="list-unstyled" id="homeSubmenu">
            <li>
                <a class="${tag eq "listAll" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/listAll">All Departments</a>
            </li>
            <li>
                <a class="${tag eq "add" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/add">Add Departments</a>
            </li>
            <li>
                <a class="${tag eq "edit" ? "active" : ""}" href="${pageContext.request.contextPath}/employee/edit">Edit Departments</a>
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