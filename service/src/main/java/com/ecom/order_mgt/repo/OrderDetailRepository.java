package com.ecom.order_mgt.repo;

import com.ecom.order_mgt.model.entity.OrderUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderUserDetails, Long> {
}
