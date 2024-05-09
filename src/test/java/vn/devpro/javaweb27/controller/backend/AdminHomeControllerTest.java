package vn.devpro.javaweb27.controller.backend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import vn.devpro.javaweb27.model.Product;
import vn.devpro.javaweb27.model.SaleOrder;
import vn.devpro.javaweb27.service.ProductService;
import vn.devpro.javaweb27.service.SaleOrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminHomeControllerTest {

    @InjectMocks
    private AdminHomeController adminHomeController;

    @Mock
    private SaleOrderService saleOrderService;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHome() {
        // Given
        List<SaleOrder> saleOrders = new ArrayList<>();
        when(saleOrderService.findAllActive()).thenReturn(saleOrders);
        when(saleOrderService.getMonthlyRevenue(anyInt())).thenReturn(new ArrayList<>());
        when(saleOrderService.getMonthlyOrders(anyInt())).thenReturn(new ArrayList<>());
        when(saleOrderService.getMonthlyProducts(anyInt())).thenReturn(new ArrayList<>());
        when(productService.hotTrend(anyInt())).thenReturn(new ArrayList<>());
        when(productService.bestSeller(anyInt())).thenReturn(new ArrayList<>());

        // When
        String view = adminHomeController.home(model);

        // Then
        assertEquals("backend/dashboard", view);
        verify(model, times(1)).addAttribute(eq("saleOrders"), anyList());
        verify(model, times(1)).addAttribute(eq("revenue_data"), anyList());
        verify(model, times(1)).addAttribute(eq("order_data"), anyList());
        verify(model, times(1)).addAttribute(eq("product_data"), anyList());
        verify(model, times(1)).addAttribute(eq("hotTrend"), anyList());
        verify(model, times(1)).addAttribute(eq("bestSaller"), anyList());
    }
}
