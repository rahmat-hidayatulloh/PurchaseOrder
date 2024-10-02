package com.PurchaseOrder.PurchaseOrderApi.controller;

import com.PurchaseOrder.PurchaseOrderApi.model.request.PurchaseOrderRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.model.response.PurchaseOrderResponse;
import com.PurchaseOrder.PurchaseOrderApi.service.DelPurchaseOrderService;
import com.PurchaseOrder.PurchaseOrderApi.service.GetPurchaseOrderService;
import com.PurchaseOrder.PurchaseOrderApi.service.PostPurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase-order")
public class PurchaseOrderOperationController {

    private final PostPurchaseOrderService postPurchaseOrderService;
    private final GetPurchaseOrderService getPurchaseOrderService;
    private final DelPurchaseOrderService delPurchaseOrderService;

    public PurchaseOrderOperationController(PostPurchaseOrderService postPurchaseOrderService,
                                            GetPurchaseOrderService getPurchaseOrderService,
                                            DelPurchaseOrderService delPurchaseOrderService) {
        this.postPurchaseOrderService = postPurchaseOrderService;
        this.getPurchaseOrderService = getPurchaseOrderService;
        this.delPurchaseOrderService = delPurchaseOrderService;
    }

    @PostMapping("/add-purchase-order")
    public EmptyResponse postPurchaseOrder(@Valid @RequestBody PurchaseOrderRequest request) {
        return postPurchaseOrderService.execute(request);
    }

    @GetMapping("/")
    public PurchaseOrderResponse getAllPurchaseOrderList() {
        return getPurchaseOrderService.execute(new UniversalIdRequest());
    }

    @GetMapping("/get-order/{poId}")
    public PurchaseOrderResponse getPurchaseOrderById(@PathVariable(required = true) int poId) {
        return getPurchaseOrderService.execute(UniversalIdRequest
                .builder().id(poId).build());
    }

    @DeleteMapping("/delete-purchase-order")
    public EmptyResponse DelPurchaseOrder(@Valid @RequestBody UniversalIdRequest request) {
        return delPurchaseOrderService.execute(request);
    }
}
