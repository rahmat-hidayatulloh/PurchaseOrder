package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.exception.DataNotFoundException;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.ItemResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetItemService implements BaseService<UniversalIdRequest, ItemResponse> {

    private final ItemRepository itemRepository;

    public GetItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemResponse execute(UniversalIdRequest input) {
        return input == null ? getAllItemList() : getItemById(input.getId());
    }

    private ItemResponse getItemById(int itemId) {
        return ItemResponse
                .builder()
                .items(List.of(
                        itemRepository.findById(itemId)
                                .orElseThrow(() -> new DataNotFoundException(itemId, "Item"))))
                .build();
    }

    private ItemResponse getAllItemList() {
        return ItemResponse
                .builder()
                .items(itemRepository.findAll())
                .build();
    }
}
