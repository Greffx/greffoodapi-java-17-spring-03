package com.greff.foodapi.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity //this entity will turn into a table in DB, that's how we say it
@Table(name = "tb_kitchens") //that's how to change name of table
public class Kitchen {

    @Id //id of kitchen, say this attribute will represent id of entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) //to configure how is going to be generatation of id. Receive property 'strategy', will be IDENTITY, given responsibility to SQL to be the provider, it will do id generation for us
    private Long id;

    @Column(name = "kitchen_name") //column map a attribute, can change name
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //hashcode and equals is to compare objects between them
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kitchen kitchen = (Kitchen) o;
        return getId().equals(kitchen.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
