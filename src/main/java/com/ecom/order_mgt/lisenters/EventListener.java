package com.ecom.order_mgt.lisenters;


import com.ecom.order_mgt.utils.AppConstants;
import com.ecom.order_mgt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListener {

    @Autowired
    private UserService userService;

    @StreamListener(Sink.INPUT)
    public void inputListener(@Payload String value) {
        log.info(String.format("Message received - %s ", value));
        if(AppConstants.USER_CREATED.equals(value)) {
            userService.syncUserDetails();
        }
    }

}
