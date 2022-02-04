<%-- 
    Document   : all-employees
    Created on : Jan 9, 2022, 10:21:28 PM
    Author     : PhuongNH
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>HRM - Add Employee</title>
        <link href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>

    <body>

        <div class="wrapper">

            <!-- Left Sidebar -->
            <nav id="sidebar">
                <!-- Top Left Sidebar -->
                <div class="sidebar-header text-center">
                    <h3 style="color: #181c32">Human Resource</h3>
                </div>
                <!-- #End Top Left Sidebar -->
                <jsp:include page="../menu.jsp"></jsp:include>
                </nav>
                <!-- #End Left Sidebar -->

                <!-- Content -->
                <div id="content" style="background-color: #f1f2f7;">
                    <!-- Right Sidebar -->
                    <nav class="navbar navbar-expand-lg navbar-light bg-light">
                        <div class="container-fluid">
                            <button type="button" id="sidebarCollapse" class="btn  btn-info">
                                <i class="fas fa-align-left"></i>
                                <span>Memu</span>
                            </button>
                        </div>
                    </nav>
                    <!-- #END Right Sidebar -->


                    <div class="block-header">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <ul class="breadcrumb breadcrumb-style" style="background-color: #f1f2f7; margin-bottom: 16px">
                                    <li class="breadcrumb-item">
                                        <h4 class="page-title"><strong>Add Employees</strong></h4>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-1">
                                        <a href="../../index.html">
                                            <i class="fas fa-home"></i>Home</a>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-2">
                                        <a href="#" onClick="return false;">Employee</a>
                                    </li>
                                    <li class="breadcrumb-item active">Add Employee</li>
                                </ul>
                            </div>
                        </div>
                    </div>


                    <form action="addEmployee" method="POST">
                        <div class="body" style="border-radius: 8px;background-color:#FFF; padding: 4px 12px; margin-bottom: 8px">
                            <div class="body-element">
                                <h2 style="font-size: 16px">
                                    <strong>Add Employee</strong>
                                </h2>
                            </div>
                            <div class="body-element">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" class="form-control" name="first_name" placeholder="First Name" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" class="form-control" name="last_name" placeholder="Last Name" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group form-float">
                                            <div class="form-line">
                                                <select name="department_id" class="col-12 m-t-20 p-l-0 form-control">
                                                    <option disabled selected> -- Select Department -- </option>
                                                <c:forEach items="${requestScope.departments}" var="d">
                                                    <option value="${d.department_id}">${d.department_name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select name="job_id" class="col-12 m-t-20 p-l-0 form-control">
                                                <option disabled selected> -- Select Job -- </option>
                                                <c:forEach items="${requestScope.jobs}" var="j">
                                                    <option value="${j.job_id}">${j.job_title}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input type="text" name="salary" class="form-control" placeholder="Salary" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input id="email" type="email" name="email" class="validate form-control" placeholder="Email">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input type="text" name="phone" class="form-control" placeholder="Telephone" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select name="gender" class="col-12 m-t-20 p-l-0 form-control">
                                                <option disabled selected> -- Select Gender -- </option>
                                                <option value="male">Male</option>
                                                <option value="female">Female</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <input type="text" class="form-control datetimepicker" placeholder="Birth Date (dd/mm/yyyy)" name="dob">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select id="province_id" class="col-12 m-t-20 p-l-0 form-control">
                                                <option disabled selected> -- Select Province -- </option>
                                                <c:forEach items="${requestScope.provinces}" var="p">
                                                    <option value="${p.province_id}">${p.province_name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group form-float">
                                        <div class="form-line" id="district_id">
                                            <select name="district_id" class="col-12 m-t-20 p-l-0 form-control">
                                                <option disabled selected> -- Select District -- </option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group form-float">
                                        <div class="form-line" id="ward_id">
                                            <select name="ward_id" class="col-12 m-t-20 p-l-0 form-control">
                                                <option disabled selected> -- Select Ward -- </option>  
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input name="street" type="text" class="form-control no-resize" placeholder="Street"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12 text-center" style="margin-bottom: 8px">
                                    <input type="submit" class="btn btn-primary btn-submit" style="margin-right: 15px" value="Submit"/>
                                    <button type="button" class="btn btn-danger btn-cancel">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- #End Content -->
    </div>

    <script src="${pageContext.request.contextPath}/Bootstrap/js/Jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Bootstrap/js/bootstrap.min.js"></script>
</body>

</html>
<script>
                                    $(document).ready(function () {
                                        $('#sidebarCollapse').on('click', function () {
                                            $('#sidebar').toggleClass('active');
                                        });
                                    });
</script>
<script type="text/javascript">

    $(document).ready(function () {
        $("#province_id").on('change', function () {
            var province_id = $("#province_id").val();
            $("#error").html("");
            $.ajax({
                url: "../../../PRJ301-Assignment/view/management/address/district.jsp",
                data: {
                    province_id: province_id
                },
                method: "POST",
                success: function (data) {
                    $("#district_id").html(data);
                }
            });
        });
    });
</script>