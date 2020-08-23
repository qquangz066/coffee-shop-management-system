package com.coffeeshop.admin.controller;

import com.coffeeshop.admin.entity.Product;
import com.coffeeshop.admin.mapper.ProductMapper;
import com.coffeeshop.admin.request.product.CreateProduct;
import com.coffeeshop.admin.request.product.UpdateProduct;
import com.coffeeshop.admin.response.CustomPage;
import com.coffeeshop.admin.response.product.ProductDetail;
import com.coffeeshop.admin.response.product.ProductItem;
import com.coffeeshop.admin.service.ProductService;
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

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public CustomPage<ProductItem> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                          @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Product> productPage = productService.getAll(PageRequest.of(page, size));
        return new CustomPage<>(productPage, productMapper.toProductItems(productPage.getContent()));
    }

    @GetMapping("/{id}")
    public ProductDetail get(@PathVariable("id") int id) {
        return productMapper.toProductDetail(productService.get(id));
    }

    @PostMapping
    public ProductDetail create(@RequestBody @Valid CreateProduct request) {
        return productMapper.toProductDetail(productService.create(request));
    }

    @PutMapping("/{id}")
    public ProductDetail update(@PathVariable("id") int id, @RequestBody @Valid UpdateProduct request) {
        return productMapper.toProductDetail(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        productService.delete(id);
    }
}
