package vn.devpro.javaweb27.controller.frontend;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb27.controller.BaseController;
import vn.devpro.javaweb27.dto.Jw27Constant;
import vn.devpro.javaweb27.model.Contact;
import vn.devpro.javaweb27.service.ContactService;

@Controller
public class ContactController extends BaseController implements Jw27Constant {

	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		model.addAttribute("contact", new Contact());
		return "frontend/contact";
	}

	@RequestMapping(value = "/contact-save", method = RequestMethod.POST)
	public String contactSave(final Model model, final HttpServletRequest request, final HttpServletResponse response,
			@ModelAttribute("contact") Contact contact) throws IOException {
		contact.setCreateDate(new Date());
		contact.setUpdateDate(new Date());
		contactService.saveOrUpdate(contact);
		return "redirect:/contact";
	}

}
