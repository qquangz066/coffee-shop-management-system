package com.coffeeshop.admin.request.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProduct {

    @NotBlank
    private String name;

    private String description;

    @DecimalMin(value = "0.00")
    private BigDecimal price;
}
