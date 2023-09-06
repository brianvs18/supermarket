package com.softlond.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductDTO {
    private String id;
    private String name;
    private long price;
    private int stock;
    private String categoryId;
}
