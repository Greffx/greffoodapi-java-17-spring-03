package com.greff.foodapi.domain.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY) //to configure how is going to be generation of id. Receive property 'strategy', will be IDENTITY, given responsibility to SQL to be the provider, it will do id generation for us
    private Long id;

    @Column(name = "kitchen_name") //column map a attribute, can change name
    private String name;
}
