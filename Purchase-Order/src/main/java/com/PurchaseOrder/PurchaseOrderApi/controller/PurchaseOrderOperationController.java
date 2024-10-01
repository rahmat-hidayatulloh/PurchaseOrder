package com.PurchaseOrder.PurchaseOrderApi.controller;

import com.PurchaseOrder.PurchaseOrderApi.model.request.PoHRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.request.PurchaseOrderRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.service.PostPoHService;
import com.PurchaseOrder.PurchaseOrderApi.service.PostPurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase-order")
public class PurchaseOrderOperationController {

    private final PostPoHService postPoHService;
    private final PostPurchaseOrderService postPurchaseOrderService;

    public PurchaseOrderOperationController(PostPoHService postPoHService, PostPurchaseOrderService postPurchaseOrderService) {
        this.postPoHService = postPoHService;
        this.postPurchaseOrderService = postPurchaseOrderService;
    }

    @PostMapping("/add-master-po")
    public EmptyResponse postPOHeader(@Valid @RequestBody PoHRequest request){
        return postPoHService.execute(request);
    }

    @PostMapping("/add-purchase-order")
    public EmptyResponse postPurchaseOrder(@Valid @RequestBody PurchaseOrderRequest request){
        return postPurchaseOrderService.execute(request);
    }
}
