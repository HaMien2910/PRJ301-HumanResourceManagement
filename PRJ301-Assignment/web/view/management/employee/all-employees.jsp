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
        <title>HRM - List All Employees</title>
        <link href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
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
                                <ul class="breadcrumb breadcrumb-style" style="background-color: #f1f2f7; margin-bottom: 14px">
                                    <li class="breadcrumb-item">
                                        <h4 class="page-title"><strong>All Employees</strong></h4>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-1">
                                        <a href="../../index.html">
                                            <i class="fas fa-home"></i>Home</a>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-2">
                                        <a href="#" onClick="return false;">Employee</a>
                                    </li>
                                    <li class="breadcrumb-item active">All Employees</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin: 0">
                        <div class="col-lg-12 col-md-12 col-sm-12 table-responsive item-display">
                            <div>
                                <h2 style="font-size: 16px">
                                    <strong>All Employees</strong>
                                </h2>
                            </div>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th class="text-center">#</th>
                                        <th class="text-center"> Name </th>
                                        <th class="text-center"> Job </th>
                                        <th class="text-center"> Department </th>
                                        <th class="text-center"> Mobile </th>
                                        <th class="text-center"> Email </th>
                                        <th class="text-center"> Address </th>
                                        <th class="text-center">Joining Date</th>
                                        <th class="text-center"> Action </th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.employees}" var="e">
                                    <tr class="" style="font-size: 12px">
                                        <td></td>
                                        <td class="text-center">${e.e_last_name} ${e.e_first_name}</td>
                                        <td class="text-center">${e.job.job_title}</td>
                                        <td class="text-center">${e.department.department_name}</td>
                                        <td class="text-center">${e.e_phone}</td>
                                        <td class="text-center">${e.e_email}</td>
                                        <td class="text-center">${e.location.ward.district.province.province_name}</td>
                                        <td class="text-center">${e.e_join_date}</td>
                                        <td class="text-center">
                                            <a href="viewEmployee?eid=${e.e_id}" class="btn btn-tbl-view">
                                                <i class="fas fa-eye tbl-icon"></i>
                                            </a>
                                            <a href="edit-employee.html" class="btn btn-tbl-edit">
                                                <i class="fas fa-pen tbl-icon"></i>
                                            </a>
                                            <a href="#" class="btn btn-tbl-delete">
                                                <i class="fas fa-trash tbl-icon"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th class="text-center"> Name </th>
                                    <th class="text-center"> Job </th>
                                    <th class="text-center"> Department </th>
                                    <th class="text-center"> Mobile </th>
                                    <th class="text-center"> Email </th>
                                    <th class="text-center"> Address </th>
                                    <th class="text-center">Joining Date</th>
                                    <th class="text-center"> Action </th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- #End Content -->
    </div>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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