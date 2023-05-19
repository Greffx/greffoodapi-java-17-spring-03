package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.request.ProductRequest;
import com.greff.foodapi.domain.mapper.ProductMapper;
import com.greff.foodapi.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductRequestDisassembler {

    private final ProductMapper productMapper;

    public Product toDomainObject(ProductRequest productRequest) {
        return productMapper.fromProductRequestToProduct(productRequest);
    }

    public Product copyProductRequestToProduct(ProductRequest productRequest, Product product) {
        return productMapper.updateDomainObject(productRequest, product);
    }
}
