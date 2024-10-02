package com.PurchaseOrder.PurchaseOrderApi.repository;

import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoD;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoDRepository extends JpaRepository<PoD, Integer> {
    List<PoD> findAllByPoH(PoH poH);
}
