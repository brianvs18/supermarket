package com.softlond.jpa.supermarketdb.sale;

import jakarta.persistence.Column;
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
@Table(name = "sales")
public class SaleEntity {

    @Id
    private String id;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "creation_date")
    private Long creationDate;
    @Column(name = "total_sale")
    private Long totalSale;
}
