package com.system.jpa.supermarketdb.saledetail;

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
@Table(name = "sales_detail")
public class SaleDetailEntity {

    @Id
    private String id;
    @Column(name = "sale_id")
    private String saleId;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_amount")
    private int productAmount;
    @Column(name = "product_price")
    private long productPrice;
    @Column(name = "product_total")
    private long totalProductPrice;

}
