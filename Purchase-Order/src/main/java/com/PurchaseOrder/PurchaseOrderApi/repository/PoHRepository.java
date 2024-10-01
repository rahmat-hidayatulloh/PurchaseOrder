package com.PurchaseOrder.PurchaseOrderApi.repository;

import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoHRepository extends JpaRepository<PoH, Integer> {
}
