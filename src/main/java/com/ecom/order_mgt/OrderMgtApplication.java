package com.ecom.order_mgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableFeignClients
@EnableBinding(Sink.class)
public class OrderMgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMgtApplication.class, args);
    }

}
