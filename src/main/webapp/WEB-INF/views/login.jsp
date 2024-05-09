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

        <!-- Login  Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="${classpath}/login/images/signin-image.jpg" alt="sing up image"></figure>
                        <a href="${classpath }/signup" class="signup-image-link">Create an account</a>
                    </div>

                    <div class="signin-form">
                        <h2 class="form-title">Login</h2>
                        <form method="POST" action="${classpath }/login_processing_url" class="register-form" id="login-form">
							<c:if test="${not empty param.login_error }">
								<div class="alert alert-danger" role="alert">
									Login attempt was not successful, try again!!!</div>
							</c:if>
                            <div class="form-group">
                                <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="username" id="username" class="form-control" placeholder="Username"/>
                            </div>
                            <div class="form-group">
                                <label for=password><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="password" class="form-control" placeholder="Password"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
                            </div>
                            <div class="form-group form-button">
                                <button type="submit" class="btn btn-primary form-submit">Login</button>
                            </div>
                        </form>
                        <div class="social-login">
                            <span class="social-label">Or login with</span>
                            <ul class="socials">
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
                            </ul>
                        </div>
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