package com.ecom.order_mgt.service;

import com.ecom.order_mgt.exception.OrderNotAvailable;
import com.ecom.order_mgt.model.dao.Orders;
import com.ecom.order_mgt.model.dto.OrderRequest;
import com.ecom.order_mgt.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Value("${app.name}")
    private String appName;

    public List<Orders> postOrder(List<OrderRequest> orderRequest) {
        log.info(String.format("[%s] - Posting the orders", appName));
        return orderRepository.saveAll(orderRequest.stream()
                .map(Orders::new)
                .collect(Collectors.toList()));
    }

    public Orders getOrderById(Long id) {
        log.info(String.format("[%s] - Getting order by Id", appName));
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotAvailable("Requested Order is not available"));
    }

    public List<Orders> getAllOrders() {
        log.info(String.format("[%s] - Getting all orders", appName));
        return orderRepository.findAll();
    }

    public List<Orders> getOrderByUserId(Long userId) {
        log.info(String.format("[%s] - Getting orders by user id", appName));
        return orderRepository.findByUserId(userId);
    }

}
