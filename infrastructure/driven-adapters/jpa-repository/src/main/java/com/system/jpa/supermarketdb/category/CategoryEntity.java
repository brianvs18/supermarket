package com.system.jpa.supermarketdb.category;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "categories")
public class CategoryEntity {

    @Id
    private String id;
    private String name;
}
