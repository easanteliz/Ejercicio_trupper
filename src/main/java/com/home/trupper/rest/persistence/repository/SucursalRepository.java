package com.home.trupper.rest.persistence.repository;

import com.home.trupper.rest.persistence.repository.entities.SucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SucursalRepository extends JpaRepository<SucursalEntity,Integer> {
    Optional<SucursalEntity> findByName(String nombre);

}