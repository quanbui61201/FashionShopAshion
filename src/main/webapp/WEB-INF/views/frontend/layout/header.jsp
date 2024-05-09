<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas_close">+</div>
    <ul class="offcanvas_widget">
        <li><span class="icon_search search-switch"></span></li>
        <li><a href="${classpath }/wishlist">
			<span class="icon_heart_alt"></span>
			<span class="tip">${totalWishlist }</span>
		</a></li>
		<li><a href="${classpath }/cart">
			<span class="icon_bag_alt"></span>
			<span class="tip" id="totalCartProductsId">${totalCartProducts }</span>
		</a></li>
    </ul>
    <div class="offcanvas_logo">
        <a href="${classpath }/index"><img src="${classpath }/frontend/img/logo.png" alt=""></a>
    </div>
    <div id="mobile-menu-wrap"></div>
    <div class="offcanvas_auth">
        <c:choose>
            <c:when test="${isLogined }">
                <a href="#">Hello</a>
                <a href="${classpath }/logout">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="${classpath }/login">Login</a>
                <a href="${classpath }/signup">Register</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<!-- Offcanvas Menu End -->

<!-- Header Section Begin -->
<header class="header">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-3 col-lg-2">
                <div class="header_logo">
                    <a href="${classpath }/index"><img src="${classpath }/frontend/img/logo.png" alt=""></a>
                </div>
            </div>
            <div class="col-xl-6 col-lg-7">
                <nav class="header_menu">
                    <ul>
                        <li><a href="${classpath }/index">Home</a></li>
                        <li><a href="${classpath }/shop?categoryId=18">Women’s</a></li>
                        <li><a href="${classpath }/shop?categoryId=19">Men’s</a></li>
                        <li id="btnShop"><a href="${classpath }/shop">Shop</a>
                        	<ul class="dropdown">
                        		<c:forEach var="category" items="${categories }" varStatus="loop">
                        			<li><a href="${classpath }/shop?categoryId=${category.id}">${category.name }</a></li>
                        		</c:forEach>
                        	</ul>
                        </li>
                        <li><a href="${classpath }/contact">Contact</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">
                <div class="header_right">
                    <div class="header_right_auth">
                        <c:choose>
                            <c:when test="${isLogined }">
                                <a href="#">Hello</a>
                                <a href="${classpath }/logout">Logout</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${classpath }/login">Login</a>
                                <a href="${classpath }/signup">Register</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <ul class="header_right_widget">
                        <li><span class="icon_search search-switch"></span></li>
                        <li><a href="${classpath }/wishlist">
                        	<span class="icon_heart_alt"></span>
                            <span class="tip">${totalWishlist }</span>
                        </a></li>
                        <li><a href="${classpath }/cart">
                        	<span class="icon_bag_alt"></span>
                            <span class="tip" id="totalCartProducts">${totalCartProducts }</span>
                        </a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="canvas_open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>
<!-- Header Section End -->

<!-- Search Begin -->
<div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form id="search-form" action="${classpath }/shop" method="get" class="search-model-form">
            <input type="text" id="search-input"  name="keyword" placeholder="Search here.....">
        </form>
    </div>
</div>
<!-- Search End -->

<script>
    document.getElementById("search-input").addEventListener("keypress", function(event) {
        if (event.keyCode === 13) { // 13 là mã ASCII của phím Enter
            event.preventDefault(); // Ngăn chặn hành động mặc định của nút Enter
            var form = document.getElementById("search-form");
            form.submit(); // Gửi yêu cầu tìm kiếm
        }
    });
</script>