<%-- 
    Document   : view-department
    Created on : Feb 12, 2022, 10:03:46 PM
    Author     : PhuongNH
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>HRM - View Department</title>
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
                    <!-- Header -->
                    <jsp:include page="../header.jsp"></jsp:include>
                    <!-- #END Header -->


                    <div class="block-header">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <ul class="breadcrumb breadcrumb-style" style="background-color: #f1f2f7; margin-bottom: 14px">
                                    <li class="breadcrumb-item">
                                        <h4 class="page-title"><strong>All Department</strong></h4>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-1">
                                        <a href="../../index.html">
                                            <i class="fas fa-home"></i>Home</a>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-2">
                                        <a href="#" onClick="return false;">Department</a>
                                    </li>
                                    <li class="breadcrumb-item active">All Department</li>
                                    <li class="breadcrumb-item active">View Detail</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin: 0">
                        <div class="col-lg-12 col-md-12 col-sm-12 table-responsive item-display">
                            <div>
                                <h2 style="font-size: 16px">
                                    <strong>View Detail </strong>
                                </h2>
                            </div>
                            <div class="tbl-view-detail">
                                <table>
                                    <tr>
                                        <td><strong>ID</strong></td>
                                        <td>${requestScope.department.department_id}</td>
                                </tr>
                                <tr>
                                    <td><strong>Name Of Department</strong></td>
                                    <td>${requestScope.department.department_name}</td>
                                </tr>
                                <tr>
                                    <td><strong>Manager</strong></td>
                                    <c:if test="${requestScope.department.manager.e_id > 0}">
                                        <td>
                                            ${requestScope.manager.e_last_name} ${requestScope.manager.e_first_name} <span style="font-size: 12px">
                                                (${requestScope.manager.e_email})</span> 
                                                <a href="../employee/viewEmployee?eid=${requestScope.manager.e_id}" class="btn btn-tbl-view">
                                                    <i class="fas fa-eye tbl-icon"></i></a>
                                        </td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td><strong>Total Employee</strong></td>
                                    <td>${requestScope.department.employees.size()}</td>
                                </tr>
                                <tr>
                                    <td><strong>Phone</strong></td>
                                    <td>${requestScope.department.department_phone}</td>
                                </tr>
                                <tr>
                                    <td><strong>Email</strong></td>
                                    <td>${requestScope.department.department_email}</td>
                                </tr>
                                <tr>
                                    <td><strong>Starting Date</strong></td>
                                    <td>${requestScope.department.department_starting_date}</td>
                                </tr>
                                <tr>
                                    <td><strong>More About Department</strong></td>
                                    <td>${requestScope.department.description}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- #End Content -->
    </div>



    <script src="${pageContext.request.contextPath}/Bootstrap/js/Jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Bootstrap/js/bootstrap.min.js"></script>
    <script>
                                            $(document).ready(function () {
                                                $('#sidebarCollapse').on('click', function () {
                                                    $('#sidebar').toggleClass('active');
                                                });
                                            });
    </script>
</body>
</html>
