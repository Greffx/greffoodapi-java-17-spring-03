package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.ProductPhoto;
import com.greff.foodapi.domain.repository.ProductRepository;
import com.greff.foodapi.domain.usecase.ProductPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PhotoProductServiceImpl implements ProductPhotoService {

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public ProductPhoto save(ProductPhoto productPhoto) {
        Long restaurantId = productPhoto.getProduct().getRestaurant().getId();
        Long productId = productPhoto.getProduct().getId();

        var optionalPhoto = productRepository.findProductPhoto(productId, restaurantId);

        System.out.println(optionalPhoto.isPresent());

        if (optionalPhoto.isPresent()) productRepository.deletePhoto(optionalPhoto.get());



        return productRepository.save(productPhoto);
    }
}
