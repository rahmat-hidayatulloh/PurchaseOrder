package com.PurchaseOrder.PurchaseOrderApi.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PoDRequest {

    @JsonProperty("id item")
    private int itemId;

    @JsonProperty("qty")
    private int itemQty;
}
