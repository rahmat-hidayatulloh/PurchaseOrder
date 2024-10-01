package com.PurchaseOrder.PurchaseOrderApi.controller;

import com.PurchaseOrder.PurchaseOrderApi.model.request.ItemRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.ItemRepository;
import com.PurchaseOrder.PurchaseOrderApi.service.PostItemService;
import io.swagger.v3.oas.annotations.tags.Tags;
import io.swagger.v3.oas.models.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemOperationController {

    private PostItemService postItemService;

    public ItemOperationController(PostItemService postItemService) {
        this.postItemService = postItemService;
    }

    @PostMapping("/add")
    public EmptyResponse postItem(@Valid @RequestBody ItemRequest request){
        return postItemService.execute(request);
    }
}
