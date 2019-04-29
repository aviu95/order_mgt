package com.ecom.order_mgt.controller;

import com.ecom.order_mgt.exception.OrderNotAvailable;
import com.ecom.order_mgt.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RequestMapping("/api")
public class BaseController {

    @ExceptionHandler(OrderNotAvailable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(Exception e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

}
