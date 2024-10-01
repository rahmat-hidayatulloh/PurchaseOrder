package com.PurchaseOrder.PurchaseOrderApi.model.response;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseResponse;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoD;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderResponse extends BaseResponse {

    private List<PoD> purchaseOrderList;

}
