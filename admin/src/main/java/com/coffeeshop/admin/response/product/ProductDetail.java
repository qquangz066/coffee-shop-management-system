package com.coffeeshop.admin.response.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDetail {
    private int id;
    private int businessId;
    private int placeId;
    private String name;
    private String description;
    private BigDecimal price;
    private long createdDate;
    private long modifiedDate;
}
