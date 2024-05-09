package vn.devpro.javaweb27.controller.frontend;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb27.controller.BaseController;
import vn.devpro.javaweb27.dto.Cart;
import vn.devpro.javaweb27.dto.CartProduct;
import vn.devpro.javaweb27.dto.Jw27Constant;
import vn.devpro.javaweb27.model.Contact;
import vn.devpro.javaweb27.model.Product;
import vn.devpro.javaweb27.model.SaleOrder;
import vn.devpro.javaweb27.model.SaleOrderProduct;
import vn.devpro.javaweb27.service.ProductService;
import vn.devpro.javaweb27.service.SaleOrderService;

@Controller
public class CartController extends BaseController implements Jw27Constant {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private SaleOrderService saleOrderService;

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(final Model model, final HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("cart") != null) {
			Cart cart = (Cart)session.getAttribute("cart");
			model.addAttribute("totalCartPrice", cart.totalPrice());
			
			String message = cart.totalCartProduct() + " sản phẩm";
			model.addAttribute("message", message);
		} else {
			String errorMessage = "Không có sản phẩm nào trong giỏ hàng";
			model.addAttribute("errorMessage", errorMessage);
		}
		
		return "frontend/cart";
	}

	@RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addToCart(final Model model,
			final HttpServletRequest request,
			@RequestBody CartProduct cartProduct) throws IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

	    if (cart == null) {
	        cart = new Cart();
	        session.setAttribute("cart", cart);
	    }

		Product dbProduct = productService.getById(cartProduct.getProductId());
		int index = cart.findCartProductByIdAndSize(dbProduct.getId(), cartProduct.getSize());
		
		if (index != -1) {
			cart.getCartProducts().get(index).setQuantity(cart.getCartProducts().get(index).getQuantity().add(cartProduct.getQuantity()));
		} else {
			cartProduct.setProductName(dbProduct.getName());
			cartProduct.setAvatar(dbProduct.getAvatar());
			cartProduct.setPrice(dbProduct.getSalePrice());
			if (cartProduct.getSize() == null) {
				cartProduct.setSize("M");
			} else {
				cartProduct.setSize(cartProduct.getSize());
			}
			cart.getCartProducts().add(cartProduct);
		}
		
		session.setAttribute("cart", cart);
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("totalCartProducts", cart.totalCartProduct());
		jsonResult.put("message", cartProduct.getQuantity() + " sản phẩm " + cartProduct.getProductName() + " đã được thêm vào giỏ hàng");
		
		return ResponseEntity.ok(jsonResult);
	}

	@RequestMapping(value = "/update-product-quantity", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateProductQuantity(final Model model,
			final HttpServletRequest request,
			@RequestBody CartProduct cartProduct) throws IOException {
		
		HttpSession session = request.getSession();
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		if (session.getAttribute("cart") != null) {
			Cart cart = (Cart)session.getAttribute("cart");
			int index = cart.findCartProductById(cartProduct.getProductId());
			BigInteger quantity = cart.getCartProducts().get(index).getQuantity().add(cartProduct.getQuantity());
			if (quantity.intValue() < 1) {
				quantity = BigInteger.ONE;
			}
			
			cart.getCartProducts().get(index).setQuantity(quantity);
			session.setAttribute("cart", cart);

			jsonResult.put("newQuantity", quantity);
			jsonResult.put("productId", cartProduct.getProductId());
		}
		
		return ResponseEntity.ok(jsonResult);
	}

	@RequestMapping(value = "/product-cart-delete/{productId}", method = RequestMethod.GET)
	public String deleteCartProduct(final Model model,
			final HttpServletRequest request,
			@PathVariable("productId") int productId) throws IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		int index = cart.findCartProductById(productId);
		cart.getCartProducts().remove(index);
		
		session.setAttribute("cart", cart);
		
		return "redirect:/cart";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(final Model model, final HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("cart") != null) {
			Cart cart = (Cart)session.getAttribute("cart");
			model.addAttribute("totalCartPrice", cart.totalPrice());
		}
		
		return "frontend/checkout";
	}

	@RequestMapping(value = "/place-order", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> placeOrder(final Model model,
			final HttpServletRequest request,
			@RequestBody Contact contact) throws IOException {

		Map<String, Object> jsonResult = new HashMap<String, Object>();

		if (StringUtils.isEmpty(contact.getName())) {
			jsonResult.put("message", "Bạn chưa nhập họ tên");
		} else if (StringUtils.isEmpty(contact.getMobile())) {
			jsonResult.put("message", "Bạn chưa nhập số điện thoại");
		} else {
			HttpSession session = request.getSession();
			if (session.getAttribute("cart") == null) {
				jsonResult.put("message", "Bạn chưa có sản phẩm nào trong giỏ hàng");
			} else {
				SaleOrder saleOrder = new SaleOrder();

				Cart cart = (Cart)session.getAttribute("cart");
				for (CartProduct cartProduct : cart.getCartProducts()) {
					SaleOrderProduct orderProduct = new SaleOrderProduct();
					orderProduct.setProduct(productService.getById(cartProduct.getProductId()));
					orderProduct.setQuantity(cartProduct.getQuantity().intValue());
					orderProduct.setCreateDate(new Date());
					orderProduct.setUserCreateSaleOrderProduct(getLoginedUser());
					saleOrder.addRelationalSaleOrderProduct(orderProduct);
				}
				
				Calendar cal = Calendar.getInstance();
				String code = contact.getMobile() + cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DAY_OF_MONTH);
				saleOrder.setCode(code);
				saleOrder.setUser(getLoginedUser());
				
				saleOrder.setTotal(cart.totalPrice());
				
				saleOrder.setCustomerName(contact.getName());
				saleOrder.setCustomerMobile(contact.getMobile());
				saleOrder.setCustomerEmail(contact.getEmail());
				saleOrder.setCustomerAddress(contact.getAddress());
				saleOrder.setCreateDate(new Date());
				saleOrder.setStatus(Boolean.FALSE);
				
				saleOrderService.saveOrder(saleOrder);
				
				jsonResult.put("code", 200);
				jsonResult.put("message", "Bạn đã đặt hàng thành công, xin cảm ơn!!");
				
				cart = new Cart();
				session.setAttribute("cart", cart);
			}
		}

		return ResponseEntity.ok(jsonResult);
	}

}
