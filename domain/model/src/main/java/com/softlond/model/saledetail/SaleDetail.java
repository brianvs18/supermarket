package com.softlond.model.saledetail;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SaleDetail {

    private String id;
    private String saleId;
    private String productId;
    private String productName;
    private int productAmount;
    private long productPrice;
    private long totalProductPrice;

}
