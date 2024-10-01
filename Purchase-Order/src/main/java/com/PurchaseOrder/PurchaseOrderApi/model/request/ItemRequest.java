package com.PurchaseOrder.PurchaseOrderApi.model.request;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseRequest;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest extends BaseRequest {
    private int id;

    private String name;

    private String description;

    private int price;

    private int cost;

}
