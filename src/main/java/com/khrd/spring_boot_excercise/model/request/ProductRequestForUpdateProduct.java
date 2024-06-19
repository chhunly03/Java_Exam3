package com.khrd.spring_boot_excercise.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequestForUpdateProduct {
    private String name;
    private Double price_per_unit;
    private Boolean active_for_sell;
}
