package com.PurchaseOrder.PurchaseOrderApi.model.response;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseResponse;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoD;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderResponse extends BaseResponse {

    private List<PoD> purchaseOrderList;

}
