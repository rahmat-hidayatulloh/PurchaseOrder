package com.PurchaseOrder.PurchaseOrderApi.model.entity;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "po_h")
@Data
public class PoH extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime datetime;

    @Column(length = 500)
    private String description;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "total_cost")
    private int totalCost;
}
