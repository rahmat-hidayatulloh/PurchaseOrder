package com.PurchaseOrder.PurchaseOrderApi.model.entity;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "item")
@Data
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String description;

    private int price;

    private int cost;

}
