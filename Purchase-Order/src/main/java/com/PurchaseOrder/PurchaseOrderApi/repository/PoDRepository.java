package com.PurchaseOrder.PurchaseOrderApi.repository;

import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoDRepository extends JpaRepository<PoD, Integer> {
}
