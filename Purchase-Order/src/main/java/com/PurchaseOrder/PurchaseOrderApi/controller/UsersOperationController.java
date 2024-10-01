package com.PurchaseOrder.PurchaseOrderApi.controller;

import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UsersRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.model.response.PurchaseOrderResponse;
import com.PurchaseOrder.PurchaseOrderApi.model.response.UsersResponse;
import com.PurchaseOrder.PurchaseOrderApi.service.DelUserService;
import com.PurchaseOrder.PurchaseOrderApi.service.GetUserService;
import com.PurchaseOrder.PurchaseOrderApi.service.PostUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersOperationController {

    private final PostUserService postUserService;
    private final DelUserService delUserService;
    private final GetUserService getUserService;

    public UsersOperationController(
            PostUserService postUserService,
            DelUserService delUserService,
            GetUserService getUserService
    ) {
        this.postUserService = postUserService;
        this.delUserService = delUserService;
        this.getUserService = getUserService;
    }

    @PostMapping("/add")
    public EmptyResponse postUser(@Valid @RequestBody UsersRequest request){
        return postUserService.execute(request);
    }

    @GetMapping("/")
    public UsersResponse getAllUserList() {
        return getUserService.execute(new UniversalIdRequest());
    }

    @GetMapping("/get-user/{userId}")
    public UsersResponse getPurchaseOrderById(@PathVariable(required = false) int userId) {
        return getUserService.execute(UniversalIdRequest
                .builder().id(userId).build());
    }

    @DeleteMapping("/delete")
    public EmptyResponse delUser(@Valid @RequestBody UniversalIdRequest request){
        return delUserService.execute(request);
    }
}
