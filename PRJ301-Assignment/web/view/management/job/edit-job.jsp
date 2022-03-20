<%-- 
    Document   : edit-job
    Created on : Mar 2, 2022, 11:19:48 PM
    Author     : PhuongNH
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>HRM - Edit Job</title>
        <link href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>

    <body>

        <div class="wrapper">

            <!-- Left Sidebar -->
            <nav id="sidebar">
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
                                <ul class="breadcrumb breadcrumb-style" style="background-color: #f1f2f7; margin-bottom: 16px">
                                    <li class="breadcrumb-item">
                                        <h4 class="page-title"><strong>Edit Job</strong></h4>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-1">
                                        <a href="../../index.html">
                                            <i class="fas fa-home"></i>Home</a>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-2">
                                        <a href="#" onClick="return false;">Job</a>
                                    </li>
                                    <li class="breadcrumb-item active">Edit Job</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <form action="editJob" method="POST" name="formJob">
                        <div class="body" style="border-radius: 8px;background-color:#FFF; padding: 4px 12px; margin-bottom: 8px">
                            <div class="body-element">
                                <h2 style="font-size: 16px">
                                    <strong>Add Job</strong>
                                </h2>
                            </div>
                            <div class="body-element">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <div class="form-line">                                                
                                                <input type="hidden" value="${requestScope.job.job_id}" name="job_id"/>
                                            <input type="text" class="form-control" value="${requestScope.job.job_title}" name="job_title" id="job_title" pattern="^[a-zA-Z ]*$" placeholder="Job Title" required/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select name="department_id" id="department_id" class="col-12 m-t-20 p-l-0 form-control" required>
                                                <option value=""> -- Select Department -- </option>
                                                <c:forEach items="${requestScope.departments}" var="d">
                                                    <option ${(requestScope.job.department.department_id == d.department_id) ? "selected = \"selected\"" : ""} value="${d.department_id}">${d.department_name}</option>
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
                                            <input type="text" class="form-control" value="${requestScope.job.min_salary}" name="min_salary" id="min_salary" pattern="^(\d+(\.\d{0,2})?|\.?\d{1,2})$" placeholder="Min Salary" required/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input type="text" name="max_salary" id="max_salary" class="form-control" value="${requestScope.job.max_salary}"  pattern="^(\d+(\.\d{0,2})?|\.?\d{1,2})$" placeholder="Max Salary" required/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12 text-center" style="margin-bottom: 8px">
                                    <input type="submit" class="btn btn-primary btn-submit" id="sub_button" style="margin-right: 15px" value="Submit"/>
                                    <button type="button" onclick="goBack()" class="btn btn-danger btn-cancel">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- #End Content -->
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/job.js"></script>
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
