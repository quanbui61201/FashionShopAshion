package vn.devpro.javaweb27.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.devpro.javaweb27.dto.Cart;
import vn.devpro.javaweb27.model.User;
import vn.devpro.javaweb27.service.UserService;
import vn.devpro.javaweb27.service.WishlistService;

@Controller
public class BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private WishlistService wishlistService;
	
	@ModelAttribute("title")
	public String protjectTitle() {
		return "Javaweb 27";
	}
	
	@ModelAttribute("totalCartProducts")
	public BigInteger getTotalCartProducts(final HttpServletRequest request) {
		HttpSession session = request.getSession();
	    Cart cart = (Cart) session.getAttribute("cart");

	    if (cart == null) {
	        return BigInteger.ZERO;
	    }

	    return cart.totalCartProduct();
	}

    @ModelAttribute("totalWishlist")
    public BigInteger getTotalWishlist(final HttpServletRequest request) {
        if (!isLogined()) {
            return BigInteger.ZERO;
        }

        User user = getLoginedUser();

        BigInteger totalWishlist = wishlistService.getTotalWishlistByUserId(user.getId());

        return totalWishlist;
    }
    
	@ModelAttribute("loginedUser")
	public User getLoginedUser() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal != null && principal instanceof UserDetails) {
			return (User) principal;
		}
		
		User user = userService.getById(39);
		return user;
	}
	
	@ModelAttribute("isLogined")
	public boolean isLogined() {

		Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principale != null && principale instanceof UserDetails) {
			return true;
		}
		return false;
	}

}