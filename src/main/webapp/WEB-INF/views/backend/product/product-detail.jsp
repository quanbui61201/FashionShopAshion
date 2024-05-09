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
	<title>Admin Product Management | View Product Detail</title>
	
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
								<h3 class="title">Product Detail</h3>
							</div>
							<div class="card-body">
								<sf:form id="productForm" class="form" modelAttribute="product">
									
									<sf:hidden path="id" />

									<div class="row">
										<div class="col-md-6 pr-md-1">
											<div class="form-group">
												<label for="role">Category</label>
												<sf:select path="category.id" class="form-control text-primary" id="category" style="margin-right: 10px;">
													<sf:options items="${categories }" itemValue="id" itemLabel="name"></sf:options>
												</sf:select>
											</div>
										</div>
										<div class="col-md-6 px-md-1">
											<div class="form-group">
		                                        <label for="name">Product name</label>
			                                    <sf:input path="name" type="text" class="form-control" id="name" name="name" placeholder="product name"></sf:input>
                                       		</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-6 pr-md-1">
											<div class="form-group">
		                                        <label for="price">Price</label>
			                                    <sf:input path="price" type="number" autocomplete="off" id="price" name="price" class="form-control" placeholder="price"></sf:input>
                                       		</div>
                                    	</div>
                        		 		<div class="col-md-6 pr-md-1">
											<div class="form-group">
		                                        <label for="salePrice">Sale price</label>
			                                    <sf:input path="salePrice" type="number" autocomplete="off" id="salePrice" name="salePrice" class="form-control" placeholder="Sale price"></sf:input>
                                       		</div>
                                    	</div>
									</div>
									
									<div class="row">
										<div class="col-md-3 pr-md-1">
											<div class="form-group">
		                                        <label for="email">Create by</label>
		                                        <sf:select path="userCreateProduct.id" class="form-control text-primary" id="userCreateProduct">
		                                            <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
		                                        </sf:select>
                                       		</div>
                                    	</div>
                        		 		<div class="col-md-3 pr-md-1">
											<div class="form-group">
		                                        <label for="address">Update by</label>
		                                        <sf:select path="userUpdateProduct.id" class="form-control text-primary" id="userUpdateProduct">
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
										<div class="col-md-8">
											<div class="form-group">
												<label for="shortDescription">Description</label>
												<sf:textarea path="shortDescription" id="shortDescription" name="shortDescription"
													rows="4" cols="80" class="form-control" placeholder="description"></sf:textarea>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-8">
											<div class="form-group">
												<label for="detailDescription">Detail</label>
												<sf:textarea path="detailDescription" id="detailDescription" name="detailDescription"
													rows="4" cols="80" class="form-control" placeholder="detail"></sf:textarea>
											</div>
										</div>
									</div>
									
									<div class="row">
                                    	<div class="col-md-4">
											<div class="form-group">
												<label for="isHot">&nbsp;&nbsp;&nbsp;&nbsp;</label>
												<sf:checkbox path="isHot" class="form-check-input" id="isHot" name="isHot"></sf:checkbox>
		                                        <label for="isHot">Is a hot product?</label>
                                       		</div>
                                    	</div>
                                    	<div class="col-md-4">
											<div class="form-group">
												<label for="status">&nbsp;&nbsp;&nbsp;&nbsp;</label>
												<sf:checkbox path="status" class="form-check-input" id="status" name="status"></sf:checkbox>
		                                        <label for="status">Active</label>
                                       		</div>
                                    	</div>
									</div>

									<div class="row">
										<div class="col-md-3 px-md-1">
											<div class="form-group file-upload">
		                                        <label for="avatarFile">Avatar</label>
												<div class="form-group">
													<img height="200px" width="auto" src="${classpath }/FileUploads/${product.avatar }" class="light-logo">
												</div>
											</div>
										</div>
										<div class="col-md-9 px-md-1">
											<div class="form-group file-upload">
		                                        <label for="image">Images</label>
												<div class="form-group">
													<div class="row">
														<c:forEach var="image" items="${product.images }" varStatus="loop">
															<div class="col-md-2 mb-2">
																<img height="100px" width="auto" src="${classpath }/FileUploads/${image.path }" class="light-logo">
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
									</div>
								</sf:form>
								
								<div class="row">
                       		 		<div class="col-md-12">
										<div class="form-group mb-4">
	                                        <a href="${classpath }/admin/product/list" class="btn btn-fill btn-primary" role="button" aria-pressed="true">
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
	    	$('#productForm .form-group').css('pointer-events', 'none');
	    });
	</script>
	
</body>

</html>