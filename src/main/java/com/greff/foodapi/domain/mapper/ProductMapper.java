package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.ProductRequest;
import com.greff.foodapi.api.model.response.ProductResponse;
import com.greff.foodapi.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductResponse fromProductToProductResponse(Product product);

    Product fromProductRequestToProduct(ProductRequest productRequest);

    Product updateDomainObject(ProductRequest productRequest,  @MappingTarget Product product);
}