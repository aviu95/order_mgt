package com.ecom.order_mgt.service;

import com.ecom.order_mgt.gateways.UserClient;
import com.ecom.order_mgt.model.entity.Users;
import com.ecom.order_mgt.repo.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserClient userClient;

    @Mock
    private UsersRepository usersRepository;

    @Test
    public void syncUserDetails() {
        Users users = new Users(1L, "Avi", "U");
        when(userClient.getUsers(any())).thenReturn(Arrays.asList(users));
        doReturn(null).when(usersRepository).saveAll(anyList());
        userService.syncUserDetails(false, Collections.singletonList(1L));
        verify(userClient, times(1)).getUsers(any());
        verify(usersRepository, times(1)).saveAll(anyList());
    }
}
