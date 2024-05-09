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
import vn.devpro.javaweb27.model.Role;
import vn.devpro.javaweb27.model.User;
import vn.devpro.javaweb27.service.RoleService;
import vn.devpro.javaweb27.service.UserService;

@Controller
@RequestMapping("admin/role/")
public class AdminRoleController extends BaseController implements Jw27Constant {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(final Model model, final HttpServletRequest request) {

		SearchModel roleSearch = new SearchModel();

		roleSearch.setStatus(1);
		String status = request.getParameter("status");
		if (!StringUtils.isEmpty(status)) {
			roleSearch.setStatus(Integer.parseInt(status));
		}

		roleSearch.setKeyword(null);
		String keyword = request.getParameter("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			roleSearch.setKeyword(keyword);
		}

		roleSearch.setBeginDate(null);
		roleSearch.setEndDate(null);
		if (!StringUtils.isEmpty(request.getParameter("beginDate")) && !StringUtils.isEmpty(request.getParameter("endDate"))) {
			roleSearch.setBeginDate(request.getParameter("beginDate"));
			roleSearch.setEndDate(request.getParameter("endDate"));
		}

		if (!StringUtils.isEmpty(request.getParameter("currentPage"))) {
			roleSearch.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		} else {
			roleSearch.setCurrentPage(1);
		}

		roleSearch.setSizeOfPage(SIZE_OF_PAGE);
		roleSearch.setTotalItems(roleService.countTotalItems(roleSearch));

	    int totalPage = roleSearch.getTotalItems() / SIZE_OF_PAGE;
	    if (roleSearch.getTotalItems() % SIZE_OF_PAGE > 0) {
	        totalPage++;
	    }
	    roleSearch.setTotalPages(totalPage);
	    
		if (totalPage < roleSearch.getCurrentPage()) {
			roleSearch.setCurrentPage(1);
		}
		
		List<Role> roles = roleService.searchRole(roleSearch);

		model.addAttribute("roleSearch", roleSearch);
		model.addAttribute("roles", roles);
		
		return "backend/role/role-list";
		
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(final Model model) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Role role = new Role();
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		model.addAttribute("role", role);
		
		return "backend/role/role-add";
		
	}
	
	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(@ModelAttribute("role") Role role) {
		
		roleService.saveOrUpdate(role);
		
		return "redirect:/admin/role/add";
		
	}

	@RequestMapping(value = "detail/{roleId}", method = RequestMethod.GET)
	public String view(final Model model, @PathVariable("roleId") int roleId) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Role role = roleService.getById(roleId);
		model.addAttribute("role", role);
		
		return "backend/role/role-detail";
		
	}

	@RequestMapping(value = "edit/{roleId}", method = RequestMethod.GET)
	public String edit(final Model model, @PathVariable("roleId") int roleId) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Role role = roleService.getById(roleId);
		role.setUpdateDate(new Date());
		model.addAttribute("role", role);
		
		return "backend/role/role-edit";
		
	}

	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String editSave(final Model model, @ModelAttribute("role") Role role) {

		roleService.saveOrUpdate(role);

		return "redirect:/admin/role/list";
		
	}

//	@RequestMapping(value = "delete/{roleId}", method = RequestMethod.GET)
//	public String delete(@PathVariable int roleId) {
//
//		roleService.deleteRoleById(roleId);
//
//		return "redirect:/admin/role/list";
//		
//	}

	@RequestMapping(value = "delete/{roleId}", method = RequestMethod.GET)
	public String delete(@PathVariable int roleId) {
		
		Role role = roleService.getById(roleId);

		role.setStatus(false);
		role.setUpdateDate(new Date());
		
		roleService.saveOrUpdate(role);

		return "redirect:/admin/role/list";
		
	}
	
}
