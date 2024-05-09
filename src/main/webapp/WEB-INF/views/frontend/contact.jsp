<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="icon" type="image/png" sizes="16x16"  href="${classpath}/backend/assets/img/favicon.png">
    <title>Contact</title>

	<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>

    <!-- Css Styles -->
    <jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>
</head>

<body>
    <!-- Header -->
	<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb_links">
                        <a href="${classpath }/index"><i class="fa fa-home"></i> Home</a>
                        <span>Contact</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Contact Section Begin -->
    <section class="contact spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="contact_content">
                        <div class="contact_address">
                            <h5>Contact info</h5>
                            <ul>
                                <li>
                                    <h6><i class="fa fa-map-marker"></i> Address</h6>
                                    <p>Ngõ 65 P. Mai Dịch, Mai Dịch, Cầu Giấy, Hà Nội</p>
                                </li>
                                <li>
                                    <h6><i class="fa fa-phone"></i> Phone</h6>
                                    <p><span>+84 985 950 895</span><span>000-000-000</span></p>
                                </li>
                                <li>
                                    <h6><i class="fa fa-headphones"></i> Support</h6>
                                    <p>devpro.edu.vn</p>
                                </li>
                            </ul>
                        </div>
                        <div class="contact_form">
                            <h5>SEND MESSAGE</h5>
                            <sf:form class="form" action="/contact-save" method="post" modelAttribute="contact">
                                <sf:input path="name" type="text" placeholder="Name" id="name" name="name" />
                                <sf:input path="email" type="email" placeholder="Email" id="email" name="email" />
                                <sf:input path="mobile" type="text" placeholder="Mobile" id="mobile" name="mobile" />
                                <sf:input path="address" type="text" placeholder="Address" id="address" name="address" />
                                <sf:textarea path="message" placeholder="Message" id="message" name="message"></sf:textarea>
                                <button type="submit" class="site-btn">Send Message</button>
                            </sf:form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="contact_map">
                    	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d664.6562077964717!2d105.77250925624543!3d21.03905153365221!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x313454b5534fb3bf%3A0x70d71b071349fa94!2sDevPro%20Vietnam!5e0!3m2!1sen!2s!4v1706894869059!5m2!1sen!2s"
                    	height="780" style="border:0;"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Contact Section End -->

    <!-- Footer -->
	<jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>

    <!-- Js Plugins -->
	<jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>
</body>

</html>