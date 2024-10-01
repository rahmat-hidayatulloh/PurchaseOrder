package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.exception.DataNotFoundException;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.PurchaseOrderResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoDRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPurchaseOrderService implements BaseService<UniversalIdRequest, PurchaseOrderResponse> {

    private final PoDRepository poDRepository;

    public GetPurchaseOrderService(PoDRepository poDRepository) {
        this.poDRepository = poDRepository;
    }

    @Override
    public PurchaseOrderResponse execute(UniversalIdRequest input) {

        return input == null ? getAllPurchaseOrder() : getPurchaseOrderById(input.getId());
    }

    private PurchaseOrderResponse getAllPurchaseOrder() {

        return PurchaseOrderResponse.builder()
                .purchaseOrderList(poDRepository.findAll())
                .build();
    }

    private PurchaseOrderResponse getPurchaseOrderById(int poId) {

        return PurchaseOrderResponse.builder()
                .purchaseOrderList(List.of(poDRepository.findById(poId)
                        .orElseThrow(()-> new DataNotFoundException(poId, "PO"))))
                .build();
    }

}
