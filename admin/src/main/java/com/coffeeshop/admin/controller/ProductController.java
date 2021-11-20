package com.coffeeshop.admin.controller;

import com.coffeeshop.admin.entity.Product;
import com.coffeeshop.admin.mapper.ProductMapper;
import com.coffeeshop.admin.request.product.CreateProduct;
import com.coffeeshop.admin.request.product.UpdateProduct;
import com.coffeeshop.admin.response.CustomPage;
import com.coffeeshop.admin.response.product.ProductDetail;
import com.coffeeshop.admin.response.product.ProductItem;
import com.coffeeshop.admin.service.ProductService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    private static final ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    @GetMapping
    public CustomPage<ProductItem> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                          @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Product> productPage = productService.getAll(PageRequest.of(page, size));
        return new CustomPage<>(productPage, PRODUCT_MAPPER.toProductItems(productPage.getContent()));
    }

    @GetMapping("/{id}")
    public ProductDetail get(@PathVariable("id") int id) {
        return PRODUCT_MAPPER.toProductDetail(productService.get(id));
    }

    @PostMapping
    public ProductDetail create(@RequestBody @Valid CreateProduct request) {
        return PRODUCT_MAPPER.toProductDetail(productService.create(PRODUCT_MAPPER.toProduct(request)));
    }

    @PutMapping("/{id}")
    public ProductDetail update(@PathVariable("id") int id, @RequestBody @Valid UpdateProduct request) {
        return PRODUCT_MAPPER.toProductDetail(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        productService.delete(id);
    }
}
