package com.PurchaseOrder.PurchaseOrderApi.model.request;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseRequest;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderRequest extends BaseRequest {

    private int id;

    private int poHId;

    private int itemId;

    private int itemQty;

    private int itemCost;

    private int itemPrice;
}
