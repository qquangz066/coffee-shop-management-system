package com.coffeeshop.admin.service.Impl;

import com.coffeeshop.admin.entity.Product;
import com.coffeeshop.admin.mapper.ProductMapper;
import com.coffeeshop.admin.repository.ProductRepository;
import com.coffeeshop.admin.request.product.UpdateProduct;
import com.coffeeshop.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private static final ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    public Page<Product> getAll(Pageable page) {
        return productRepository.findAll(page);
    }

    public Product get(int id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(int id, UpdateProduct request) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        PRODUCT_MAPPER.updateProduct(product, request);
        return productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.delete(productRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
