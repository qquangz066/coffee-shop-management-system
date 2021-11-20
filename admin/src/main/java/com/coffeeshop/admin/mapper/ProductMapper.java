package com.coffeeshop.admin.mapper;

import com.coffeeshop.admin.entity.Product;
import com.coffeeshop.admin.request.product.CreateProduct;
import com.coffeeshop.admin.request.product.UpdateProduct;
import com.coffeeshop.admin.response.product.ProductDetail;
import com.coffeeshop.admin.response.product.ProductItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {
    ProductItem toProductItem(Product product);

    ProductDetail toProductDetail(Product product);

    List<ProductItem> toProductItems(List<Product> product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    Product toProduct(CreateProduct request);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    void updateProduct(@MappingTarget Product product, UpdateProduct request);
}
