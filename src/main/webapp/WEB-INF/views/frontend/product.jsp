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
    <title>Product Detail</title>

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
                        <a href="${classpath}/index"><i class="fa fa-home"></i> Home</a>
                        <a href="${classpath}/shop">Shop </a>
                        <span>Essential structured blazer</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <form class="form-inline" action="${classpath }/product" method="get">
                    <div class="col-lg-6">
                        <div class="product_details_pic">
                            <div class="product_details_pic_left product_thumb nice-scroll">
                                <c:forEach items="${productImages }" var="productImage">
                                    <a class="pt active" href="#product-${productImage.id}">
                                        <img src="${classpath }/FileUploads/${productImage.path }" alt="">
                                    </a>
                                </c:forEach>
                            </div>
                            <div class="product_details_slider_content">
                                <div class="product_details_pic_slider owl-carousel" style="max-width:413px; max-height:550px">
                                    <c:forEach items="${productImages }" var="productImage">
                                        <img data-hash="product-${productImage.id}" class="product_big_img" src="${classpath }/FileUploads/${productImage.path }" alt="">
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="product_details_text">
                            <h3>${product.name } <span>Brand:</span></h3>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <span>( 138 reviews )</span>
                            </div>
                            <div class="product_details_price">
                                <fmt:formatNumber value="${product.salePrice }" minFractionDigits="0"></fmt:formatNumber><sup>$</sup>
                            </div>
                            <p>${product.shortDescription }</p>
                            <div class="product_details_button">
                                <div class="quantity">
                                    <span>Quantity:</span>
                                    <div class="pro-qty">
                                    	<span class="dec qtybtn">-</span>
                                        <input type="text" id="quantity" name="quantity" value="1">
                                        <span class="inc qtybtn">+</span>
                                    </div>
                                </div>
                                <a class="cart-btn" onclick="addToCart(${product.id }, '${product.name }')">
                                    <span class="icon_bag_alt"></span> Add to cart
                                </a>
                                <c:choose>
                                	<c:when test="${isLogined }">
		                                <ul>
		                                    <li><a onclick="addToWishlist(${product.id })">
		                                    	<span class="icon_heart_alt"></span>
		                                    </a></li>
		                                </ul>
                                	</c:when>
                                </c:choose>
                            </div>
                            <div class="product_details_widget">
                                <ul>
                                    <li>
                                        <span>Available size:</span>
                                        <div class="size_btn">
                                            <label for="xxs-size">
                                                <input type="radio" id="xxs-size" name="size" value="XXS">xxs
                                            </label>
                                            <label for="xs-size">
                                                <input type="radio" id="xs-size" name="size" value="XS">xs
                                            </label>
                                            <label for="s-size">
                                                <input type="radio" id="s-size" name="size" value="S">s
                                            </label>
                                            <label for="m-size" class="active">
                                                <input type="radio" id="m-size" name="size" value="M">m
                                            </label>
                                            <label for="l-size">
                                                <input type="radio" id="l-size" name="size" value="L">l
                                            </label>
                                            <label for="xl-size">
                                                <input type="radio" id="xl-size" name="size" value="XL">xl
                                            </label>
                                        </div>
                                    </li>
                                    <li>
                                        <span>Promotions:</span>
                                        <p>Free shipping</p>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="product_details_tab">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab">Description</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab">Specification</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab">Reviews ( 2 )</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                    <h6>Description</h6>
                                    <p>${product.detailDescription}</p>
                                </div>
                                <div class="tab-pane" id="tabs-2" role="tabpanel">
                                    <h6>Specification</h6>
                                    <p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed
                                        quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt loret.
                                        Neque porro lorem quisquam est, qui dolorem ipsum quia dolor si. Nemo enim ipsam
                                        voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed quia ipsu
                                        consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Nulla
                                    consequat massa quis enim.</p>
                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
                                        dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
                                        nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium
                                    quis, sem.</p>
                                </div>
                                <div class="tab-pane" id="tabs-3" role="tabpanel">
                                    <h6>Reviews ( 2 )</h6>
                                    <p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed
                                        quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt loret.
                                        Neque porro lorem quisquam est, qui dolorem ipsum quia dolor si. Nemo enim ipsam
                                        voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed quia ipsu
                                        consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Nulla
                                    consequat massa quis enim.</p>
                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
                                        dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
                                        nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium
                                    quis, sem.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="related_title">
                        <h5>RELATED PRODUCTS</h5>
                    </div>
                </div>
                <c:forEach var="product" items="${relatedProducts }" varStatus="loop">
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="product_item">
                            <div class="product_item_pic set-bg" data-setbg="${classpath }/FileUploads/${product.avatar }">
			                   	<c:choose>
			                    	<c:when test="${product.isHot }">
			                    		<div class="label sale">Hot</div>
			                    	</c:when>
			                   	</c:choose>
                                <ul class="product_hover">
                                    <li>
                                        <a href="${classpath }/FileUploads/${product.avatar }" class="image-popup">
                                            <span class="arrow_expand"></span>
                                        </a>
                                    </li>
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
                                    $<fmt:formatNumber value="${product.salePrice }" minFractionDigits="0"></fmt:formatNumber>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->

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
	
	<script type="text/javascript">
		addToCart = function(_productId, _productName) {		
			let data = {
				productId: _productId, //lay theo id
				quantity: jQuery("#quantity").val(),
				productName: _productName,
				size: $('input[name=size]:checked').val(),
			};
				
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
	                console.error(errorMessage);
	                alert("Đã có lỗi xảy ra...");
				},
			});
		}
	</script>
</body>

</html>