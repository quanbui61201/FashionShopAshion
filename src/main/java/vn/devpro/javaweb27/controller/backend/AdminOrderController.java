package vn.devpro.javaweb27.controller.backend;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb27.controller.BaseController;
import vn.devpro.javaweb27.dto.Jw27Constant;
import vn.devpro.javaweb27.dto.SearchModel;
import vn.devpro.javaweb27.model.SaleOrder;
import vn.devpro.javaweb27.model.SaleOrderProduct;
import vn.devpro.javaweb27.model.User;
import vn.devpro.javaweb27.service.SaleOrderProductService;
import vn.devpro.javaweb27.service.SaleOrderService;
import vn.devpro.javaweb27.service.UserService;

@Controller
@RequestMapping("/admin/order/")
public class AdminOrderController extends BaseController implements Jw27Constant {

	@Autowired
	private SaleOrderService saleOrderService;

	@Autowired
	private SaleOrderProductService saleOrderProductService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(final Model model, final HttpServletRequest request) {

		SearchModel saleOrderSearch = new SearchModel();
		
		saleOrderSearch.setStatus(2);
		String status = request.getParameter("status");
		if (!StringUtils.isEmpty(status)) {
			saleOrderSearch.setStatus(Integer.parseInt(status));
		}
		
		saleOrderSearch.setKeyword(null);
		String keyword = request.getParameter("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			saleOrderSearch.setKeyword(keyword);
		}

		saleOrderSearch.setBeginDate(null);
		saleOrderSearch.setEndDate(null);
		if (!StringUtils.isEmpty(request.getParameter("beginDate")) && !StringUtils.isEmpty(request.getParameter("endDate"))) {
			saleOrderSearch.setBeginDate(request.getParameter("beginDate"));
			saleOrderSearch.setEndDate(request.getParameter("endDate"));
		}

		if (!StringUtils.isEmpty(request.getParameter("currentPage"))) {
			saleOrderSearch.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		} else {
			saleOrderSearch.setCurrentPage(1);
		}
		
		saleOrderSearch.setSizeOfPage(SIZE_OF_PAGE);
		saleOrderSearch.setTotalItems(saleOrderService.countTotalItems(saleOrderSearch));

	    int totalPage = saleOrderSearch.getTotalItems() / SIZE_OF_PAGE;
	    if (saleOrderSearch.getTotalItems() % SIZE_OF_PAGE > 0) {
	        totalPage++;
	    }
	    saleOrderSearch.setTotalPages(totalPage);
	    
		if (totalPage < saleOrderSearch.getCurrentPage()) {
			saleOrderSearch.setCurrentPage(1);
		}

		List<SaleOrder> saleOrders = saleOrderService.searchOrder(saleOrderSearch);
		
		Integer totalSales = saleOrderService.totalSale(saleOrderSearch);
		
		model.addAttribute("saleOrders", saleOrders);
		model.addAttribute("totalSales", totalSales);
		model.addAttribute("saleOrderSearch", saleOrderSearch);

		return "backend/order/order-list";
		
	}

	@RequestMapping(value = "detail/{orderId}", method = RequestMethod.GET)
	public String view(final Model model, @PathVariable("orderId") int orderId) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		SaleOrder order = saleOrderService.getById(orderId);
		model.addAttribute("order", order);

		List<SaleOrderProduct> orderProducts = saleOrderProductService.findByOrderId(orderId);
		model.addAttribute("orderProducts", orderProducts);
		
		return "backend/order/order-detail";
		
	}

	@RequestMapping(value = "edit/{orderId}", method = RequestMethod.GET)
	public String edit(final Model model, @PathVariable("orderId") int orderId) {

		SaleOrder order = saleOrderService.getById(orderId);
		order.setStatus(Boolean.TRUE);
		order.setUpdateDate(new Date());
		saleOrderService.saveOrUpdate(order);

		return "redirect:/admin/order/list";
		
	}

	@RequestMapping(value = "edit-save", method = RequestMethod.GET)
	public String editSave(final Model model, @ModelAttribute("order") SaleOrder order) {
		
		saleOrderService.saveOrUpdate(order);
		
		return "redirect:/admin/order/list";
		
	}

//	@RequestMapping(value = "delete/{saleOrderId}", method = RequestMethod.GET)
//	public String delete(@PathVariable int saleOrderId) {
//
//		saleOrderService.deleteSaleOrderById(saleOrderId);
//
//		return "redirect:/admin/order/list";
//		
//	}

	@RequestMapping(value = "delete/{saleOrderId}", method = RequestMethod.GET)
	public String delete(@PathVariable int saleOrderId) {

		SaleOrder order = saleOrderService.getById(saleOrderId);
		order.setStatus(Boolean.FALSE);
		order.setUpdateDate(null);
		saleOrderService.saveOrUpdate(order);

		return "redirect:/admin/order/list";
		
	}

}
