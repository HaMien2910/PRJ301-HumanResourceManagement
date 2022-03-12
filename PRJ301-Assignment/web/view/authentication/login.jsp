<%-- 
    Document   : login
    Created on : Mar 10, 2022, 6:13:43 PM
    Author     : PhuongNH
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
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
                        <form class="login100-form validate-form" action="login" method="POST">
                            <span class="login100-form-title">
                            Login
                        </span>
                            <div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                                <input class="input100" style="border-bottom: none;" type="text" name="username">
                                <span class="focus-input100"></span>
                                <span class="label-input100">Email</span>
                            </div>
                            <div class="wrap-input100 validate-input" data-validate="Password is required">
                                <input class="input100" style="border-bottom: none;" type="password" name="password">
                                <span class="focus-input100"></span>
                                <span class="label-input100">Password</span>
                            </div>
                            <div class="form-check-and-update-pass">
                                <div class="form-checkbox">
                                    <label class="form-checklabel">
                                    <input class="form-checkinput" type="checkbox" value=""> Remember me
                                    <span class="form-check-sign" style="position: absolute;display: contents;">
                                        <span class="check"></span>
                                    </span>
                                </label>
                                </div>
                                <div>
                                    <a href="#" class="txt1">
                                    Forgot Password?
                                </a>
                                </div>
                            </div>
                            <div class="container-login100-form-btn">
                                <input type="submit" class="login100-form-btn" value="Login"/>
                            </div>
                        </form>
                        <div class="login100-more" style="background-image: url('${pageContext.request.contextPath}/assets/images/pages/bg-01.png');">
                        </div>
                    </div>
                </div>
            </div>
            <!-- Plugins Js -->
            <!--<script src="../../assets/js/app.min.js"></script>-->
            <!-- Extra page Js -->
            <!--<script src="../../assets/js/pages/examples/pages.js"></script>-->
            <!--        <form action="login" method="POST">
                    Username: <input type="text" name="username"/><br>
                    Password: <input type="password" name="password"/><br>
                    <input type="submit" value="Login">
                </form>-->
        </body>

        </html>