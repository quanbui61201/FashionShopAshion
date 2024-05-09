<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" type="image/png" sizes="16x16"  href="${classpath}/backend/assets/img/favicon.png">
	<title>Admin Contact Management</title>
	
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
					<div class="col-md-12">
						<div class="card  card-plain">
							<div class="card-header">
								<h4 class="card-title">Contact List</h4>
							</div>
							<div class="card-body">
								<form action="${classpath }/admin/contact/list" method="get">
									<div class="table-responsive">
										<!-- ========================= Add - Page ========================= -->
										<div class="row">
											<div class="col-md-2">
												<div class="form-group mb-4">
													<a href="${classpath }/admin/contact/add" role="button" class="btn btn-primary text-nowrap">Create Contact</a>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group mb-4">
													<h3>Total contacts: &nbsp ${contactSearch.totalItems }</h3>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group mb-4 invisible">
													<label>Current page</label> <input id="currentPage" name="currentPage"
														class="form-control" value="${contactSearch.currentPage }">
												</div>
											</div>
										</div>
										<!-- =========================== Search =========================== -->
										<div class="row">
											<div class="col-md-2">
												<div class="form-group mb-4">
													<select class="form-control text-primary" id="status" name="status">
														<option value="2">All</option>
														<option value="1">Active</option>
														<option value="0">Inactive</option>
													</select>
												</div>
											</div>

											<div class="col-md-2">
												<input class="form-control text-primary" type="date" id="beginDate" name="beginDate" />
											</div>
											
											<div class="col-md-2">
												<input class="form-control text-primary" type="date" id="endDate" name="endDate" />
											</div>
											
											<div class="col-md-2">
												<input type="text" class="form-control text-primary" id="keyword" name="keyword" placeholder="Search keyword" />
											</div>

											<div class="col-md-1">
												<button type="submit" id="btnSearch" name="btnSearch" class="btn btn-primary">Search</button>
											</div>
										</div>
										<!-- ========================= Data Table ========================= -->
										<table class="table tablesorter table-hover" id="">
											<thead class=" text-primary text-nowrap">
												<tr align="center">
													<th scope="col">No.</th>
													<th scope="col">Id</th>
													<th scope="col">Full Name</th>
													<th scope="col">Mobile</th>
													<th scope="col">Email</th>
													<th scope="col">Address</th>
													<th scope="col">Create by</th>
													<th scope="col">Create date</th>
													<th scope="col">Status</th>
													<th scope="col">Actions</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="contact" items="${contacts }" varStatus="loop">
													<tr>
														<td>${loop.index + 1 }</td>
														<td>${contact.id }</td>
														<td>${contact.name }</td>
														<td align="center">${contact.mobile }</td>
														<td>${contact.email }</td>
														<td>${contact.address }</td>
														<td>${contact.userCreateContact.username }</td>
														<td align="center"><fmt:formatDate value="${contact.createDate }" pattern="dd-MM-yyyy" /></td>
														<td align="center">
															<c:choose>
																<c:when test="${contact.status }">
																	<span>Active</span>
																</c:when>
																<c:otherwise>
																	<span>Inactive</span>
																</c:otherwise>
															</c:choose>
														</td>
														<td class="text-center">
															<div class="btn-group" role="group" aria-label="Actions">
																<a href="${classpath }/admin/contact/detail/${contact.id }" role="button" class="btn btn-link">
																	<i class="tim-icons icon-zoom-split text-success"></i>
																</a>
																<a href="${classpath }/admin/contact/edit/${contact.id }" role="button" class="btn btn-link">
																	<i class="tim-icons icon-pencil text-warning"></i>
																</a>
																<a role="button" class="btn btn-link" data-toggle="modal" data-target="#modalConfirmDelete" data-contact-id="${contact.id}">
																	<i class="tim-icons icon-simple-remove text-danger"></i>
																</a>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<!-- ========================= Pagination ========================= -->
										<div class="row mt-3">
											<div class="col-md-12">
												<div class="pagination float-right">
													<div id="paging"></div>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/backend/layout/delete-modal.jsp"></jsp:include>
	
	<!-- Slider js: All Jquery-->
	<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
	
	<script>
	    $('#modalConfirmDelete').on('show.bs.modal', function (event) {
	        var button = $(event.relatedTarget);
	        var contactId = button.data('contact-id');
	        var modal = $(this);
	        modal.find('#deleteLink').attr('href', '${classpath}/admin/contact/delete/' + contactId);
	    });
	</script>
	
	<!-- pagination -->
	<script type="text/javascript">
		$( document ).ready(function() {
			//Dat gia tri cua search ung voi dieu kien search truoc do
			$("#status").val("${contactSearch.status}");
			$("#keyword").val("${contactSearch.keyword}");
			$("#beginDate").val("${contactSearch.beginDate}");
			$("#endDate").val("${contactSearch.endDate}");
			
			$("#paging").pagination({
				currentPage: ${contactSearch.currentPage},
				items: ${contactSearch.totalItems},
				itemsOnPage: ${contactSearch.sizeOfPage},
				cssStyle: 'light-theme',
				onPageClick: function(pageNumber, event) {
					$('#currentPage').val(pageNumber);
					$('#btnSearch').trigger('click');
				},
			});
		});
	</script>
</body>

</html>