package com.softlond.model.sale;

import com.softlond.model.saledetail.SaleDetail;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Sale {

    private String id;
    private String clientId;
    private Long creationDate;
    private Long totalSale;
    private List<SaleDetail> saleDetails;

}
