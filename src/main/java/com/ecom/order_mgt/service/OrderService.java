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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Value("${app.name}")
    private String appName;

    public List<Orders> postOrder(List<OrderRequest> orderRequest) {
        log.info(String.format("[%s] - Posting the orders", appName));
        return orderRepository.saveAll(orderRequest.stream()
                .map(request -> {
                    Users user = usersRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException(String.format("User %s is not synced ", request.getUserId())));
                    return new Orders(request, user);
                })
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

    public Paged<OrderUserDetails> getOrderDetails(Integer page, Integer size, String sort) {
        log.info(String.format("[%s] - Getting Paginated Order User Details", appName));
        Page<OrderUserDetails> pagedData = orderDetailRepository.findAll(PageRequest.of(page, size, getSortFromParam(sort)));
        return new Paged<>(pagedData.getContent(), pagedData.getNumber(), pagedData.getSize(), pagedData.getTotalPages());
    }

    private Sort getSortFromParam(String sort) {
        String[] params;
        if (StringUtils.isEmpty(sort) || (params = sort.split(":")).length != 2)
            return new Sort(Collections.EMPTY_LIST);

        if (params[0].equalsIgnoreCase("asc"))
            return new Sort(Sort.Direction.ASC, params[1]);

        return new Sort(Sort.Direction.DESC, params[1]);
    }

}
