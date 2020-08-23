package com.coffeeshop.admin.service;

import com.coffeeshop.admin.entity.Product;
import com.coffeeshop.admin.mapper.ProductMapper;
import com.coffeeshop.admin.repository.ProductRepository;
import com.coffeeshop.admin.request.product.CreateProduct;
import com.coffeeshop.admin.request.product.UpdateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Page<Product> getAll(Pageable page) {
        return productRepository.findAll(page);
    }

    public Product get(int id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Product create(CreateProduct request) {
        return productRepository.save(productMapper.toProduct(request));
    }

    public Product update(int id, UpdateProduct request) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productMapper.updateProduct(request, product);
        return productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.delete(productRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
