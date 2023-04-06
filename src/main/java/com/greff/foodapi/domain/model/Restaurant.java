package com.greff.foodapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_restaurant", schema = "greffoodb")
public class Restaurant {

    @Id
    private Long id;

    @Column(name = "restaurant_name")
    private String name;

    @Column(name = "delivery_tax")
    private BigDecimal deliveryTax;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant kitchen = (Restaurant) o;
        return getId().equals(kitchen.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
