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
import vn.devpro.javaweb27.model.Category;
import vn.devpro.javaweb27.model.User;
import vn.devpro.javaweb27.service.CategoryService;
import vn.devpro.javaweb27.service.UserService;

@Controller
@RequestMapping("/admin/category/")
public class AdminCategoryController extends BaseController implements Jw27Constant {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(final Model model, final HttpServletRequest request) {

		SearchModel categorySearch = new SearchModel();
		
		categorySearch.setStatus(1);
		String status = request.getParameter("status");
		if (!StringUtils.isEmpty(status)) {
			categorySearch.setStatus(Integer.parseInt(status));
		}
		
		categorySearch.setKeyword(null);
		String keyword = request.getParameter("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			categorySearch.setKeyword(keyword);
		}

		categorySearch.setBeginDate(null);
		categorySearch.setEndDate(null);
		if (!StringUtils.isEmpty(request.getParameter("beginDate")) && !StringUtils.isEmpty(request.getParameter("endDate"))) {
			categorySearch.setBeginDate(request.getParameter("beginDate"));
			categorySearch.setEndDate(request.getParameter("endDate"));
		}

		if (!StringUtils.isEmpty(request.getParameter("currentPage"))) {
			categorySearch.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		} else {
			categorySearch.setCurrentPage(1);
		}

		categorySearch.setSizeOfPage(SIZE_OF_PAGE);
		categorySearch.setTotalItems(categoryService.countTotalItems(categorySearch));
		
	    int totalPage = categorySearch.getTotalItems() / SIZE_OF_PAGE;
	    if (categorySearch.getTotalItems() % SIZE_OF_PAGE > 0) {
	        totalPage++;
	    }
	    categorySearch.setTotalPages(totalPage);
	    
		if (totalPage < categorySearch.getCurrentPage()) {
			categorySearch.setCurrentPage(1);
		}
		
		List<Category> categories = categoryService.searchCategory(categorySearch);
		
		model.addAttribute("categories", categories);
		model.addAttribute("categorySearch", categorySearch);

		return "backend/category/category-list";
		
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(final Model model) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Category category = new Category();
		category.setCreateDate(new Date());
		category.setUpdateDate(new Date());
		model.addAttribute("category", category);
		
		return "backend/category/category-add";
		
	}

	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(@ModelAttribute("category") Category category) {

		categoryService.saveOrUpdate(category);
		
		return "redirect:/admin/category/add";
		
	}

	@RequestMapping(value = "detail/{categoryId}", method = RequestMethod.GET)
	public String view(final Model model, @PathVariable("categoryId") int categoryId) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Category category = categoryService.getById(categoryId);
		model.addAttribute("category", category);
		
		return "backend/category/category-detail";
		
	}

	@RequestMapping(value = "edit/{categoryId}", method = RequestMethod.GET)
	public String edit(final Model model, @PathVariable("categoryId") int categoryId) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Category category = categoryService.getById(categoryId);
		category.setUpdateDate(new Date());
		model.addAttribute("category", category);
		
		return "backend/category/category-edit";
		
	}

	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String editSave(final Model model, @ModelAttribute("category") Category category) {

		categoryService.saveOrUpdate(category);

		return "redirect:/admin/category/list";
		
	}

//	@RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
//	public String delete(@PathVariable int categoryId) {
//
//		categoryService.deleteCategoryById(categoryId);
//
//		return "redirect:/admin/category/list";
//		
//	}

	@RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
	public String delete(@PathVariable int categoryId) {

		Category category = categoryService.getById(categoryId);
		category.setStatus(Boolean.FALSE);
		category.setUpdateDate(new Date());
		categoryService.saveOrUpdate(category);

		return "redirect:/admin/category/list";
		
	}

}
