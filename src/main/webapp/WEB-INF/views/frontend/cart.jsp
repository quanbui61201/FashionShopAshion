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
    <title>Cart</title>

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
                        <span>Shopping cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shop_cart_table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Size</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${cart.cartProducts }" varStatus="loop">
                                    <tr>
                                        <td class="cart_product_item">
                                            <img src="${classpath}/FileUploads/${item.avatar }" alt="" width="90px" height="90px">
                                            <div class="cart_product_item_title">
                                                <a href="${classpath}/product/${item.productId}" class="h6">${item.productName }</a>
                                                <div class="rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="cart_size">${item.size}</td>
                                        <td class="cart_price">
                                            $ <fmt:formatNumber value="${item.price }" minFractionDigits="0" />
                                        </td>
                                        <td class="cart_quantity">
                                            <div class="pro-qty">
                                            	<span class="dec qtybtn" onclick="updateProductQuantity(${item.productId }, -1)">-</span>
                                                <input type="text" value="${item.quantity }">
                                                <span class="inc qtybtn" onclick="updateProductQuantity(${item.productId }, 1)">+</span>
                                            </div>
                                        </td>
                                        <td class="cart_total">
                                            $ <fmt:formatNumber value="${item.price * item.quantity }" minFractionDigits="0" />
                                        </td>
                                        <td class="cart_close">
                                            <a href="${classpath }/product-cart-delete/${item.productId }">
                                                <span class="icon_close"></span>
											</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart_btn">
                        <a href="${classpath}/shop">Continue Shopping</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="discount_content">
                        <h6>Discount codes</h6>
                        <form action="#">
                            <input type="text" placeholder="Enter your coupon code">
                            <button type="submit" class="site-btn">Apply</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <div class="cart_total_procced">
                        <h6>Cart total</h6>
                        <ul>
                            <li>Subtotal <span>$ <fmt:formatNumber value="${totalCartPrice }" minFractionDigits="0" /></span></li>
                            <li>Total <span>$ <fmt:formatNumber value="${totalCartPrice }" minFractionDigits="0" /></span></li>
                        </ul>
                        <c:choose>
						    <c:when test="${totalCartPrice > 0}">
						        <a href="${classpath}/checkout" class="primary-btn">Proceed to checkout</a>
						    </c:when>
						    <c:otherwise>
						        <a class="primary-btn" style="pointer-events:none">Proceed to checkout</a>
						    </c:otherwise>
						</c:choose>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->

    <!-- Footer -->
	<jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>

    <!-- Js Plugins -->
	<jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>

    <script type="text/javascript">
		updateProductQuantity = function(_productId, _quantity) {
			let data = {
				productId : _productId,
				quantity : _quantity
			};

			//$ === jQuery
			jQuery.ajax({
				url : "/update-product-quantity",
				type : "POST",
				contentType : "application/json",
				data : JSON.stringify(data),
				dataType : "json",

				success : function(jsonResult) {
					let totalProducts = jsonResult.totalCartProducts;
					$("#productQuantity_" + jsonResult.productId).html(jsonResult.newQuantity);
					window.location.reload();
				},

				error : function(jqXhr, textStatus, errorMessage) {
					alert("An error occur");
				}
			});
		}
	</script>
</body>

</html>