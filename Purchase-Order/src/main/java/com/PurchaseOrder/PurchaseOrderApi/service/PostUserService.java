package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.Users;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UsersRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostUserService implements BaseService<UsersRequest, EmptyResponse> {

    private final UsersRepository usersRepository;

    public PostUserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public EmptyResponse execute(UsersRequest input) {

        Optional<Users> usersOptional = usersRepository.findByEmail(input.getEmail());
        Users user = mapNewUser(new Users(), input);
        if (usersOptional.isEmpty()) {
            user.setCreatedBy("Administrator");
        } else {
            user.setId(usersOptional.get().getId());
            user.setCreatedBy(usersOptional.get().getCreatedBy());
            user.setUpdatedBy(usersOptional.get().getFirstName());
            user.setCreatedDatetime(usersOptional.get().getCreatedDatetime());
        }
        usersRepository.save(user);
        return new EmptyResponse();
    }

    private Users mapNewUser(Users user, UsersRequest input) {
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        return user;
    }
}
