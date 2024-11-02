package com.home.trupper.rest.persistence.repository.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="trupper_products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

}
