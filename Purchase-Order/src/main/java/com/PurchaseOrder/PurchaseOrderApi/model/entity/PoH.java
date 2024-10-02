package com.PurchaseOrder.PurchaseOrderApi.model.entity;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "po_h")
@Data
@EqualsAndHashCode(callSuper = false)
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

    @OneToMany(mappedBy = "poH", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PoD> poDList;
}
