package com.PurchaseOrder.PurchaseOrderApi.model.request;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderRequest extends BaseRequest {

    private String description;

    @JsonProperty("PO Detail")
    private List<PoDRequest> poDRequestList;

}
