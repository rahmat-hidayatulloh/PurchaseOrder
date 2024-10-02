package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.exception.DataNotFoundException;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.UsersResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserService implements BaseService<UniversalIdRequest, UsersResponse> {

    private final UsersRepository usersRepository;

    public GetUserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UsersResponse execute(UniversalIdRequest input) {

        return input != null && input.getId() != 0 ? getUserById(input.getId()) : getAllUserList();
    }

    private UsersResponse getAllUserList() {
        return UsersResponse.builder()
                .users(usersRepository.findAll())
                .build();
    }

    private UsersResponse getUserById(int userId) {
        return UsersResponse.builder()
                .users(List.of(usersRepository.findById(userId)
                        .orElseThrow(() -> new DataNotFoundException(userId, "User"))))
                .build();
    }


}
