package com.greff.foodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data//substitute setter of attributes  //substitute getter of attributes
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //substitute hashcode and equals is to compare objects between them, this means that will choose what will generate hashcode and equals
@Entity //this entity will turn into a table in DB, that's how we say it
@Table(name = "tb_kitchens") //that's how to change name of table
public class Kitchen {

    @EqualsAndHashCode.Include //that is how we choose an attribute to be used in hashcode and equals
    @Id //id of kitchen, say this attribute will represent id of entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) //to configure how is going to be generation of id. Receive property 'STRATEGY'.
                                                        //Using IDENTITY, given responsibility to SQL to be the provider, it will do id generation for us
    private Long id;

    @JsonProperty("name") //this not change at domain model, not change the name of attribute. But the representation in JSON will be another, if we change 'name' value
    @Column(name = "kitchen_name", nullable = false) //column map an attribute, can change name, using nullable = false, attribute will not be accepted as null no more
    private String name;
}