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
import vn.devpro.javaweb27.model.Category;
import vn.devpro.javaweb27.model.Product;
import vn.devpro.javaweb27.model.User;
import vn.devpro.javaweb27.service.CategoryService;
import vn.devpro.javaweb27.service.ProductService;
import vn.devpro.javaweb27.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminProductControllerTest {

    @InjectMocks
    private AdminProductController adminProductController;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductService productService;

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
        List<Category> categories = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        SearchModel searchModel = new SearchModel();
        when(categoryService.findAllActive()).thenReturn(categories);
        when(productService.searchProduct(any())).thenReturn(products);
        when(productService.countTotalItems(any())).thenReturn(0);
        when(request.getParameter(anyString())).thenReturn("1");

        // When
        String view = adminProductController.list(model, request);

        // Then
        assertEquals("backend/product/product-list", view);
        verify(categoryService, times(1)).findAllActive();
        verify(productService, times(1)).searchProduct(any());
        verify(productService, times(1)).countTotalItems(any());
        verify(model, times(1)).addAttribute(eq("categories"), anyList());
        verify(model, times(1)).addAttribute(eq("products"), anyList());
        verify(model, times(1)).addAttribute(eq("productSearch"), any(SearchModel.class));
    }

    // Add more test cases for other methods
}
