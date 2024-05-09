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
import vn.devpro.javaweb27.model.User;
import vn.devpro.javaweb27.model.Role;
import vn.devpro.javaweb27.service.RoleService;
import vn.devpro.javaweb27.service.UserService;

@Controller
@RequestMapping("admin/user/")
public class AdminUserController extends BaseController implements Jw27Constant {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(final Model model, final HttpServletRequest request) {
		
		List<Role> roles = roleService.findAllActive();
		model.addAttribute("roles", roles);

		SearchModel userSearch = new SearchModel();

		userSearch.setStatus(1);
		String status = request.getParameter("status");
		if (!StringUtils.isEmpty(status)) {
			userSearch.setStatus(Integer.parseInt(status));
		}

		userSearch.setRoleId(0);
		String roleId = request.getParameter("roleId");
		if (!StringUtils.isEmpty(roleId)) {
			userSearch.setRoleId(Integer.parseInt(roleId));
		}

		userSearch.setKeyword(null);
		String keyword = request.getParameter("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			userSearch.setKeyword(keyword);
		}

		userSearch.setBeginDate(null);
		userSearch.setEndDate(null);
		if (!StringUtils.isEmpty(request.getParameter("beginDate")) && !StringUtils.isEmpty(request.getParameter("endDate"))) {
			userSearch.setBeginDate(request.getParameter("beginDate"));
			userSearch.setEndDate(request.getParameter("endDate"));
		}

		if (!StringUtils.isEmpty(request.getParameter("currentPage"))) {
			userSearch.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		} else {
			userSearch.setCurrentPage(1);
		}

		userSearch.setSizeOfPage(SIZE_OF_PAGE);
		userSearch.setTotalItems(userService.countTotalItems(userSearch));

	    int totalPage = userSearch.getTotalItems() / SIZE_OF_PAGE;
	    if (userSearch.getTotalItems() % SIZE_OF_PAGE > 0) {
	        totalPage++;
	    }
	    userSearch.setTotalPages(totalPage);
	    
		if (totalPage < userSearch.getCurrentPage()) {
			userSearch.setCurrentPage(1);
		}

		List<User> users = userService.searchUser(userSearch);
		
		model.addAttribute("users", users);
		model.addAttribute("userSearch", userSearch);

		return "backend/user/user-list";
		
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(final Model model) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);

		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		
		User user = new User();
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		model.addAttribute("user", user);
		
		return "backend/user/user-add";
		
	}
	
	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(@ModelAttribute("user") User user,
						  @RequestParam("avatarFile") MultipartFile avatarFile,
						  @RequestParam("role") int roleId) throws IOException {
		
		userService.saveAddUser(user, avatarFile, roleId);
		
		return "redirect:/admin/user/add";
		
	}

	@RequestMapping(value = "detail/{userId}", method = RequestMethod.GET)
	public String view(final Model model, @PathVariable("userId") int userId) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		User user = userService.getById(userId);
		model.addAttribute("user", user);
		
		return "backend/user/user-detail";
		
	}

	@RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
	public String edit(final Model model, @PathVariable("userId") int userId) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		User user = userService.getById(userId);
		user.setUpdateDate(new Date());
		model.addAttribute("user", user);
		
		return "backend/user/user-edit";
		
	}

	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String editSave(@ModelAttribute("user") User user, @RequestParam("avatarFile") MultipartFile avatarFile) throws IOException {

		userService.saveEditUser(user, avatarFile);

		return "redirect:/admin/user/list";
		
	}

//	@RequestMapping(value = "delete/{userId}", method = RequestMethod.GET)
//	public String delete(@PathVariable int userId) {
//
//		User user = userService.getById(userId);
//		userService.deleteUser(user);
//
//		return "redirect:/admin/user/list";
//		
//	}

	@RequestMapping(value = "delete/{userId}", method = RequestMethod.GET)
	public String delete(@PathVariable int userId) {

		User user = userService.getById(userId);
		
		user.setStatus(Boolean.FALSE);
		user.setUpdateDate(new Date());
		
		userService.saveOrUpdate(user);

		return "redirect:/admin/user/list";
		
	}
	
}
