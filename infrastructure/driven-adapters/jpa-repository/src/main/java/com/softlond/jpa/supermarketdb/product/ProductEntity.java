package com.softlond.jpa.supermarketdb.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private Double price;
    private Integer stock;
    private String categoryId;
}
