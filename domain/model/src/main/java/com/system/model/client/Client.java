package com.system.model.client;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Client {

    private String id;
    private String name;
    private String lastname;
    private String document;
}
