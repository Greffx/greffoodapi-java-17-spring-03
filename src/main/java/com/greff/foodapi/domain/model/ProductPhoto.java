package com.greff.foodapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_product_photo")
public class ProductPhoto {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "product_id") //id will not be autoincrement, will use product id that already exists
    private Long id;

    //lazy because when is searching for a photo will not always need to fetch product too
    @OneToOne(fetch = FetchType.LAZY)
    //this annotation says that product entity is mapped through id
    @MapsId
    //object-oriented, if user want a product by its photo
    private Product product;

    private String filename;
    private String description;

    @Column(name = "content_type")
    private String contentType;

    private Long size;
}
