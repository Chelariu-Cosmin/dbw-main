package com.software.application.data.repositories;

import com.software.application.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerIdOrderByCreatedDateDesc(Long customer_id);
}
