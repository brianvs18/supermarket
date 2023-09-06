package com.system.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClientDTO {
    private String id;
    private String name;
    private String lastname;
    private String document;
}
