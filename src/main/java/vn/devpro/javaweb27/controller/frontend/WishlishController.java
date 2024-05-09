package vn.devpro.javaweb27.controller.frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb27.controller.BaseController;
import vn.devpro.javaweb27.dto.Jw27Constant;
import vn.devpro.javaweb27.model.Product;
import vn.devpro.javaweb27.model.User;
import vn.devpro.javaweb27.model.Wishlist;
import vn.devpro.javaweb27.service.ProductService;
import vn.devpro.javaweb27.service.WishlistService;

@Controller
public class WishlishController extends BaseController implements Jw27Constant {

	@Autowired
	private WishlistService wishlistService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/wishlist", method = RequestMethod.GET)
	public String wishlish(final Model model) throws IOException {
		if (isLogined()) {
			User user = getLoginedUser();
			String sql = "SELECT p.* FROM tbl_product p INNER JOIN tbl_wishlist w ON p.id = w.product_id WHERE w.user_id = " + user.getId();
			List<Product> products = productService.executeNativeSql(sql);
			
			model.addAttribute("products", products);
		}

		return "frontend/wishlist";
	}

	@RequestMapping(value = "/add-to-wishlist/{productId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addToWishlish(@PathVariable("productId") int productId) throws IOException {
		Map<String, Object> jsonResult = new HashMap<String, Object>();

	    if (!isLogined()) {
	        jsonResult.put("code", 401);
	        jsonResult.put("message", "Bạn chưa có tài khoản! Vui lòng đăng nhập hoặc đăng ký!");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jsonResult);
	    }

	    User user = getLoginedUser();

	    if (!wishlistService.isExist(user.getId(), productId)) {
			Wishlist wishlist = new Wishlist(null, user.getId(), productId);
			wishlistService.saveOrUpdate(wishlist);
	    }
		jsonResult.put("code", 200);
		jsonResult.put("message", "sản phẩm đã được thêm vào yêu thích!");

		return ResponseEntity.ok(jsonResult);
	}

	@Transactional
	@RequestMapping(value = "/remove-from-wishlist/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> removeFromWishlish(final Model model, @PathVariable("productId") int productId) throws IOException {
		Map<String, Object> jsonResult = new HashMap<String, Object>();

	    if (!isLogined()) {
	        jsonResult.put("code", 401);
	        jsonResult.put("message", "Bạn chưa có tài khoản! Vui lòng đăng nhập hoặc đăng ký!");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jsonResult);
	    }

		User user = getLoginedUser();
		int userId = user.getId();
		
		if (wishlistService.isExist(userId, productId)) {
            wishlistService.deleteByUserIdAndProductId(userId, productId);
            jsonResult.put("code", 200);
            jsonResult.put("message", "Sản phẩm đã được xoá khỏi yêu thích!");
        } else {
            jsonResult.put("code", 404);
            jsonResult.put("message", "Sản phẩm không tồn tại trong danh sách yêu thích của bạn!");
        }

		return ResponseEntity.ok(jsonResult);
	}

}
