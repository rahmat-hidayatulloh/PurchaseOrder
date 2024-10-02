package com.PurchaseOrder.PurchaseOrderApi.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ItemDto {

    private int id;

    private String name;

    private String description;

    private int price;

    private int cost;
}
