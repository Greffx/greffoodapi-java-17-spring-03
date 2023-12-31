package com.greff.foodapi.domain.model;
//DOMAIN LAYER OF DDD pattern - domain model: got models/entities, - got repositories interfaces, and - got services with business rules implementations

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data//substitute setter of attributes  //substitute getter of attributes
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//substitute hashcode and equals is to compare objects between them, this means that will choose what will generate hashcode and equals
@Entity //this entity will turn into a table in DB, that's how we say it
@Table(name = "tb_kitchens") //that's how to change name of table
public class Kitchen {

    //@NotNull(groups = Groups.KitchenId.class)
    //when registering a restaurant, validate an object restaurant that contains a (groups = Groups.RestaurantRegister.class) constraints
    //saying like when trying to post a restaurant, id of kitchen of that restaurant can't be null
    @EqualsAndHashCode.Include //that is how we choose an attribute to be used in hashcode and equals
    @Id //id of kitchen, say this attribute will represent id of entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //to configure how is going to be generation of id. Receive property 'STRATEGY'.
    //Using IDENTITY, given responsibility to SQL to be the provider, it will do id generation for us
    private Long id;

    //@Column(name = "kitchen_name", nullable = false)
    //column map an attribute, can change name, using nullable = false, attribute will not be accepted as null no more
    private String name;

    //1 kitchen has many restaurants, that's why the collection. 0...* means in diagram that kitchen can have none of many restaurants
    //OneToMany is inverse of ManyToOne mapping, that it's called bidirectional. When is Many is a collection
    @OneToMany(mappedBy = "kitchen")
    //with this mappedBy is to indicate name of attribute inside another entity which was used to map <entityName> ('kitchen' entity)
    private List<Restaurant> restaurants = new ArrayList<>(); //good to init with empty list, to avoid nullPointerException

}