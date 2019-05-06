package com.ecom.order_mgt.gateways;

import com.ecom.order_mgt.model.entity.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "UserClient", url = "${services.user-mgt.url}")
public interface UserClient {

    @GetMapping("/api/users")
    List<Users> getAlluser();

}
