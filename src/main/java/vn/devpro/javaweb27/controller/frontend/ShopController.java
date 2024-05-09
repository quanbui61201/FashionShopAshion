package vn.devpro.javaweb27.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb27.controller.BaseController;
import vn.devpro.javaweb27.dto.Jw27Constant;
import vn.devpro.javaweb27.dto.SearchModel;
import vn.devpro.javaweb27.model.Category;
import vn.devpro.javaweb27.model.Product;
import vn.devpro.javaweb27.model.ProductImage;
import vn.devpro.javaweb27.service.CategoryService;
import vn.devpro.javaweb27.service.ProductImageService;
import vn.devpro.javaweb27.service.ProductService;

@Controller
public class ShopController extends BaseController implements Jw27Constant {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productImageService;

	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shop(final Model model, final HttpServletRequest request) throws IOException {
		
		List<Category> categories = categoryService.findAllActive();
		model.addAttribute("categories", categories);

		SearchModel productSearch = new SearchModel();

		productSearch.setStatus(1);
		
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

		return "frontend/shop";
	}

	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	public String productDetail(final Model model, @PathVariable("productId") int productId) throws IOException {
		Product product = productService.getById(productId);
		model.addAttribute("product", product);
		
		List<ProductImage> productImages = productImageService.getProductImagesByProductId(productId);
		model.addAttribute("productImages", productImages);
		
		List<Product> relatedProducts = productService.getRelatedProducts(productId);
		model.addAttribute("relatedProducts", relatedProducts);
		return "frontend/product";
	}
	
}
