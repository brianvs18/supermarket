package com.system.model.product;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {
    private String id;
    private String name;
    private long price;
    private int stock;
    private String categoryId;
}
