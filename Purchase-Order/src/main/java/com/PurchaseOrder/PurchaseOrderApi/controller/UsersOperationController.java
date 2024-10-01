package com.PurchaseOrder.PurchaseOrderApi.controller;

import com.PurchaseOrder.PurchaseOrderApi.model.request.UsersRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.service.PostUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersOperationController {

    private PostUserService postUserService;

    public UsersOperationController(PostUserService postUserService) {
        this.postUserService = postUserService;
    }

    @PostMapping("/add")
    public EmptyResponse postUser(@Valid @RequestBody UsersRequest request){
        return postUserService.execute(request);
    }
}
