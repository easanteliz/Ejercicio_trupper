package com.home.trupper.rest.auth;


import com.home.trupper.rest.persistence.repository.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String username;
    private String password;
    private String name;
    private Set<RoleEntity> roles;

}
