package com.ecom.order_mgt.controller;

import com.ecom.order_mgt.model.Paged;
import com.ecom.order_mgt.model.dto.OrderRequest;
import com.ecom.order_mgt.model.entity.OrderUserDetails;
import com.ecom.order_mgt.model.entity.Orders;
import com.ecom.order_mgt.model.entity.Users;
import com.ecom.order_mgt.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Spy
    private OrderService orderService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private Long orderId = 1L;

    private Long itemId = 1L;

    private Users userOne = new Users(1L, "Avi", "U");

    private Orders orderOne = new Orders(orderId, userOne, itemId, "Ordering Apple", LocalDateTime.now());

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void orders() throws Exception {
        OrderRequest orderRequest = new OrderRequest(1L, 1L, "Order 1");

        doReturn(Arrays.asList(orderOne)).when(orderService).postOrder(anyList());

        MvcResult mvcResult = this.mockMvc.perform(post("/api/orders")
                .content(objectMapper.writeValueAsString(Collections.singletonList(orderRequest)))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.writeValueAsString(Arrays.asList(orderOne)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getAllorders() throws Exception {
        doReturn(Arrays.asList(orderOne)).when(orderService).getAllOrders();

        MvcResult mvcResult = this.mockMvc.perform(get("/api/orders")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.writeValueAsString(Arrays.asList(orderOne)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getOrderById() throws Exception {
        doReturn(orderOne).when(orderService).getOrderById(1L);

        MvcResult mvcResult = this.mockMvc.perform(get("/api/order/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.writeValueAsString(orderOne), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getOrdersByUserId() throws Exception {
        doReturn(Arrays.asList(orderOne)).when(orderService).getOrderByUserId(1L);

        MvcResult mvcResult = this.mockMvc.perform(get("/api/orders/user/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.writeValueAsString(Arrays.asList(orderOne)), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getOrdersWithUser() throws Exception {
        List<OrderUserDetails> collectedDetail = Collections.singletonList(new OrderUserDetails(1L, "Ordering Apple", "Avi", "U"));
        Paged<OrderUserDetails> orderUserDetailsPaged = new Paged<>(collectedDetail, 0, 1, 1);

        doReturn(orderUserDetailsPaged).when(orderService).getOrderDetails(anyInt(), anyInt(), any());

        MvcResult mvcResult = this.mockMvc.perform(get("/api/orders/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.writeValueAsString(orderUserDetailsPaged), mvcResult.getResponse().getContentAsString());

    }
}
