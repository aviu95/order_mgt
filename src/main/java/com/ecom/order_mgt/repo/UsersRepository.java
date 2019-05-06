package com.ecom.order_mgt.repo;

import com.ecom.order_mgt.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
