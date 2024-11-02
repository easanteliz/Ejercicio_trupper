package com.home.trupper.rest.persistence.repository;

import com.home.trupper.rest.persistence.repository.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findProductEntitiesByCodigo(String codigo);
}
