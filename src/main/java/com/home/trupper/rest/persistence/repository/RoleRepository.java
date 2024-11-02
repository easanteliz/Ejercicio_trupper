package com.home.trupper.rest.persistence.repository;

import com.home.trupper.rest.persistence.repository.entities.RoleEntity;
import com.home.trupper.rest.persistence.repository.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleEnum(RoleEnum role);
}
