package com.ecom.order_mgt.controller;

import com.ecom.order_mgt.model.dto.OrderRequest;
import com.ecom.order_mgt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity orders(@RequestBody List<OrderRequest> orderRequest) {
        return ResponseEntity.ok(orderService.postOrder(orderRequest));
    }

    @GetMapping("/orders")
    public ResponseEntity orders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity getOrdersByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrderByUserId(userId));
    }


}
