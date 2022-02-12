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
                                    <td></td>
                                </tr>
                                <tr>
                                    <td><strong>Department Name</strong></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td><strong>Manager</strong></td>
                                    <td></td>
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
