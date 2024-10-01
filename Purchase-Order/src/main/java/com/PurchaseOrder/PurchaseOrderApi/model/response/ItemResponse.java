package com.PurchaseOrder.PurchaseOrderApi.model.response;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseResponse;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.Item;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse extends BaseResponse {
    private List<Item> items;
}
