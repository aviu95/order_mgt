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

    @PostMapping("/order")
    public ResponseEntity order(@RequestBody List<OrderRequest> orderRequest) {
        return ResponseEntity.ok(orderService.postOrder(orderRequest));
    }

    @GetMapping("/order")
    public ResponseEntity order() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity orderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

}
