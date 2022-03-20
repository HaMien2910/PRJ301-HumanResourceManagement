<%-- 
    Document   : verify
    Created on : Mar 16, 2022, 12:11:27 AM
    Author     : PhuongNH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HRM - Login</title>
        <!-- Favicon-->
        <link rel="icon" href="../../assets/images/favicon.ico" type="image/x-icon">
        <link href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Bootstrap/css/app.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>

    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form class="login100-form validate-form" action="verify" method="POST">
                        <span class="login100-form-title">
                            <h5 style="color: #6675df"><strong>We already send a OTP code to your email</strong></h5>
                        </span>
                        <div class="wrap-input100 validate-input">
                            <input class="input100" style="border-bottom: none;" type="text" name="otp" placeholder="Enter OTP">
                            <span class="focus-input100"></span>
                        </div>
                        <c:if test="${!requestScope.isValid}">
                            <span style="font-size: 12px;margin-left: 16px; color: #cc3300">OTP invalid! Please check email and re-enter!</span>
                        </c:if>
                        <div class="container-login100-form-btn" style="margin-top: 16px;">
                            <input type="submit" class="login100-form-btn" value="Verify"/>
                        </div>
                    </form>
                    <div class="login100-more" style="background-image: url('${pageContext.request.contextPath}/assets/images/pages/bg-01.png');">
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
