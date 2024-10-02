package com.PurchaseOrder.PurchaseOrderApi.model.dto;

import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoD;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PoHDto {

    @JsonProperty("Purchase order Id")
    private int id;

    @JsonProperty("Purchase order date")
    private LocalDateTime datetime;

    private String description;

    @JsonProperty("total price")
    private int totalPrice;

    @JsonProperty("total cost")
    private int totalCost;

    @JsonProperty("Purchase Detail Order")
    private List<PoDDto> poDList;
}
