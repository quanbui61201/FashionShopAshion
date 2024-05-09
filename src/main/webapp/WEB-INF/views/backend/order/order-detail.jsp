<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" type="image/png" sizes="16x16"  href="${classpath}/backend/assets/img/favicon.png">
	<title>Admin Order Management | View Order Detail</title>
	
	<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/backend/layout/css.jsp"></jsp:include>
</head>

<body class="">
	<div class="wrapper">
		<!-- Sidebar -->
		<jsp:include page="/WEB-INF/views/backend/layout/sidebar.jsp"></jsp:include>
		
		<div class="main-panel">
			<!-- Navbar -->
			<jsp:include page="/WEB-INF/views/backend/layout/navbar.jsp"></jsp:include>
			
			<div class="content">
				<div class="row">
					<div class="col">
						<div class="card">
							<div class="card-header">
								<h3 class="title">Order Detail</h3>
							</div>
							<div class="card-body">
								<sf:form id="orderForm" class="form" modelAttribute="order">
									
									<sf:hidden path="id" />

									<div class="row">
										<div class="col-md-6 pr-md-1">
											<div class="form-group">
		                                        <label for="name">Code</label>
			                                    <sf:input path="code" type="text" class="form-control" id="code" name="code" placeholder="code"></sf:input>
                                       		</div>
										</div>
										<div class="col-md-6 pr-md-1">
											<div class="form-group">
		                                        <label for="name">Total</label>
			                                    <sf:input path="total" type="number" class="form-control" id="total" name="total" placeholder="total"></sf:input>
                                       		</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-6 pr-md-1">
											<div class="form-group">
		                                        <label for="customerName">Customer Name</label>
		                                        <sf:input path="customerName" type="text" class="form-control" id="customerName" name="customerName" placeholder="name"></sf:input>
                                       		</div>
                                    	</div>
										<div class="col-md-6 pr-md-1">
										   <div class="form-group">
											   <label for="customerMobile">Customer Mobile</label>
											   <sf:input path="customerMobile" type="text" class="form-control" id="customerMobile" name="customerMobile" placeholder="mobile"></sf:input>
											</div>
									   </div>
									</div>
									
									<div class="row">
										<div class="col-md-4 pr-md-1">
											<div class="form-group">
		                                        <label for="customerEmail">Customer Email</label>
		                                        <sf:input path="customerEmail" type="email" class="form-control" id="customerEmail" name="customerEmail" placeholder="email"></sf:input>
                                       		</div>
                                    	</div>
                        		 		<div class="col-md-8 pr-md-1">
											<div class="form-group">
		                                        <label for="customerAddress">Customer Address</label>
		                                        <sf:input path="customerAddress" type="text" class="form-control" id="customerAddress" name="customerAddress" placeholder="address"></sf:input>
                                       		</div>
                                    	</div>
									</div>
									
									<div class="row">
										<div class="col-md-3 pr-md-1">
											<div class="form-group">
		                                        <label for="email">Create by</label>
		                                        <sf:select path="userCreateSaleOrder.id" class="form-control text-primary" id="userCreateSaleOrder">
		                                            <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
		                                        </sf:select>
                                       		</div>
                                    	</div>
                        		 		<div class="col-md-3 pr-md-1">
											<div class="form-group">
		                                        <label for="address">Update by</label>
		                                        <sf:select path="userCreateSaleOrder.id" class="form-control text-primary" id="userCreateSaleOrder">
		                                            <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
		                                        </sf:select>
                                       		</div>
                                    	</div>
                        		 		<div class="col-md-3 pr-md-1">
											<div class="form-group">
		                                        <label for="createDate">Create date</label>
		                                        <sf:input path="createDate" class="form-control text-primary" type="date" 
		                                        			id="createDate" name="createDate"></sf:input>
                                       		</div>
                                    	</div>
										<div class="col-md-3 pr-md-1">
											<div class="form-group">
		                                        <label for="updateDate">Update date</label>
		                                        <sf:input path="updateDate" class="form-control text-primary" type="date" 
		                                        			id="updateDate" name="updateDate"></sf:input>
                                       		</div>
                                    	</div>
									</div>
									
									<div class="row">
                                    	<div class="col-md-4">
											<div class="form-group">
												<label for="status">&nbsp;&nbsp;&nbsp;&nbsp;</label>
												<sf:checkbox path="status" class="form-check-input" id="status" name="status"></sf:checkbox>
		                                        <label for="status">Delivered</label>
                                       		</div>
                                    	</div>
									</div>

									<div class="row">
										<div class="col-md-12 px-md-1">
											<div class="form-group file-upload">
		                                        <h5 class="title">Products</h5>
												<div class="form-group">
													<table class="table tablesorter table-hover" id="">
														<thead class=" text-primary text-nowrap">
															<tr align="center">
																<th scope="col">No.</th>
																<th scope="col">Avatar</th>
																<th scope="col">Name</th>
																<th scope="col">Size</th>
																<th scope="col">Quantity</th>
																<th scope="col">Price</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="op" items="${orderProducts }" varStatus="loop">
																<tr>
																	<td align="center">${loop.index + 1 }</td>
																	<td align="center">
																		<img width="40px" height="40px" src="${classpath }/FileUploads/${op.product.avatar }" class="light-logo">
																	</td>
																	<td>${op.product.name }</td>
																	<td align="center">${op.size }</td>
																	<td align="center">
																		<fmt:formatNumber value="${op.quantity }" minFractionDigits="0"></fmt:formatNumber>
																	</td>
																	<td align="right">
																		$<fmt:formatNumber value="${op.quantity * op.product.salePrice }" minFractionDigits="0"></fmt:formatNumber>
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</sf:form>
								
								<div class="row">
                       		 		<div class="col-md-12">
										<div class="form-group mb-4">
	                                        <a href="${classpath }/admin/order/list" class="btn btn-fill btn-primary" role="button" aria-pressed="true">
	                                        	Back to list
	                                        </a>
                                      	</div>
                                   	</div>
								</div>	
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Slider js: All Jquery-->
	<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
	
	<script>
	    $(document).ready(function() {
	    	$('#orderForm .form-group').css('pointer-events', 'none');
	    });
	</script>
	
</body>

</html>