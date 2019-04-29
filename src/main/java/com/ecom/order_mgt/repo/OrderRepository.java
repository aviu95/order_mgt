package com.ecom.order_mgt.repo;

import com.ecom.order_mgt.model.dao.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
