package com.home.trupper.rest;

import com.home.trupper.rest.persistence.repository.SucursalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class TrupperApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrupperApiApplication.class, args);

		}

		@Bean
		CommandLineRunner commandLineRunner(SucursalRepository sucursalRepository) {
		return args -> {
/*
			ProductRequest product = ProductRequest.builder()
					.codigo("Test")
					.descripcion("Producto de prueba")
					.precio(10.00)
					.build();
			OrderRequest orderEntity = OrderRequest.builder()
					.fecha(new Date())
					.productEntities(List.of(product))
					.total(1900.0)
					.build();
			SucursalEntity sucursalEntity = SucursalEntity.builder()
					.orderEntities(List.of(orderEntity))
					.name("TESTSucursal")
					.build();
			System.out.println("SucursalEntity: " + sucursalEntity);

			sucursalRepository.save(sucursalEntity);
			*/


		};

	}
/*
	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();
			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();
			PermissionEntity adminPermission = PermissionEntity.builder()
					.name("ADMIN")
					.build();


			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission,deletePermission,adminPermission))
					.build();


			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissions(Set.of(readPermission, createPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissions(Set.of(createPermission, readPermission, updatePermission,deletePermission))
					.build();

			UserEntity userAdmin = UserEntity.builder()
					.username("admin")
					.password(new BCryptPasswordEncoder().encode("admin"))
					.roles(Set.of(roleAdmin))
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.isEnable(true)
					.build();

			UserEntity userDev = UserEntity.builder()
					.username("developer")
					.password(new BCryptPasswordEncoder().encode("admin"))
					.roles(Set.of(roleDeveloper))
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.isEnable(true)
					.build();

			UserEntity user = UserEntity.builder()
					.username("user")
					.password(new BCryptPasswordEncoder().encode("admin"))
					.roles(Set.of(roleDeveloper))
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.isEnable(true)
					.build();

			userRepository.saveAll(List.of(userAdmin,userDev,user));

		};
	}
 */
}