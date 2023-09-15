package com.softlond.jpa.supermarketdb.user;

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
@Table(name = "users")
public class UserEntity {

    @Id
    private String id;
    private String username;
    private String password;
    private String clientId;
}
