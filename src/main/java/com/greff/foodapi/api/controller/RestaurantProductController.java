package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.ProductAssembler;
import com.greff.foodapi.api.assembler.ProductRequestDisassembler;
import com.greff.foodapi.api.model.request.ProductRequest;
import com.greff.foodapi.api.model.response.ProductResponse;
import com.greff.foodapi.domain.model.Product;
import com.greff.foodapi.domain.usecase.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    private final ProductService productService;
    private final ProductAssembler productAssembler;
    private final ProductRequestDisassembler productRequestDisassembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody @Valid ProductRequest productRequest,
                                         @PathVariable Long restaurantId) {
        Product product = productRequestDisassembler.toDomainObject(productRequest);

        productService.create(product, restaurantId);

        return productAssembler.toModel(product);
    }

    @PutMapping("/{productId}")
    public ProductResponse updateProduct(@RequestBody @Valid ProductRequest productRequest,
                                         @PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productService.findProductThroughRestaurant(restaurantId, productId);

        productRequestDisassembler.copyProductRequestToProduct(productRequest, product);

        product = productService.update(product, restaurantId);

        return productAssembler.toModel(product);
    }

    @GetMapping
    public List<ProductResponse> findAllProducts(@PathVariable Long restaurantId) {
        var products = productService.findAll(restaurantId);

        return productAssembler.toCollectionModel(products);
    }

    @GetMapping("/{productId}")
    public ProductResponse findProductById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        var product = productService.findProductThroughRestaurant(restaurantId, productId);

        return productAssembler.toModel(product);
    }




}