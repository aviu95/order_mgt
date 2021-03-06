package com.ecom.order_mgt.repo;

import com.ecom.order_mgt.model.entity.Orders;
import com.ecom.order_mgt.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUserId(Users userId);

}
