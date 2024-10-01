package com.PurchaseOrder.PurchaseOrderApi.controller;

import com.PurchaseOrder.PurchaseOrderApi.model.request.ItemRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.model.response.ItemResponse;
import com.PurchaseOrder.PurchaseOrderApi.model.response.PurchaseOrderResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.ItemRepository;
import com.PurchaseOrder.PurchaseOrderApi.service.DelItemService;
import com.PurchaseOrder.PurchaseOrderApi.service.GetItemService;
import com.PurchaseOrder.PurchaseOrderApi.service.PostItemService;
import io.swagger.v3.oas.annotations.tags.Tags;
import io.swagger.v3.oas.models.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemOperationController {

    private final PostItemService postItemService;
    private final DelItemService delItemService;
    private final GetItemService getItemService;

    public ItemOperationController(PostItemService postItemService, DelItemService delItemService, GetItemService getItemService) {
        this.postItemService = postItemService;
        this.delItemService = delItemService;
        this.getItemService = getItemService;
    }

    @PostMapping("/add")
    public EmptyResponse postItem(@Valid @RequestBody ItemRequest request){
        return postItemService.execute(request);
    }

    @GetMapping("/")
    public ItemResponse getAllItemList() {
        return getItemService.execute(new UniversalIdRequest());
    }

    @GetMapping("/get-item/{itemId}")
    public ItemResponse getItemById(@PathVariable(required = false) int itemId) {
        return getItemService.execute(UniversalIdRequest
                .builder().id(itemId).build());
    }

    @DeleteMapping("/delete")
    public EmptyResponse delItem(@Valid @RequestBody UniversalIdRequest request){
        return delItemService.execute(request);
    }
}
