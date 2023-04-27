package com.greff.foodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.greff.foodapi.core.Groups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.ConvertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    //validation rule, restriction, this annotation says that doesn't accept null values, instead of database validate, our application does it
    //validation happen in JPA repository, so don't try to do insert, will stop early at preInsert
    //@NotEmpty means that don't accept null or ""(means empty)
    //verifies not null, not empty "" and can't be only whitespace without nothing, like "   "
    @NotBlank
    @Column(name = "restaurant_name", nullable = false)
    private String name;

    //@DecimalMin("1") minimum string value, can see in class why, because bigDecimal representation is string
    @PositiveOrZero //another annotation that say the same thing
    @Column(name = "delivery_tax", nullable = false)
    private BigDecimal deliveryTax;

    @ConvertGroup(to = Groups.KitchenId.class) //converting group Default.class to group that I created, better than saying each annotation group
    //if had more than 10 groups would be way too much hardcode, this way is converting this instance to my group
    //would be like, in time to validate kitchen convert to another group and do the validation
    //(groups = Default.class) that's standard and everyone, that's why we change, if necessary
    @NotNull
    //this one is saying that an instance of kitchen is need it, but need to validate its properties to,
    //because this one only check instance of object's there
    @Valid //using valid in Kitchen type says that don't want only to check an instance of kitchen is null, but its properties too, can't be null either
    //this is a cascade validation type
    //many restaurants own one kitchen
    @JsonIgnoreProperties( {"hibernateLazyInitializer"} ) //this attribute needs to be serialized, so we ignore this property and shall be fine
    //lazy type create a subclass 'Kitchen$HibernateProxy$njmLPKPv' in runtime, it's a proxy, when an instance of this attribute is necessary
    @ManyToOne(fetch = FetchType.LAZY) //if it's not been called, must be lazy, so don't fetch by default because of toOne type (eager load)
    //it's the owner of bidirectional relationship with kitchen, because it needs the association.
    //since kitchen could have more than one restaurant, doesn't make sense to create a column
    //every relation that ends with 'ToOne', it's standard to use 'eager loading', everytime an instance of this entity is called
    //eager load is going to happen too, by default
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
    @OneToMany(mappedBy = "restaurant") //collection resource owns this relation
    private List<Product> product = new ArrayList<>();

    @JsonIgnore
    //many restaurants own many payment methods
    //if you needs to be eager here, you can change (fetch = eager), rarely will change to eager, more like change eager to lazy
    @ManyToMany
    //every relation that ends with toMany is lazy by default, lazy will load by demand, when requested. like when we call in method or something
    //if we don't call, will not be used because of lazy type
    //@JoinTable(name = "nameOfIntermediateClass") altering name of class that will be needed it in @ManyToMany association
    //when we do this, intermediate table will be created in db, that's how manyToMany relationships works. they need this class, table.
    //joinColumns defines columns of foreign key in intermediate class that defines restaurant.
    //like if intermediateClass has attribute like 'restaurantId', we need to define this in joinColumns
    //it needs to know which is the name column of foreign key in intermediateClass that represents restaurant
    //inverseJoinColumns defines the same thing, but for the other table that we want to make the association
    //@JoinColumn is to define the name of foreign key, fk
    @JoinTable(name = "tb_restaurants__payment_methods",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private List<PaymentMethod> paymentMethods; //since can have more than 1 method of payment is a collection
}
