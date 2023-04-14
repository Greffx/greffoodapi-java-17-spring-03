package com.greff.foodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @Embedded //that indicates that this attribute is embedded('incorporado') type, is a part of entity restaurant
    //saying is a part, not a column. will not create a table about it, but its attributes will be created in restaurant table
    private Address address;

    @JsonIgnore
    @CreationTimestamp //annotation of 'IMPLEMENTATION Hibernate', not from JPA.
    //CreationTimestamp add, inform creationDate will have a Date.now when it's created by the first time
    //columnDefinition is to take it out milliseconds of column
    //it not is necessary to show it to user, so I took it out
    @Column(name = "creation_date", nullable = false, columnDefinition = "datetime")
    private LocalDateTime creationDate;

    @JsonIgnore
    @UpdateTimestamp//also 'IMPLEMENTATION Hibernate', not from JPA.
    //UpdateTimestamp add, inform updateDate will have a Date.now always when updated
    @Column(name = "update_date", nullable = false, columnDefinition = "datetime")
    private LocalDateTime updateDate;


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
