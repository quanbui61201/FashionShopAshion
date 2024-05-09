package vn.devpro.javaweb27.controller.backend;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb27.controller.BaseController;
import vn.devpro.javaweb27.model.Product;
import vn.devpro.javaweb27.model.SaleOrder;
import vn.devpro.javaweb27.service.ProductService;
import vn.devpro.javaweb27.service.SaleOrderService;

@Controller
@RequestMapping("/admin/")
public class AdminHomeController extends BaseController {

	@Autowired
	private SaleOrderService saleOrderService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(final Model model) {
		
		List<SaleOrder> saleOrders = saleOrderService.findAllActive();
		model.addAttribute("saleOrders", saleOrders);
		
		List<Integer> monthlyRevenueList = saleOrderService.getMonthlyRevenue(LocalDate.now().getYear() - 1);
		model.addAttribute("revenue_data", monthlyRevenueList);

		List<Integer> monthlyOrderList = saleOrderService.getMonthlyOrders(LocalDate.now().getYear() - 1);
		model.addAttribute("order_data", monthlyOrderList);

		List<Integer> monthlyProductList = saleOrderService.getMonthlyProducts(LocalDate.now().getYear() - 1);
		model.addAttribute("product_data", monthlyProductList);

		List<Product> hotTrend = productService.hotTrend(3);
		model.addAttribute("hotTrend", hotTrend);

		List<Product> bestSaller = productService.bestSeller(3);
		model.addAttribute("bestSaller", bestSaller);

		return "backend/dashboard";
	}

}
