package com.ecom.order_mgt.gateways;

import com.ecom.order_mgt.model.dto.UserRequest;
import com.ecom.order_mgt.model.entity.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "UserClient", url = "${services.user-mgt.url}")
public interface UserClient {

    @PostMapping("/api/userDetails")
    List<Users> getUsers(@RequestBody UserRequest userRequest);

}
