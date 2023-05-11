package com.greff.foodapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable //it's an embeddable(incorporável) class, can be embedded to other class.
//This class is a part of another entity. This class is not an entity itself.
//ALL attributes of this embedded class will be reflected in table of entity of who embedded this class
//like to another entity borrowed to use your attributes
public class Address {

    @Column(name = "cep_address") //since this will be embedded in another table, need to change the name of column to better understanding when reading there
    private String cep;

    @Column(name = "public_area_address")
    private String publicArea;

    @Column(name = "number_address")
    private String number;

    @Column(name = "complement_address")
    private String complement;

    @Column(name = "street_address")
    private String street;

    @ManyToOne(fetch = FetchType.LAZY) //many address has one city
    @JoinColumn(name = "city_address_id")
    private City city;
}
