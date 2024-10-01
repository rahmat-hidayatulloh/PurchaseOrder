package com.PurchaseOrder.PurchaseOrderApi.model.response;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseResponse;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.Users;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsersResponse extends BaseResponse {
    private List<Users> users;
}
