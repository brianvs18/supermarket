package com.system.model.category;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Category {
    private String id;
    private String name;
}
