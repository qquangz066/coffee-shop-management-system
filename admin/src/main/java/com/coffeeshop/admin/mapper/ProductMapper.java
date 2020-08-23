package com.coffeeshop.admin.mapper;

import com.coffeeshop.admin.entity.Product;
import com.coffeeshop.admin.request.product.CreateProduct;
import com.coffeeshop.admin.request.product.UpdateProduct;
import com.coffeeshop.admin.response.product.ProductDetail;
import com.coffeeshop.admin.response.product.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //entity mapper
    ProductItem toProductItem(Product product);

    ProductDetail toProductDetail(Product product);

    List<ProductItem> toProductItems(List<Product> product);

    //request mapper
    Product toProduct(CreateProduct request);

    void updateProduct(UpdateProduct request, @MappingTarget Product product);
}
