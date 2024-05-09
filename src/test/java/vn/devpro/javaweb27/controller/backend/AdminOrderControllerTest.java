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
import vn.devpro.javaweb27.model.SaleOrder;
import vn.devpro.javaweb27.service.SaleOrderService;
import vn.devpro.javaweb27.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminOrderControllerTest {

    @InjectMocks
    private AdminOrderController adminOrderController;

    @Mock
    private SaleOrderService saleOrderService;

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
        List<SaleOrder> saleOrders = new ArrayList<>();
        when(saleOrderService.searchOrder(any())).thenReturn(saleOrders);
        when(saleOrderService.totalSale(any())).thenReturn(0);
        when(request.getParameter(anyString())).thenReturn("1");
        when(request.getParameter(anyString())).thenReturn("1");

        // When
        String view = adminOrderController.list(model, request);

        // Then
        assertEquals("backend/order/order-list", view);
        verify(saleOrderService, times(1)).searchOrder(any());
        verify(saleOrderService, times(1)).totalSale(any());
        verify(model, times(1)).addAttribute(eq("saleOrders"), anyList());
        verify(model, times(1)).addAttribute(eq("totalSales"), anyInt());
        verify(model, times(1)).addAttribute(eq("saleOrderSearch"), any(SearchModel.class));
    }

    // Add more test cases for other methods
}
