package com.PurchaseOrder.PurchaseOrderApi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "po_d")
@Data
public class PoD{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "poh_id", nullable = false)
    private PoH poH;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "item_qty")
    private int itemQty;

    @Column(name = "item_cost")
    private int itemCost;

    @Column(name = "item_price")
    private int itemPrice;

}
