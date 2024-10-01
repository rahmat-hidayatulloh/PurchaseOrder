package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.exception.DataNotFoundException;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoD;
import com.PurchaseOrder.PurchaseOrderApi.model.request.PurchaseOrderRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.ItemRepository;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoDRepository;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoHRepository;
import org.springframework.stereotype.Service;

@Service
public class PostPurchaseOrderService implements BaseService<PurchaseOrderRequest, EmptyResponse> {
    private final PoDRepository poDRepository;
    private final PoHRepository poHRepository;
    private final ItemRepository itemRepository;

    public PostPurchaseOrderService(PoDRepository poDRepository, PoHRepository poHRepository, ItemRepository itemRepository) {
        this.poDRepository = poDRepository;
        this.poHRepository = poHRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public EmptyResponse execute(PurchaseOrderRequest input) {
        poDRepository.save(mapNewPurchaseOrder(new PoD(), input));
        return new EmptyResponse();
    }

    private PoD mapNewPurchaseOrder(PoD poD, PurchaseOrderRequest input) {
        poD.setItem(itemRepository.findById(input.getItemId())
                .orElseThrow(() -> new DataNotFoundException(input.getItemId(), "Item")));
        poD.setPoH(poHRepository.findById(input.getPoHId())
                .orElseThrow(() -> new DataNotFoundException(input.getPoHId(), "PoH")));
        poD.setItemPrice(input.getItemPrice());
        poD.setItemCost(input.getItemCost());
        poD.setItemQty(input.getItemQty());
        return poD;
    }
}
