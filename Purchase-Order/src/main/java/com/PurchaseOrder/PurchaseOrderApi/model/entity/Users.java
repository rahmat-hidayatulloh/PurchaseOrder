package com.PurchaseOrder.PurchaseOrderApi.model.entity;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", length = 500)
    private String firstName;

    @Column(name = "last_name", length = 500)
    private String lastName;

    @Column(length = 255)
    private String email;

    @Column(length = 255)
    private String phone;
}
