<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="icon" type="image/png" sizes="16x16"  href="${classpath}/backend/assets/img/favicon.png">
    <title>Checkout</title>

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

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <form action="#" class="checkout_form">
                <div class="row">
                    <div class="col-lg-8">
                        <h5>Billing detail</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout_form_input">
                                    <p>Customer Name <span>*</span></p>
                                    <input type="text" id="name" name="name" placeholder="Your name" class="form-control" value="${loginedUser.name }" />
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout_form_input">
                                    <p>Customer Mobile <span>*</span></p>
                                    <input type="text" id="mobile" name="mobile" placeholder="Your phone number" class="form-control" value="${loginedUser.mobile }" />
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="checkout_form_input">
                                    <p>Customer Email <span>*</span></p>
                                    <input type="email"  id="email" name="email" placeholder="Your email" class="form-control" value="${loginedUser.email }" />
                                </div>
                                <div class="checkout_form_input">
                                    <p>Customer Address <span>*</span></p>
                                    <input type="text" id="address" name="address" placeholder="Your address" class="form-control" value="${loginedUser.address }" />
                                </div>
                                <div class="checkout_form_input">
                                    <p>Order notes</p>
                                    <input type="text" id="message" name="message" placeholder="Note about your order, e.g, special noe for delivery">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="checkout_order">
                            <h5>Your order</h5>
                            <div class="checkout_order_product">
                                <ul>
                                    <li>
                                        <span class="top_text">Product</span>
                                        <span class="top_text_right">Total</span>
                                    </li>
                                    <c:forEach var="item" items="${cart.cartProducts }" varStatus="loop">
                                        <li>${loop.index + 1 }. ${item.productName }. Size: ${item.size }
                                            <span>
                                                $<fmt:formatNumber value="${item.price * item.quantity }" minFractionDigits="0" />
                                            </span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="checkout_order_total">
                                <ul>
                                    <li>Subtotal <span>$<fmt:formatNumber value="${totalCartPrice }" minFractionDigits="0" /></span></li>
                                    <li>Total <span>$<fmt:formatNumber value="${totalCartPrice }" minFractionDigits="0" /></span></li>
                                </ul>
                            </div>
                            <button type="button" onclick="_placeOrder()" class="site-btn">Place order</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <!-- Checkout Section End -->

	<!-- Success Modal -->
	<div id="successModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box">
						<i class="material-icons">&#xE876;</i>
					</div>
					<h4 class="modal-title w-100">Awesome!</h4>	
				</div>
				<div class="modal-body">
					<p class="text-center">You have placed your order successfully. Check your email for details.</p>
				</div>
				<div class="modal-footer">
					<a href="${classpath }/index" class="btn btn-success btn-block">Back to Shop</a>
				</div>
			</div>
		</div>
	</div>
	
    <!-- Footer -->
    <jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>

    <!-- Js Plugins -->
    <jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>

    <script type="text/javascript">
		function _placeOrder() {
			//javascript object
			let data = {				
				name : jQuery("#name").val(),
				email : jQuery("#email").val(),
				mobile : jQuery("#mobile").val(),
				address : jQuery("#address").val(),
			};
			
			//$ === jQuery
			jQuery.ajax({
				url : "/place-order",
				type : "POST",
				contentType: "application/json",
				data : JSON.stringify(data),
				dataType : "json",
				
				success : function(jsonResult) {
	                jQuery("#successModal").modal("show");
				},
				
				error : function(jqXhr, textStatus, errorMessage) {
					alert("An error occur");
				}
			});
		}
	</script>
</body>

</html>