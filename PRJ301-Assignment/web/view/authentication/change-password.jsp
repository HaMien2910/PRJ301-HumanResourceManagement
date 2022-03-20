<%-- 
    Document   : change-password
    Created on : Mar 16, 2022, 7:58:40 PM
    Author     : PhuongNH
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>HRM - Authentication</title>
        <link href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>

    <body>

        <div class="wrapper">

            <!-- Left Sidebar -->
            <nav id="sidebar">>
                <jsp:include page="../management/menu.jsp"></jsp:include>
                </nav>
                <!-- #End Left Sidebar -->

                <!-- Content -->
                <div id="content" style="background-color: #f1f2f7;">
                    <!-- Header -->
                <jsp:include page="../management/header.jsp"></jsp:include>
                    <!-- #END Header -->
                    <div class="block-header">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <ul class="breadcrumb breadcrumb-style" style="background-color: #f1f2f7; margin-bottom: 14px">
                                    <li class="breadcrumb-item">
                                        <h4 class="page-title"><strong>Authentication</strong></h4>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-1">
                                        <a href="../../index.html">
                                            <i class="fas fa-home"></i>Home</a>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-2">
                                        <a href="#" onClick="return false;">Authentication</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <form action="changePassword" method="POST" name="formJob">
                        <div class="body" style="border-radius: 8px;background-color:#FFF; padding: 4px 12px; margin-bottom: 8px">
                            <div class="body-element" style="margin-bottom: 24px;">
                                <h2 style="font-size: 16px">
                                    <strong>Change Password</strong>
                                </h2>
                            </div>
                        <c:if test="${requestScope.isSuccessful}">
                            <span style="font-size: 12px;margin-left: 16px; color: #4fab52"><strong>Your password has been changed!</strong></span>
                        </c:if>
                        <div class="body-element"style="margin-left: 48px;">
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input type="password" class="form-control" name="current_password" placeholder="Current password" required/>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${!requestScope.isValidCurrentPassword}">
                                    <span style="font-size: 12px;margin-left: 16px; color: #cc3300">Error current password. Enter again!</span>
                                </c:if>
                            </div>
                        </div>
                        <div class="body-element"style="margin-left: 48px;">
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input type="password" class="form-control" name="new_password1"  placeholder="New password" required/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="body-element"style="margin-left: 48px;">
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input type="password" class="form-control" name="new_password2" placeholder="Confirm new password one more" required/>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${!requestScope.isValidNewPassword}">
                                    <span style="font-size: 12px;margin-left: 16px; color: #cc3300">Error confirmation password. Enter again!</span>
                                </c:if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-4 text-center" style="margin-left: 48px; margin-bottom: 8px">
                                <input type="submit" class="btn btn-primary btn-submit" id="sub_button" style="margin-right: 15px" value="Save"/>
                                <button type="button" onclick="goBack()" class="btn btn-danger btn-cancel">Cancel</button>
                            </div>
                        </div>
                    </div>
            </div>
        </form>
    </div>
    <!-- #End Content -->
</div>
<!-- #End Modal To Delete An Employee -->
<script src="${pageContext.request.contextPath}/assets/js/management.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/Bootstrap/js/Jquery.js"></script>
<script src="${pageContext.request.contextPath}/Bootstrap/js/bootstrap.min.js"></script>
<script>
                                    function goBack() {
                                        window.location = "/PRJ301-Assignment/employee/listAllEmployees";
                                    }
</script>
</body>
</html>