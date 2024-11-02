package com.home.trupper.rest.auth.service;

import com.home.trupper.rest.auth.AuthorizationResponse;
import com.home.trupper.rest.auth.UserRequest;
import com.home.trupper.rest.persistence.repository.PermissionRepository;
import com.home.trupper.rest.persistence.repository.RoleRepository;
import com.home.trupper.rest.persistence.repository.UserRepository;
import com.home.trupper.rest.persistence.repository.entities.PermissionEntity;
import com.home.trupper.rest.persistence.repository.entities.RoleEntity;
import com.home.trupper.rest.persistence.repository.entities.UserEntity;
import com.home.trupper.rest.service.UserDetailServiceDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;
    private final UserDetailServiceDAO userDetailServiceDAO;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationResponse login(UserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));

        return AuthorizationResponse.builder()
                .bearerToken(jwtService.generateToken(userDetailServiceDAO.loadUserByUsername(userRequest.getUsername())))
                .build();
    }

    public AuthorizationResponse register(UserRequest userRequest) {

        Optional <UserEntity> userDB = userRepository.findByUsername(userRequest.getUsername());

        if(userDB.isPresent()){
                     return AuthorizationResponse.builder()
                    .bearerToken(jwtService.generateToken(userDetailServiceDAO.loadUserByUsername(userRequest.getUsername())))
                    .build();
        }

        userRequest.getRoles().forEach(loginRole -> {
            Optional<RoleEntity> rol  = Optional.ofNullable(roleRepository.findByRoleEnum(loginRole.getRoleEnum())
                    .orElseThrow(() -> new UsernameNotFoundException("ROLE not found")));
            rol.ifPresent(roleEntity -> loginRole.setId(roleEntity.getId()));
            loginRole.getPermissions().forEach(loginPermission ->{
                Optional<PermissionEntity> permisionDB = Optional.ofNullable(permissionRepository.findByName(loginPermission.getName()))
                        .orElseThrow(() -> new UsernameNotFoundException("Permission not found"));
                permisionDB.ifPresent(permissionEntity -> loginPermission.setId(permissionEntity.getId()));
            });
        });

        UserEntity user = UserEntity.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(userRequest.getRoles())
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .isEnable(true)
                .build();
        userRepository.save(user);
        log.info("Se registra usuario en la base de datos ...");
        return AuthorizationResponse.builder()
                .bearerToken(jwtService.generateToken(userDetailServiceDAO.loadUserByUsername(userRequest.getUsername())))
                .build();
    }

}
