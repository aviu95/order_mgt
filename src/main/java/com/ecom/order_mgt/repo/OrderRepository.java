package com.ecom.order_mgt.repo;

import com.ecom.order_mgt.model.dao.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUserId(Long userId);

}
