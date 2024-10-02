package com.PurchaseOrder.PurchaseOrderApi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoDDto {

    private int id;

    private ItemDto item;

    private int itemQty;

    private int itemCost;

    private int itemPrice;
}