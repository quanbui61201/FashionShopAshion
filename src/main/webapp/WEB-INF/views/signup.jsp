<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="icon" type="image/png" sizes="16x16"  href="${classpath}/backend/assets/img/favicon.png">
    <title>Log In</title>

	<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
    <!-- Font Icon -->
    <link rel="stylesheet" href="${classpath}/login/fonts/material-icon/css/material-design-iconic-font.min.css">

    <link rel="stylesheet" href="${classpath}/login/css/style.css">
</head>
<body>

    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <form method="POST" action="${classpath }/register" class="register-form" id="register-form" autocomplete="off">
                            <div class="form-group">
                                <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="username" id="username" class="form-control" placeholder="Username"/>
                            </div>
                            <div class="form-group">
                                <label for="password"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="password" class="form-control" placeholder="Password"/>
                            </div>
                            <div class="form-group">
                                <label for="retypepassword"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="retypepassword" id="retypepassword" class="form-control" placeholder="Repeat your password"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account"></i></label>
                                <input type="text" name="name" id="name" class="form-control" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" class="form-control" placeholder="Your Email"/>
                            </div>
                            <div class="form-group">
                                <label for="mobile"><i class="zmdi zmdi-phone"></i></label>
                                <input type="text" name="mobile" id="mobile" class="form-control" placeholder="Your Mobile"/>
                            </div>
                            <div class="form-group">
                                <label for="address"><i class="zmdi zmdi-phone"></i></label>
                                <input type="text" name="address" id="address" class="form-control" placeholder="Your Address"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                            </div>
                            <div class="form-group form-button">
                                <button type="submit" class="btn btn-primary form-submit">Register</button>
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure><img src="${classpath}/login/images/signup-image.jpg" alt="sing up image"></figure>
                        <a href="${classpath }/login" class="signup-image-link">I am already member</a>
                    </div>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="${classpath}/login/vendor/jquery/jquery.min.js"></script>
    <script src="${classpath}/login/js/main.js"></script>
</body>
</html>