package vn.devpro.javaweb27.controller.backend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import vn.devpro.javaweb27.dto.SearchModel;
import vn.devpro.javaweb27.model.Contact;
import vn.devpro.javaweb27.service.ContactService;
import vn.devpro.javaweb27.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminContactControllerTest {

    @InjectMocks
    private AdminContactController adminContactController;

    @Mock
    private ContactService contactService;

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Model model;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testList() {
        // Given
        SearchModel searchModel = new SearchModel();
        List<Contact> contacts = new ArrayList<>();
        when(contactService.searchContact(any())).thenReturn(contacts);
        when(request.getParameter(anyString())).thenReturn("1");
        when(request.getParameter(anyString())).thenReturn("1");

        // When
        String view = adminContactController.list(model, request);

        // Then
        assertEquals("backend/contact/contact-list", view);
        verify(contactService, times(1)).searchContact(any());
        verify(model, times(1)).addAttribute(eq("contacts"), anyList());
        verify(model, times(1)).addAttribute(eq("contactSearch"), any(SearchModel.class));
    }

    @Test
    public void testAdd() {
        // Given

        // When
        String view = adminContactController.add(model);

        // Then
        assertEquals("backend/contact/contact-add", view);
        verify(userService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("users"), anyList());
        verify(model, times(1)).addAttribute(eq("contact"), any(Contact.class));
    }

    @Test
    public void testAddSave() {
        // Given
        Contact contact = new Contact();

        // When
        String view = adminContactController.addSave(contact);

        // Then
        assertEquals("redirect:/admin/contact/add", view);
        verify(contactService, times(1)).saveOrUpdate(contact);
    }

    // Add more test cases for other methods
}
