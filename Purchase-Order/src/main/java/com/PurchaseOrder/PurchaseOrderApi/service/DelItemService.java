package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class DelItemService implements BaseService<UniversalIdRequest, EmptyResponse> {

    private final ItemRepository itemRepository;

    public DelItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public EmptyResponse execute(UniversalIdRequest input) {
        itemRepository.deleteById(input.getId());
        return new EmptyResponse();
    }
}
