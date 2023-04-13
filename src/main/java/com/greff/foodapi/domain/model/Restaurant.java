package com.greff.foodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

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
    @JoinColumn(name = "kitchen_id_code", nullable = false)
    //this is the way to change the mapping name of joined columns, exist NULLABLE here to
    private Kitchen kitchen; //Restaurant owns a kitchen, in db will be a column

    @JsonIgnore
    //many restaurants owns many payment methods
    @ManyToMany
    //@JoinTable(name = "nameOfIntermediateClass") altering name of class that will be needed it in @ManyToMany association
    //when we do this, intermediate table will be created in db, that's how manyToMany relationships works. they need this class, table.
    //joinColumns defines columns of foreign key in intermediate class that defines restaurant.
    //like if intermediateClass has attribute like 'restaurantId', we need to define this in joinColumns
    //it needs to know which is the name column of foreign key in intermediateClass that represents restaurant
    //inverseJoinColumns defines the same thing, but for the other table that we want to make the association
    //@JoinColumn is to define the name of foreign key, fk
    @JoinTable(name = "tb_restaurant__payment_method",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private List<PaymentMethod> paymentMethods; //since can have more than 1 method of payment is a collection
}
