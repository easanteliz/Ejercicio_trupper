package com.home.trupper.rest.persistence.repository;

import com.home.trupper.rest.persistence.repository.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
