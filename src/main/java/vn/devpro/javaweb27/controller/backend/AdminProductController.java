package vn.devpro.javaweb27.controller.backend;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.javaweb27.controller.BaseController;
import vn.devpro.javaweb27.dto.Jw27Constant;
import vn.devpro.javaweb27.dto.SearchModel;
import vn.devpro.javaweb27.model.Category;
import vn.devpro.javaweb27.model.Product;
import vn.devpro.javaweb27.model.User;
import vn.devpro.javaweb27.service.CategoryService;
import vn.devpro.javaweb27.service.ProductService;
import vn.devpro.javaweb27.service.UserService;

@Controller
@RequestMapping("admin/product/")
public class AdminProductController extends BaseController implements Jw27Constant {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(final Model model, final HttpServletRequest request) {

		List<Category> categories = categoryService.findAllActive();
		model.addAttribute("categories", categories);

		SearchModel productSearch = new SearchModel();
		
		productSearch.setStatus(1);
		String status = request.getParameter("status");
		if (!StringUtils.isEmpty(status)) {
			productSearch.setStatus(Integer.parseInt(status));
		}
		
		productSearch.setCategoryId(0);
		String categoryId = request.getParameter("categoryId");
		if (!StringUtils.isEmpty(categoryId)) {
			productSearch.setCategoryId(Integer.parseInt(categoryId));
		}

		productSearch.setProductPrice(0);
		String productPrice = request.getParameter("productPrice");
		if (!StringUtils.isEmpty(productPrice)) {
			productSearch.setProductPrice(Integer.parseInt(productPrice));
		}

		productSearch.setKeyword(null);
		String keyword = request.getParameter("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			productSearch.setKeyword(keyword);
		}
		
		productSearch.setBeginDate(null);
		productSearch.setEndDate(null);
		if (!StringUtils.isEmpty(request.getParameter("beginDate")) && !StringUtils.isEmpty(request.getParameter("endDate"))) {
			productSearch.setBeginDate(request.getParameter("beginDate"));
			productSearch.setEndDate(request.getParameter("endDate"));
		}

		if (!StringUtils.isEmpty(request.getParameter("currentPage"))) {
			productSearch.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		} else {
			productSearch.setCurrentPage(1);
		}

		productSearch.setSizeOfPage(SIZE_OF_PAGE);
		productSearch.setTotalItems(productService.countTotalItems(productSearch));

	    int totalPage = productSearch.getTotalItems() / SIZE_OF_PAGE;
	    if (productSearch.getTotalItems() % SIZE_OF_PAGE > 0) {
	        totalPage++;
	    }
	    productSearch.setTotalPages(totalPage);
	    
		if (totalPage < productSearch.getCurrentPage()) {
			productSearch.setCurrentPage(1);
		}

		List<Product> products = productService.searchProduct(productSearch);
		
		model.addAttribute("products", products);
		model.addAttribute("productSearch", productSearch);

		return "backend/product/product-list";

	}

	@RequestMapping(value = "detail/{productId}", method = RequestMethod.GET)
	public String detail(final Model model, @PathVariable("productId") int productId) {

		List<User> users = userService.findAll();
		model.addAttribute("users", users);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		Product product = productService.getById(productId);
		model.addAttribute("product", product);

        return "backend/product/product-detail";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(final Model model) {

		List<User> users = userService.findAll();
		model.addAttribute("users", users);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		Product product = new Product();
		product.setCreateDate(new Date());
		product.setUpdateDate(new Date());
		model.addAttribute("product", product);

		return "backend/product/product-add";

	}

	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(@ModelAttribute("product") Product product,
			@RequestParam("avatarFile") MultipartFile avatarFile,
			@RequestParam("imageFiles") MultipartFile[] imageFiles) throws IOException {

		productService.saveAddProduct(product, avatarFile, imageFiles);

		return "redirect:/admin/product/add";

	}

	@RequestMapping(value = "edit/{productId}", method = RequestMethod.GET)
	public String edit(final Model model, @PathVariable("productId") int productId) {

		List<User> users = userService.findAll();
		model.addAttribute("users", users);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		Product product = productService.getById(productId);
		product.setUpdateDate(new Date());
		model.addAttribute("product", product);

		return "backend/product/product-edit";

	}

	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String editSave(@ModelAttribute("product") Product product,
			@RequestParam("avatarFile") MultipartFile avatarFile,
			@RequestParam("imageFiles") MultipartFile[] imageFiles) throws IOException {
		
		productService.saveEditProduct(product, avatarFile, imageFiles);

		return "redirect:/admin/product/list";

	}

//	@RequestMapping(value = "delete/{productId}", method = RequestMethod.GET)
//	public String delete(@PathVariable int productId) {
//
//		Product product = productService.getById(productId);
//
//		productService.deleteProduct(product);
//
//		return "redirect:/admin/product/list";
//
//	}

	@RequestMapping(value = "delete/{productId}", method = RequestMethod.GET)
	public String delete(@PathVariable int productId) {

		Product product = productService.getById(productId);

		product.setStatus(false);
		product.setUpdateDate(new Date());
		
		productService.saveOrUpdate(product);

		return "redirect:/admin/product/list";

	}

}
