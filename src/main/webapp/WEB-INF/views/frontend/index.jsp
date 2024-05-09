<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="16x16"  href="${classpath}/backend/assets/img/favicon.png">
    <title>Home Page</title>

	<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>

    <!-- Css Styles -->
    <jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>
</head>

<body>
    <!-- Header -->
	<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>

    <!-- Categories Section Begin -->
    <section class="categories">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6 p-0">
                    <div class="categories_item categories_large_item set-bg" data-setbg="${classpath }/frontend/img/categories/category-1.jpg">
                        <div class="categories_text">
                            <h1>Women’s fashion</h1>
                            <p>Sitamet, consectetur adipiscing elit, sed do eiusmod tempor incidid-unt labore
                            edolore magna aliquapendisse ultrices gravida.</p>
                            <a href="${classpath }/shop?categoryId=18">Shop now</a>
                        </div>
                    </div>
                </div>
	            <div class="col-lg-6">
	                <div class="row">
	                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
	                        <div class="categories_item set-bg" data-setbg="${classpath }/frontend/img/categories/category-2.jpg">
	                            <div class="categories_text">
	                                <h4>Men’s fashion</h4>
	                                <p>${menTotal} items</p>
	                                <a href="${classpath }/shop?categoryId=19">Shop now</a>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
	                        <div class="categories_item set-bg" data-setbg="${classpath }/frontend/img/categories/category-3.jpg">
	                            <div class="categories_text">
	                                <h4>Kid’s fashion</h4>
	                                <p>${kidTotal} items</p>
	                                <a href="${classpath }/shop?categoryId=20">Shop now</a>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
	                        <div class="categories_item set-bg" data-setbg="${classpath }/frontend/img/categories/category-4.jpg">
	                            <div class="categories_text">
	                                <h4>Cosmetics</h4>
	                                <p>${cosmeticTotal} items</p>
	                                <a href="${classpath }/shop?categoryId=21">Shop now</a>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
	                        <div class="categories_item set-bg" data-setbg="${classpath }/frontend/img/categories/category-5.jpg">
	                            <div class="categories_text">
	                                <h4>Accessories</h4>
	                                <p>${accessoryTotal} items</p>
	                                <a href="${classpath }/shop?categoryId=22">Shop now</a>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	    	</div>
        </div>
    </section>
    <!-- Categories Section End -->

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-4">
                    <div class="section-title">
                        <h4>New product</h4>
                    </div>
                </div>
                <div class="col-lg-8 col-md-8">
                    <ul class="filter_controls">
                        <li class="active" data-filter="*">All</li>
                        <c:forEach var="category" items="${categories }" varStatus="loop">
                        	<li data-filter=".category-${category.id }">${category.name }</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="row property_gallery">
				<c:set var="count" value="0" />
            	<c:forEach var="product" items="${products }" varStatus="loop">
            		<c:if test="${count < 8 && (empty filter || fn:contains(filter, product.category.id))}">
		                <div class="col-lg-3 col-md-4 col-sm-6 mix category-${product.category.id }">
		                    <div class="product_item">
		                        <div class="product_item_pic set-bg" data-setbg="${classpath }/FileUploads/${product.avatar }">
		                        	<c:choose>
			                        	<c:when test="${product.isHot }">
			                        		<div class="label sale">Hot</div>
			                        	</c:when>
		                        	</c:choose>
		                            <ul class="product_hover">
		                                <li><a href="${classpath }/FileUploads/${product.avatar }" class="image-popup"><span class="arrow_expand"></span></a></li>
		                                <li>
	                                        <a onclick="addToWishlist(${product.id})">
	                                        	<span class="icon_heart_alt"></span>
	                                        </a>
	                                    </li>
	                                    <li>
	                                        <a onclick="addToCart(${product.id}, 1, '${product.name }')">
	                                            <span class="icon_bag_alt"></span>
	                                    	</a>
	                                	</li>
		                            </ul>
		                        </div>
		                        <div class="product_item_text">
		                            <h6><a href="${classpath }/product/${product.id}" title="${product.name}">${product.name }</a></h6>
		                            <div class="rating">
		                                <i class="fa fa-star"></i>
		                                <i class="fa fa-star"></i>
		                                <i class="fa fa-star"></i>
		                                <i class="fa fa-star"></i>
		                                <i class="fa fa-star"></i>
		                            </div>
		                            <div class="product_price">
	                                    $ <fmt:formatNumber value="${product.salePrice }" minFractionDigits="0"></fmt:formatNumber>
	                                </div>
		                        </div>
		                    </div>
		                </div>
        				<c:set var="count" value="${count + 1}" />
	                </c:if>
	            </c:forEach>
            </div>
        </div>
    </section>
    <!-- Product Section End -->

    <!-- Banner Section Begin -->
    <section class="banner set-bg" data-setbg="${classpath }/frontend/img/banner/banner-1.jpg">
        <div class="container">
            <div class="row">
                <div class="col-xl-7 col-lg-8 m-auto">
                    <div class="banner_slider owl-carousel">
                        <div class="banner_item">
                            <div class="banner_text">
                                <span>The Chloe Collection</span>
                                <h1>The Project Jacket</h1>
                                <a href="${classpath}/shop">Shop now</a>
                            </div>
                        </div>
                        <div class="banner_item">
                            <div class="banner_text">
                                <span>The Chloe Collection</span>
                                <h1>The Project Jacket</h1>
                                <a href="${classpath}/shop">Shop now</a>
                            </div>
                        </div>
                        <div class="banner_item">
                            <div class="banner_text">
                                <span>The Chloe Collection</span>
                                <h1>The Project Jacket</h1>
                                <a href="${classpath}/shop">Shop now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Section End -->

    <!-- Trend Section Begin -->
    <section class="trend spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="trend_content">
                        <div class="section-title">
                            <h4>Hot Trend</h4>
                        </div>
                        <c:forEach var="product" items="${hotTrend }" varStatus="loop">
	                        <div class="trend_item">
	                            <div class="trend_item_pic">
	                                <img src="${classpath}/FileUploads/${product.avatar }" alt="">
	                            </div>
	                            <div class="trend_item_text">
	                                <a href="${classpath}/product/${product.id }">${product.name }</a>
	                                <div class="rating">
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                </div>
	                                <div class="product_price">
	                                	$ <fmt:formatNumber value="${product.salePrice }" minFractionDigits="0"></fmt:formatNumber>
	                                </div>
	                            </div>
	                        </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="trend_content">
                        <div class="section-title">
                            <h4>Best seller</h4>
                        </div>
                        <c:forEach var="product" items="${bestSaller }" varStatus="loop">
	                        <div class="trend_item">
	                            <div class="trend_item_pic">
	                                <img src="${classpath}/FileUploads/${product.avatar }" alt="">
	                            </div>
	                            <div class="trend_item_text">
	                                <a href="${classpath}/product/${product.id }">${product.name }</a>
	                                <div class="rating">
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                </div>
	                                <div class="product_price">
	                                	$ <fmt:formatNumber value="${product.salePrice }" minFractionDigits="0"></fmt:formatNumber>
	                                </div>
	                            </div>
	                        </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="trend_content">
                        <div class="section-title">
                            <h4>Feature</h4>
                        </div>
                        <c:forEach var="product" items="${feature }" varStatus="loop">
	                        <div class="trend_item">
	                            <div class="trend_item_pic">
	                                <img src="${classpath}/FileUploads/${product.avatar }" alt="">
	                            </div>
	                            <div class="trend_item_text">
	                                <a href="${classpath}/product/${product.id }">${product.name }</a>
	                                <div class="rating">
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                    <i class="fa fa-star"></i>
	                                </div>
	                                <div class="product_price">
	                                	$ <fmt:formatNumber value="${product.salePrice }" minFractionDigits="0"></fmt:formatNumber>
	                                </div>
	                            </div>
	                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Trend Section End -->

    <!-- Discount Section Begin -->
    <section class="discount">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 p-0">
                    <div class="discount_pic">
                        <img src="${classpath}/frontend/img/discount.jpg" alt="">
                    </div>
                </div>
                <div class="col-lg-6 p-0">
                    <div class="discount_text">
                        <div class="discount_text_title">
                            <span>Discount</span>
                            <h2>Summer 2024</h2>
                            <h5><span>Sale</span> 50%</h5>
                        </div>
                        <div class="discount_countdown" id="countdown-time">
                            <div class="countdown_item">
                                <span>22</span>
                                <p>Days</p>
                            </div>
                            <div class="countdown_item">
                                <span>18</span>
                                <p>Hour</p>
                            </div>
                            <div class="countdown_item">
                                <span>46</span>
                                <p>Min</p>
                            </div>
                            <div class="countdown_item">
                                <span>05</span>
                                <p>Sec</p>
                            </div>
                        </div>
                        <a href="${classpath }/shop">Shop now</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Discount Section End -->

    <!-- Services Section Begin -->
    <section class="services spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="services_item">
                        <i class="fa fa-car"></i>
                        <h6>Free Shipping</h6>
                        <p>For all oder over $99</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="services_item">
                        <i class="fa fa-money"></i>
                        <h6>Money Back Guarantee</h6>
                        <p>If good have Problems</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="services_item">
                        <i class="fa fa-support"></i>
                        <h6>Online Support 24/7</h6>
                        <p>Dedicated support</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="services_item">
                        <i class="fa fa-headphones"></i>
                        <h6>Payment Secure</h6>
                        <p>100% secure payment</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Services Section End -->

    <!-- Instagram Begin -->
    <div class="instagram">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram_item set-bg" data-setbg="${classpath }/frontend/img/instagram/insta-1.jpg">
                        <div class="instagram_text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram_item set-bg" data-setbg="${classpath }/frontend/img/instagram/insta-2.jpg">
                        <div class="instagram_text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram_item set-bg" data-setbg="${classpath }/frontend/img/instagram/insta-3.jpg">
                        <div class="instagram_text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram_item set-bg" data-setbg="${classpath }/frontend/img/instagram/insta-4.jpg">
                        <div class="instagram_text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram_item set-bg" data-setbg="${classpath }/frontend/img/instagram/insta-5.jpg">
                        <div class="instagram_text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram_item set-bg" data-setbg="${classpath }/frontend/img/instagram/insta-6.jpg">
                        <div class="instagram_text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Instagram End -->

    <!-- Footer -->
	<jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>

    <!-- Js Plugins -->
	<jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>
	
    <script>
    	addToWishlist = function(_productId) {		
			//$ === jQuery
			jQuery.ajax({
				url : "/add-to-wishlist/" + _productId,
				type : "POST",
				contentType: "application/json",
				
				success : function(jsonResult) {
					alert(jsonResult.code + ": " + jsonResult.message);
				},
				
				error : function(jqXhr, textStatus, errorMessage) {
					alert(jqXhr.status + ': Đã có lỗi xay ra...!')
				},
			});
		}
	</script>
	
    <script>
		addToCart = function(_productId, _quantity, _productName) {		
			let data = {
				productId: _productId,
				quantity: _quantity,
				productName: _productName,
			};
				
			//$ === jQuery
			jQuery.ajax({
				url : "/add-to-cart",
				type : "POST",
				contentType: "application/json",
				data : JSON.stringify(data),
				dataType : "json",
				
				success : function(jsonResult) {
					alert(jsonResult.code + ": " + jsonResult.message);
					let totalProducts = jsonResult.totalCartProducts;
					$("#totalCartProductsId").html(totalProducts);
					$("#totalCartProducts").html(totalProducts);
				},
				
				error : function(jqXhr, textStatus, errorMessage) {
					alert(jqXhr.status + ': Đã có lỗi xay ra...!')
				},
			});
		}
	</script>
</body>

</html>