package com.coffeeshop.admin.service;

import com.coffeeshop.admin.entity.Product;
import com.coffeeshop.admin.request.product.UpdateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getAll(Pageable page);

    Product get(int id);

    Product create(Product product);

    Product update(int id, UpdateProduct request);

    void delete(int id);
}

