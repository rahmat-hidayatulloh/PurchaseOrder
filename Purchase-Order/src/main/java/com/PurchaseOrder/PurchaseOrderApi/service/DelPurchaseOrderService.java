package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoDRepository;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoHRepository;
import org.springframework.stereotype.Service;

@Service
public class DelPurchaseOrderService implements BaseService<UniversalIdRequest, EmptyResponse> {

    private final PoDRepository poDRepository;
    private final PoHRepository poHRepository;

    public DelPurchaseOrderService(PoDRepository poDRepository, PoHRepository poHRepository) {
        this.poDRepository = poDRepository;
        this.poHRepository = poHRepository;
    }

    @Override
    public EmptyResponse execute(UniversalIdRequest input) {
        poHRepository.deleteById(input.getId());
        return new EmptyResponse();
    }
}
