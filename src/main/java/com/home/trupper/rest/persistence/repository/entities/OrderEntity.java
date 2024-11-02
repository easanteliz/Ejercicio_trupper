package com.home.trupper.rest.persistence.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="trupper_orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Double total;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="trupper_order_products", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<ProductEntity> productEntities;
}

/***
 *  n ordenes pueden tener N productos oneToMany
 * ***/
