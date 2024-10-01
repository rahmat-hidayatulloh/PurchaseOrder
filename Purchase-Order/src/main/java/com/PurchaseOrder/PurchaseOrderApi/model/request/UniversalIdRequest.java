package com.PurchaseOrder.PurchaseOrderApi.model.request;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversalIdRequest extends BaseRequest {
    private int id;
}
