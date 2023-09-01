package com.softlond.model.product;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {
    private String id;
    private String name;
    private Double price;
    private Integer stock;
    private String categoryId;
}
