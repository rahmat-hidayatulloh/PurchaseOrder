package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.PurchaseOrderResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoDRepository;
import org.springframework.stereotype.Service;

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
        PurchaseOrderResponse purchaseOrderResponse = new PurchaseOrderResponse();

        return PurchaseOrderResponse.builder()
                .purchaseOrderList(poDRepository.findAll())
                .build();
    }

    private PurchaseOrderResponse getPurchaseOrderById(int id) {
        return null;
    }

}
