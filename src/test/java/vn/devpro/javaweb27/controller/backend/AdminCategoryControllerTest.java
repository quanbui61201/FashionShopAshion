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
import vn.devpro.javaweb27.service.CategoryService;
import vn.devpro.javaweb27.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminCategoryControllerTest {

    @InjectMocks
    private AdminCategoryController adminCategoryController;

    @Mock
    private CategoryService categoryService;

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
        List<Category> categories = new ArrayList<>();
        when(categoryService.searchCategory(any())).thenReturn(categories);
        when(request.getParameter(anyString())).thenReturn("1");
        when(request.getParameter(anyString())).thenReturn("1");

        // When
        String view = adminCategoryController.list(model, request);

        // Then
        assertEquals("backend/category/category-list", view);
        verify(categoryService, times(1)).searchCategory(any());
        verify(model, times(1)).addAttribute(eq("categories"), anyList());
        verify(model, times(1)).addAttribute(eq("categorySearch"), any(SearchModel.class));
    }

    @Test
    public void testAdd() {
        // Given

        // When
        String view = adminCategoryController.add(model);

        // Then
        assertEquals("backend/category/category-add", view);
        verify(userService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("users"), anyList());
        verify(model, times(1)).addAttribute(eq("category"), any(Category.class));
    }

    @Test
    public void testAddSave() {
        // Given
        Category category = new Category();

        // When
        String view = adminCategoryController.addSave(category);

        // Then
        assertEquals("redirect:/admin/category/add", view);
        verify(categoryService, times(1)).saveOrUpdate(category);
    }

    // Add more test cases for other methods
}
