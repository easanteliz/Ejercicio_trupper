package com.home.trupper.rest.persistence.repository.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="trupper_branches")
@ToString
public class SucursalEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="trupper_sucursal_ordenes", joinColumns = @JoinColumn(name = "sucursal_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<OrderEntity> orderEntities;
}
/***
 *  1 sucursal puede tener N ordenes oneToMany
 * ***/
