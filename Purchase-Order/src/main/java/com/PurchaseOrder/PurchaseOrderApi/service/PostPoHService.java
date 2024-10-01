package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoH;
import com.PurchaseOrder.PurchaseOrderApi.model.request.PoHRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoHRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PostPoHService implements BaseService<PoHRequest, EmptyResponse> {

    private final PoHRepository poHRepository;

    public PostPoHService(PoHRepository poHRepository) {
        this.poHRepository = poHRepository;
    }

    @Override
    public EmptyResponse execute(PoHRequest input) {
        Optional<PoH> poHOptional = poHRepository.findById(input.getId());

        PoH poH = mapNewPoH(new PoH(), input);
        if (poHOptional.isEmpty()) {
            poH.setDatetime(LocalDate.now().atTime(6, 0));
            poH.setCreatedBy("Administrator");
        } else {
            poH.setDatetime(poHOptional.get().getDatetime());
            poH.setId(poHOptional.get().getId());
            poH.setCreatedBy(poHOptional.get().getCreatedBy());
            poH.setUpdatedBy("Administrator");
            poH.setCreatedDatetime(poHOptional.get().getCreatedDatetime());
        }
        poHRepository.save(poH);
        return new EmptyResponse();
    }

    private PoH mapNewPoH(PoH poH, PoHRequest input) {
        poH.setTotalPrice(input.getTotalPrice());
        poH.setTotalCost(input.getTotalCost());
        poH.setDescription(input.getDescription());
        return poH;
    }

}
