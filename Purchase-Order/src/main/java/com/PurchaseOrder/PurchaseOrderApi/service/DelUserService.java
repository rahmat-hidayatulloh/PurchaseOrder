package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class DelUserService implements BaseService<UniversalIdRequest, EmptyResponse> {

    private final UsersRepository usersRepository;

    public DelUserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public EmptyResponse execute(UniversalIdRequest input) {
        usersRepository.deleteById(input.getId());
        return new EmptyResponse();
    }
}
