package com.ecom.order_mgt.service;

import com.ecom.order_mgt.exception.OrderNotAvailable;
import com.ecom.order_mgt.model.Paged;
import com.ecom.order_mgt.model.dto.OrderRequest;
import com.ecom.order_mgt.model.entity.OrderUserDetails;
import com.ecom.order_mgt.model.entity.Orders;
import com.ecom.order_mgt.model.entity.Users;
import com.ecom.order_mgt.repo.OrderDetailRepository;
import com.ecom.order_mgt.repo.OrderRepository;
import com.ecom.order_mgt.repo.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Spy
    private OrderRepository orderRepository;

    @Spy
    private UsersRepository usersRepository;

    @Spy
    private OrderDetailRepository orderDetailRepository;

    private Long orderId = 1L;

    private Long itemId = 1L;

    private Users userOne = new Users(1L, "Avi", "U");

    private Orders orderOne = new Orders(1L, userOne, itemId, "Ordering Apple", LocalDateTime.now());

    private Users userTwo = new Users(2L, "Dad", "G");

    private Orders orderTwo = new Orders(2L, userTwo, itemId, "Ordering Orange", LocalDateTime.now());

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
        doReturn(Optional.of(userOne)).when(usersRepository).findById(1L);
        doReturn(Arrays.asList(orderOne, orderTwo)).when(orderRepository).findByUserId(any());
        List<Orders> orderByUserId = orderService.getOrderByUserId(1L);
        assertEquals(2, orderByUserId.size());

    }

    @Test
    public void postOrder() {
        OrderRequest orderOne = new OrderRequest(1L, 1L, "Apple");
        doReturn(Optional.of(userOne)).when(usersRepository).findById(1L);
        doReturn(Arrays.asList(this.orderOne)).when(orderRepository).saveAll(anyList());
        List<Orders> orders = orderService.postOrder(Collections.singletonList(orderOne));
        assertEquals(1, orders.size());
        assertEquals(orderOne.getUserId(), orders.get(0).getUserId().getId());
    }

    @Test
    public void postOrderThrowUserNotSynced() {
        OrderRequest orderOne = new OrderRequest(1L, 1L, "Apple");
        assertThrows(RuntimeException.class, () -> orderService.postOrder(Collections.singletonList(orderOne)));
    }

    @Test
    public void getOrderDetails() {
        OrderUserDetails orderDetailOne = new OrderUserDetails(1L, "Ordering Apple", "Avi", "U");
        OrderUserDetails orderDetailTwo = new OrderUserDetails(2L, "Ordering Orange", "Dad", "G");
        doReturn(new PageImpl<>(Collections.singletonList(orderDetailOne))).when(orderDetailRepository).findAll(any(Pageable.class));
        Paged<OrderUserDetails> orderDetails = orderService.getOrderDetails(0, 1, null);
        assertEquals(1, orderDetails.getItems().size());

        doReturn(new PageImpl<>(Arrays.asList(orderDetailOne, orderDetailTwo))).when(orderDetailRepository).findAll(any(Pageable.class));
        Paged<OrderUserDetails> orderResponseOne = orderService.getOrderDetails(0, 1, "asc:id");
        assertEquals(2, orderResponseOne.getItems().size());
        assertEquals("Avi", orderResponseOne.getItems().get(0).getFirstName());

        doReturn(new PageImpl<>(Arrays.asList(orderDetailTwo, orderDetailOne))).when(orderDetailRepository).findAll(any(Pageable.class));
        Paged<OrderUserDetails> orderResponseTwo = orderService.getOrderDetails(0, 1, "desc:id");
        assertEquals(2, orderResponseTwo.getItems().size());
        assertEquals("Dad", orderResponseTwo.getItems().get(0).getFirstName());

    }
}
