package com.coffeeshop.admin.response.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductItem {
    private int id;
    private int businessId;
    private int placeId;
    private String name;
    private String description;
    private BigDecimal price;
}
