package com.ecom.order_mgt.service;

import com.ecom.order_mgt.exception.OrderNotAvailable;
import com.ecom.order_mgt.model.entity.Orders;
import com.ecom.order_mgt.model.entity.Users;
import com.ecom.order_mgt.repo.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Spy
    private OrderRepository orderRepository;

    private Long orderId = 1L;

    private Long itemId = 1L;

    private Orders orderOne = new Orders(1L, new Users(1L, "Avi", "U"), itemId, "Ordering Apple", LocalDateTime.now());

    private Orders orderTwo = new Orders(1L, new Users(1L, "Dad", "G"), itemId, "Ordering Orange", LocalDateTime.now());

    @Test
    public void getOrders() {
        Long orderId = 1L;
        Long itemId = 1L;
        doReturn(Arrays.asList(orderOne)).when(orderRepository).findAll();
        List<Orders> allOrders = orderService.getAllOrders();
        assertEquals(1, allOrders.size());
        assertEquals(orderId, allOrders.get(0).getId());
        assertEquals(itemId, allOrders.get(0).getItemId());
    }

    @Test
    public void getOrderById() {
        doReturn(Optional.of(orderOne)).when(orderRepository).findById(orderId);
        Orders orderById = orderService.getOrderById(orderId);
        assertEquals(itemId, orderById.getItemId());
        assertEquals(orderId, orderById.getId());
    }

    @Test
    public void shouldReturnNotFoundExceptionOrderById() {
        doReturn(Optional.empty()).when(orderRepository).findById(orderId);
        assertThrows(OrderNotAvailable.class, () -> orderService.getOrderById(orderId));
    }

    @Test
    public void getOrderByUserId() {
        doReturn(Arrays.asList(orderOne, orderTwo)).when(orderRepository).findByUserId(1L);
        List<Orders> orderByUserId = orderService.getOrderByUserId(1L);
        assertEquals(2, orderByUserId.size());

    }
}
