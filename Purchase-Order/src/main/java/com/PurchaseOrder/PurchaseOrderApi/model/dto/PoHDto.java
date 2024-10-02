package com.PurchaseOrder.PurchaseOrderApi.model.dto;

import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoD;
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

    private int id;

    private LocalDateTime datetime;

    private String description;

    private int totalPrice;

    private int totalCost;

    private List<PoDDto> poDList;
}
