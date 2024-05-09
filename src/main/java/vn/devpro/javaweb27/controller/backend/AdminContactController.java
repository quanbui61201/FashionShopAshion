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
import vn.devpro.javaweb27.model.Contact;
import vn.devpro.javaweb27.model.User;
import vn.devpro.javaweb27.service.ContactService;
import vn.devpro.javaweb27.service.UserService;

@Controller
@RequestMapping("/admin/contact/")
public class AdminContactController extends BaseController implements Jw27Constant {

	@Autowired
	private ContactService contactService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(final Model model, final HttpServletRequest request) {

		SearchModel contactSearch = new SearchModel();
		
		contactSearch.setStatus(1);
		String status = request.getParameter("status");
		if (!StringUtils.isEmpty(status)) {
			contactSearch.setStatus(Integer.parseInt(status));
		}
		
		contactSearch.setKeyword(null);
		String keyword = request.getParameter("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			contactSearch.setKeyword(keyword);
		}

		contactSearch.setBeginDate(null);
		contactSearch.setEndDate(null);
		if (!StringUtils.isEmpty(request.getParameter("beginDate")) && !StringUtils.isEmpty(request.getParameter("endDate"))) {
			contactSearch.setBeginDate(request.getParameter("beginDate"));
			contactSearch.setEndDate(request.getParameter("endDate"));
		}

		if (!StringUtils.isEmpty(request.getParameter("currentPage"))) {
			contactSearch.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		} else {
			contactSearch.setCurrentPage(1);
		}

		contactSearch.setSizeOfPage(SIZE_OF_PAGE);
		contactSearch.setTotalItems(contactService.countTotalItems(contactSearch));

	    int totalPage = contactSearch.getTotalItems() / SIZE_OF_PAGE;
	    if (contactSearch.getTotalItems() % SIZE_OF_PAGE > 0) {
	        totalPage++;
	    }
	    contactSearch.setTotalPages(totalPage);
	    
		if (totalPage < contactSearch.getCurrentPage()) {
			contactSearch.setCurrentPage(1);
		}
		
		List<Contact> contacts = contactService.searchContact(contactSearch);
		
		model.addAttribute("contacts", contacts);
		model.addAttribute("contactSearch", contactSearch);

		return "backend/contact/contact-list";
		
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(final Model model) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Contact contact = new Contact();
		contact.setCreateDate(new Date());
		contact.setUpdateDate(new Date());
		model.addAttribute("contact", contact);
		
		return "backend/contact/contact-add";
		
	}

	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(@ModelAttribute("contact") Contact contact) {
		
		contactService.saveOrUpdate(contact);
		
		return "redirect:/admin/contact/add";
		
	}

	@RequestMapping(value = "detail/{contactId}", method = RequestMethod.GET)
	public String view(final Model model, @PathVariable("contactId") int contactId) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Contact contact = contactService.getById(contactId);
		model.addAttribute("contact", contact);
		
		return "backend/contact/contact-detail";
		
	}

	@RequestMapping(value = "edit/{contactId}", method = RequestMethod.GET)
	public String edit(final Model model, @PathVariable("contactId") int contactId) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Contact contact = contactService.getById(contactId);
		contact.setUpdateDate(new Date());
		model.addAttribute("contact", contact);
		
		return "backend/contact/contact-edit";
		
	}

	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String editSave(final Model model, @ModelAttribute("contact") Contact contact) {

		contactService.saveOrUpdate(contact);

		return "redirect:/admin/contact/list";
		
	}

//	@RequestMapping(value = "delete/{contactId}", method = RequestMethod.GET)
//	public String delete(@PathVariable int contactId) {
//
//		contactService.deleteContactById(contactId);
//
//		return "redirect:/admin/contact/list";
//		
//	}

	@RequestMapping(value = "delete/{contactId}", method = RequestMethod.GET)
	public String delete(@PathVariable int contactId) {

		Contact contact = contactService.getById(contactId);
		contact.setStatus(Boolean.FALSE);
		contact.setUpdateDate(new Date());
		contactService.saveOrUpdate(contact);

		return "redirect:/admin/contact/list";
		
	}

}
