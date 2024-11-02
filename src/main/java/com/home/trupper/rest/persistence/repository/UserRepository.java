package com.home.trupper.rest.persistence.repository;

import com.home.trupper.rest.persistence.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    /*
    @Query ("select u from UserEntity u where u.username= ?")
    Optional<List<UserEntity>> findUser(String Username);
    */
}
