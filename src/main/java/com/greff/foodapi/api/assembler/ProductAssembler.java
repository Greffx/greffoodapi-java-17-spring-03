package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.ProductResponse;
import com.greff.foodapi.domain.mapper.ProductMapper;
import com.greff.foodapi.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProductAssembler {

    private final ProductMapper productMapper;

    public ProductResponse toModel(Product product) {
        return productMapper.fromProductToProductResponse(product);
    }

    public List<ProductResponse> toCollectionModel(List<Product> products) {
        return products.stream().map(this::toModel).toList();
    }
}
