package com.system.jpa.supermarketdb.client;

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
@Table(name = "clients")
public class ClientEntity {

    @Id
    private String id;
    private String name;
    private String lastname;
    private String document;
}
