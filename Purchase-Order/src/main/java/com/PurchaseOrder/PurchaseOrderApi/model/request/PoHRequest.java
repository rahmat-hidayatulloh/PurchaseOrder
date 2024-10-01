package com.PurchaseOrder.PurchaseOrderApi.model.request;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoHRequest extends BaseRequest {
    private int id;

    private LocalDateTime datetime;

    private String description;

    private int totalPrice;

    private int totalCost;
}
