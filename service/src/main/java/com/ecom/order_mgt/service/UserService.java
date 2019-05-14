package com.ecom.order_mgt.service;

import com.ecom.order_mgt.gateways.UserClient;
import com.ecom.order_mgt.model.dto.UserRequest;
import com.ecom.order_mgt.model.entity.Users;
import com.ecom.order_mgt.repo.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserClient userClient;

    public void syncUserDetails(Boolean fullSync, List<Long> userIds) {
        log.info("Fetching User details from User Service");
        List<Users> alluser = userClient.getUsers(new UserRequest(fullSync, userIds));
        log.info("Syncing User details in Order Service");
        usersRepository.saveAll(alluser);
    }


    public List<Users> getUsers() {
        log.info("Fetching users from Table");
        return usersRepository.findAll();
    }
}
