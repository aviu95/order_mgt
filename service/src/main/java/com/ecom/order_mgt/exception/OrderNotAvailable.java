package com.ecom.order_mgt.exception;

public class OrderNotAvailable extends RuntimeException {
    public OrderNotAvailable(String message) {
        super(message);
    }
}
