package com.PurchaseOrder.PurchaseOrderApi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoDDto {

    @JsonProperty("PO Detail Id")
    private int id;

    private ItemDto item;

    @JsonProperty("PO Id")
    private int poHId;

    @JsonProperty("Item Id")
    private int itemId;

    @JsonProperty("Qty")
    private int itemQty;

    @JsonProperty("Item Cost")
    private int itemCost;

    @JsonProperty("Item Price")
    private int itemPrice;
}