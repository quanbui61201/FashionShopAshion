<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" type="image/png" sizes="16x16"  href="${classpath}/backend/assets/img/favicon.png">
	<title>Dashboard</title>
	
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
					<div class="col-12">
						<div class="card card-chart">
							<div class="card-header ">
								<div class="row">
									<div class="col-sm-6 text-left">
										<h5 class="card-category">Total Shipments</h5>
										<h2 class="card-title">Statistic</h2>
									</div>
									<div class="col-sm-6">
										<div class="btn-group btn-group-toggle float-right" data-toggle="buttons">
											<label class="btn btn-sm btn-primary btn-simple active" id="revenue-chart">
												<input type="radio" name="options" checked>
												<span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Revenue</span>
												<span class="d-block d-sm-none"> <i class="tim-icons icon-single-02"></i></span>
											</label>
											<label class="btn btn-sm btn-primary btn-simple" id="order-chart">
												<input type="radio" class="d-none d-sm-none" name="options">
												<span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Order</span>
												<span class="d-block d-sm-none"> <i class="tim-icons icon-gift-2"></i></span>
											</label>
											<label class="btn btn-sm btn-primary btn-simple" id="product-chart">
												<input type="radio" class="d-none" name="options">
												<span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Product</span>
												<span class="d-block d-sm-none"> <i class="tim-icons icon-tap-02"></i></span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<div class="card-body">
								<div class="chart-area">
									<canvas id="chartBig1"></canvas>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%-- <div class="row">
					<div class="col-lg-4">
						<div class="card card-chart">
							<div class="card-header">
								<h5 class="card-category">Total Shipments</h5>
								<h3 class="card-title">
									<i class="tim-icons icon-bell-55 text-primary"></i> 763,215
								</h3>
							</div>
							<div class="card-body">
								<div class="chart-area">
									<canvas id="chartLinePurple"></canvas>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="card card-chart">
							<div class="card-header">
								<h5 class="card-category">Daily Sales</h5>
								<h3 class="card-title">
									<i class="tim-icons icon-delivery-fast text-info"></i> 3,500$
								</h3>
							</div>
							<div class="card-body">
								<div class="chart-area">
									<canvas id="CountryChart"></canvas>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="card card-chart">
							<div class="card-header">
								<h5 class="card-category">Completed Tasks</h5>
								<h3 class="card-title">
									<i class="tim-icons icon-send text-success"></i> 12,100K
								</h3>
							</div>
							<div class="card-body">
								<div class="chart-area">
									<canvas id="chartLineGreen"></canvas>
								</div>
							</div>
						</div>
					</div>
				</div> --%>
				<div class="row">
					<div class="col-lg-6 col-md-12">
						<div class="card card-tasks">
							<div class="card-header ">
								<h6 class="title d-inline">Tasks(5)</h6>
								<p class="card-category d-inline">today</p>
								<div class="dropdown">
									<button type="button"
										class="btn btn-link dropdown-toggle btn-icon"
										data-toggle="dropdown">
										<i class="tim-icons icon-settings-gear-63"></i>
									</button>
									<div class="dropdown-menu dropdown-menu-right"
										aria-labelledby="dropdownMenuLink">
										<a class="dropdown-item" href="#pablo">Action</a> <a
											class="dropdown-item" href="#pablo">Another action</a> <a
											class="dropdown-item" href="#pablo">Something else</a>
									</div>
								</div>
							</div>
							<div class="card-body ">
								<div class="table-full-width table-responsive">
									<table class="table">
										<tbody>
											<tr>
												<td>
													<div class="form-check">
														<label class="form-check-label"> <input
															class="form-check-input" type="checkbox" value="">
															<span class="form-check-sign"> <span class="check"></span>
														</span>
														</label>
													</div>
												</td>
												<td>
													<p class="title">Task</p>
													<p class="text-muted">Description</p>
												</td>
												<td class="td-actions text-right">
													<button type="button" rel="tooltip" title=""
														class="btn btn-link" data-original-title="Edit Task">
														<i class="tim-icons icon-pencil"></i>
													</button>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-12">
						<div class="card ">
							<div class="card-header">
								<h4 class="card-title">Order Table</h4>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table tablesorter " id="">
										<thead class=" text-primary">
											<tr>
												<th>No.</th>
												<th>Code</th>
												<th>Customer</th>
												<th align="right">Payment</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="saleOrder" items="${saleOrders }" varStatus="loop">
												<tr>
													<td>${loop.index + 1 }</td>
													<td>${saleOrder.code }</td>
													<td>${saleOrder.customerName }</td>
													<td align="right">
														$<fmt:formatNumber value="${saleOrder.total }" minFractionDigits="0"></fmt:formatNumber>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
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
	
	<script type="text/javascript">
		gradientChart = {
			maintainAspectRatio : false,
			legend : {
				display : false
			},

			tooltips : {
				backgroundColor : '#f5f5f5',
				titleFontColor : '#333',
				bodyFontColor : '#666',
				bodySpacing : 4,
				xPadding : 12,
				mode : "nearest",
				intersect : 0,
				position : "nearest"
			},
			responsive : true,
			scales : {
				yAxes : [ {
					barPercentage : 1.6,
					gridLines : {
						drawBorder : false,
						color : 'rgba(29,140,248,0.0)',
						zeroLineColor : "transparent",
					},
					ticks : {
						padding : 20,
						fontColor : "#9a9a9a"
					}
				} ],

				xAxes : [ {
					barPercentage : 1.6,
					gridLines : {
						drawBorder : false,
						color : 'rgba(225,78,202,0.1)',
						zeroLineColor : "transparent",
					},
					ticks : {
						padding : 20,
						fontColor : "#9a9a9a"
					}
				} ]
			}
		};

		var chart_labels = [ 'JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC' ];
		var revenue_data = <%= request.getAttribute("revenue_data") %>;
		var order_data = <%= request.getAttribute("order_data") %>;
		var product_data = <%= request.getAttribute("product_data") %>;

		var ctx = document.getElementById("chartBig1").getContext('2d');

		var gradientStroke = ctx.createLinearGradient(0, 230, 0, 50);
		gradientStroke.addColorStop(1, 'rgba(72,72,176,0.1)');
		gradientStroke.addColorStop(0.4, 'rgba(72,72,176,0.0)');
		gradientStroke.addColorStop(0, 'rgba(119,52,169,0)');

		var config = {
			type : 'line',
			data : {
				labels : chart_labels,
				datasets : [ {
					label : "Data",
					fill : true,
					backgroundColor : gradientStroke,
					borderColor : '#d346b1',
					borderWidth : 2,
					borderDash : [],
					borderDashOffset : 0.0,
					pointBackgroundColor : '#d346b1',
					pointBorderColor : 'rgba(255,255,255,0)',
					pointHoverBackgroundColor : '#d346b1',
					pointBorderWidth : 20,
					pointHoverRadius : 4,
					pointHoverBorderWidth : 15,
					pointRadius : 4,
					data : revenue_data,
				} ]
			},
			options : gradientChart
		};

		var myChartData = new Chart(ctx, config);
		$("#revenue-chart").click(function() {
			var data = myChartData.config.data;
			data.datasets[0].data = revenue_data;
			data.datasets[0].label = "$";
			data.labels = chart_labels;
			myChartData.update();
		});
		$("#order-chart").click(function() {
			var data = myChartData.config.data;
			data.datasets[0].data = order_data;
			data.datasets[0].label = "Orders";
			data.labels = chart_labels;
			myChartData.update();
		});
		$("#product-chart").click(function() {
			var data = myChartData.config.data;
			data.datasets[0].data = product_data;
			data.datasets[0].label = "Products";
			data.labels = chart_labels;
			myChartData.update();
		});
	</script>
</body>

</html>