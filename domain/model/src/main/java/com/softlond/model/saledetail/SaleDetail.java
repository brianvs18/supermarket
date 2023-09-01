package com.softlond.model.saledetail;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SaleDetail {
    private String id;
    private String productId;
    private double productPrice;
    private String productName;
    private int productAmount;
    private String saleId;
}
