package com.greff.foodapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_restaurants")
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_name", nullable = false)
    private String name;

    @Column(name = "delivery_tax", nullable = false)
    private BigDecimal deliveryTax;

    @ManyToOne //many restaurants own one kitchen
    //it's the owner of bidirectional relationship with kitchen, because it needs the association.
    // since kitchen could have more than one restaurant, doesn't make sense to create a column
    @JoinColumn(name = "kitchen_id_code", nullable = false) //this is the way to change the mapping name of joined columns, exist NULLABLE here to
    private Kitchen kitchen; //Restaurant owns a kitchen, in db will be a column

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;
}
