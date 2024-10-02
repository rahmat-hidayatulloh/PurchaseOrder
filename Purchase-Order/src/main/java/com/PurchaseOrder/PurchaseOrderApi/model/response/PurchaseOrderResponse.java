package com.PurchaseOrder.PurchaseOrderApi.model.response;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseResponse;
import com.PurchaseOrder.PurchaseOrderApi.model.dto.PoHDto;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoH;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderResponse extends BaseResponse {

    @JsonProperty("Purchase Order")
    private List<PoHDto> purchaseOrderList;

}
