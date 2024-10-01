package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.Item;
import com.PurchaseOrder.PurchaseOrderApi.model.request.ItemRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostItemService implements BaseService<ItemRequest, EmptyResponse> {

    private final ItemRepository itemRepository;

    public PostItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public EmptyResponse execute(ItemRequest input) {

        Optional<Item> itemOptional = itemRepository.findById(input.getId());

        Item item = mapNewItem(new Item(), input);
        if (itemOptional.isEmpty()) {
            item.setCreatedBy("Administrator");
        } else {
            item.setId(itemOptional.get().getId());
            item.setCreatedBy(itemOptional.get().getCreatedBy());
            item.setUpdatedBy("Administrator");
            item.setCreatedDatetime(itemOptional.get().getCreatedDatetime());
        }
        itemRepository.save(item);
        return new EmptyResponse();
    }

    private Item mapNewItem(Item item, ItemRequest input) {
        item.setName(input.getName());
        item.setPrice(input.getPrice());
        item.setCost(input.getCost());
        item.setDescription(input.getDescription());
        return item;
    }

}
