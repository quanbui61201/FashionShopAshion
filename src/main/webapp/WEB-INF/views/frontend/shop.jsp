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
    <title>Shop</title>

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
                        <span>Shop</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Shop Section Begin -->
    <section class="shop spad">
        <form action="${classpath }/shop" method="get">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-3 col-md-3">
						<jsp:include page="/WEB-INF/views/frontend/layout/sidebar.jsp"></jsp:include>
	                </div>
	                <div class="col-lg-9 col-md-9">
	                    <div class="row">
	                        <c:forEach var="product" items="${products }" varStatus="loop">
	                            <div class="col-lg-4 col-md-6">
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
	                        
	                        <div class="col-lg-12 text-center">
	                            <div class="pagination_option">
		                            <c:if test="${productSearch.currentPage > 1}">
							            <a href="?keyword=${param.keyword}&categoryId=${param.categoryId}&productPrice=${param.productPrice}&currentPage=${productSearch.currentPage - 1}"><i class="fa fa-angle-left"></i></a>
							        </c:if>
	                                <c:forEach begin="1" end="${productSearch.totalPages}" var="pageNumber">
							            <a href="?keyword=${productSearch.keyword}&categoryId=${productSearch.categoryId}&productPrice=${productSearch.productPrice}&currentPage=${pageNumber}">${pageNumber}</a>
							        </c:forEach>
							        <c:if test="${productSearch.currentPage < productSearch.totalPages}">
							            <a href="?keyword=${param.keyword}&categoryId=${param.categoryId}&productPrice=${param.productPrice}&currentPage=${productSearch.currentPage + 1}"><i class="fa fa-angle-right"></i></a>
							        </c:if>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
    	</form>
    </section>
    <!-- Shop Section End -->

    <!-- Footer -->
	<jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>

    <!-- Js Plugins -->
	<jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>
    
    <script>
	    $( document ).ready(function() {
			//Dat gia tri cua search ung voi dieu kien search truoc do
        	$('input[name="categoryId"][value="${productSearch.categoryId}"]').prop('checked', true);
        	$('input[name="productPrice"][value="${productSearch.productPrice}"]').prop('checked', true);
			$("#keyword").val("${productSearch.keyword}");
		});
	</script>
	
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