package com.softlond.model.sale;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Sale {
    private String id;
    private String clientId;
    private Long creationDate;

}
