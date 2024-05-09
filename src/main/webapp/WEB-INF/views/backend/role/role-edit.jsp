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
	<title>Admin Role Management | Edit Role</title>
	
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
								<h3 class="title">Edit Role</h3>
							</div>
							<div class="card-body">
								<sf:form class="form" action="${classpath }/admin/role/edit-save" method="post" modelAttribute="role" enctype="multipart/form-data">
									
									<sf:hidden path="id"/>
									
									<div class="row">
										<div class="col-md-4 pr-md-1">
											<div class="form-group">
		                                        <label for="name">Role Name</label>
		                                        <sf:input path="name" type="text" class="form-control" id="name" name="name" placeholder="name"></sf:input>
                                       		</div>
                                    	</div>
									</div>
									
									<div class="row">
										<div class="col-md-3 pr-md-1">
											<div class="form-group">
		                                        <label for="email">Create by</label>
		                                        <sf:select path="userCreateRole.id" class="form-control text-primary" id="userCreateRole">
		                                            <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
		                                        </sf:select>
                                       		</div>
                                    	</div>
                        		 		<div class="col-md-3 pr-md-1">
											<div class="form-group">
		                                        <label for="address">Update by</label>
		                                        <sf:select path="userUpdateRole.id" class="form-control text-primary" id="userUpdateRole">
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
												<label for="description">Description</label>
												<sf:textarea path="description" id="description" name="description"
													rows="4" cols="80" class="form-control" placeholder="description"></sf:textarea>
											</div>
										</div>
									</div>
									
									<div class="row">
                                    	<div class="col-md-4">
											<div class="form-group">
												<label for="status">&nbsp;&nbsp;&nbsp;&nbsp;</label>
												<sf:checkbox path="status" class="form-check-input" id="status" name="status"></sf:checkbox>
		                                        <label for="status">Active</label>
                                       		</div>
                                    	</div>
									</div>
										
									<div class="row">
                        		 		<div class="col-md-12">
											<div class="form-group mb-4">
		                                        <a href="${classpath }/admin/role/list" class="btn btn-fill btn-primary" role="button" aria-pressed="true">
		                                        	Back to list
		                                        </a>
                                   				<button type="submit" class="btn btn-fill btn-primary">Save</button>
                                       		</div>
                                    	</div>
									</div>
										
								</sf:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Slider js: All Jquery-->
	<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
	
</body>

</html>