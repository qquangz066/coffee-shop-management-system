package com.coffeeshop.admin.request.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateProduct {

    @NotNull
    @Positive
    private int businessId;

    @NotNull
    @Positive
    private int placeId;

    @NotBlank
    private String name;

    private String description;

    @DecimalMin(value = "0.00")
    private BigDecimal price;
}
