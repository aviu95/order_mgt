package com.ecom.order_mgt.component_test;


import com.ecom.order_mgt.OrderMgtApplication;
import com.ecom.order_mgt.model.entity.Orders;
import com.ecom.order_mgt.model.entity.Users;
import com.ecom.order_mgt.repo.OrderRepository;
import com.ecom.order_mgt.repo.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static com.ecom.order_mgt.utils.Utility.createURLWithPort;
import static com.ecom.order_mgt.utils.Utility.getHttpEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = OrderMgtApplication.class)
public class OrderApplicationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private ObjectMapper objectMapper;

    private Users userOne;

    private Orders orderOne;

    @BeforeEach
    public void init() {
        this.userOne = new Users(1L, "Avi", "U");
        this.orderOne = new Orders(1L, userOne, 1L, "Ordering Apple", LocalDateTime.now());
        usersRepository.save(userOne);
        orderRepository.save(orderOne);
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void getOrderByOrderId() throws Exception {
        HttpEntity<Object> requestHttpEntity = getHttpEntity(null);
        ResponseEntity<String> exchange = restTemplate.exchange(createURLWithPort("/api/order/1", port), HttpMethod.GET, requestHttpEntity, String.class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertNotNull(exchange.getBody());
        String expected = objectMapper.writeValueAsString(orderOne);
        assertEquals(expected, exchange.getBody());

    }
}
