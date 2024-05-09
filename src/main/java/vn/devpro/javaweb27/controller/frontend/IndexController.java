package vn.devpro.javaweb27.controller.frontend;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb27.controller.BaseController;
import vn.devpro.javaweb27.model.Category;
import vn.devpro.javaweb27.model.Product;
import vn.devpro.javaweb27.service.CategoryService;
import vn.devpro.javaweb27.service.ProductService;

@Controller
public class IndexController extends BaseController{

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(final Model model) throws IOException {

		List<Category> categories = categoryService.findAllActive();
		model.addAttribute("categories", categories);

		int menTotal = categoryService.countProductsById(19);
		model.addAttribute("menTotal", menTotal);

		int kidTotal = categoryService.countProductsById(20);
		model.addAttribute("kidTotal", kidTotal);

		int cosmeticTotal = categoryService.countProductsById(21);
		model.addAttribute("cosmeticTotal", cosmeticTotal);

		int accessoryTotal = categoryService.countProductsById(22);
		model.addAttribute("accessoryTotal", accessoryTotal);

		List<Product> products = productService.findAllActive();
		model.addAttribute("products", products);

		List<Product> hotTrend = productService.hotTrend(3);
		model.addAttribute("hotTrend", hotTrend);

		List<Product> bestSaller = productService.bestSeller(3);
		model.addAttribute("bestSaller", bestSaller);

		List<Product> feature = productService.feature(3);
		model.addAttribute("feature", feature);

		return "frontend/index";
	}

}
