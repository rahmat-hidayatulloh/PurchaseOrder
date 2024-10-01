package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.exception.DataNotFoundException;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoD;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoH;
import com.PurchaseOrder.PurchaseOrderApi.model.request.PoDRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.request.PurchaseOrderRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.EmptyResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.ItemRepository;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoDRepository;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoHRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostPurchaseOrderService implements BaseService<PurchaseOrderRequest, EmptyResponse> {
    private final PoDRepository poDRepository;
    private final PoHRepository poHRepository;
    private final ItemRepository itemRepository;

    public PostPurchaseOrderService(PoDRepository poDRepository, PoHRepository poHRepository, ItemRepository itemRepository) {
        this.poDRepository = poDRepository;
        this.poHRepository = poHRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public EmptyResponse execute(PurchaseOrderRequest input) {
        poHRepository.save(mapNewPurchaseOrder(new PoH(), input));
        return new EmptyResponse();
    }


    private PoH mapNewPurchaseOrder(PoH poH, PurchaseOrderRequest input) {

        poH.setDescription(input.getDescription());

        List<PoD> listPoD = storedPoDList(poH, input.getPoDRequestList());
        poH.setPoDList(listPoD);

        int totalCost = calculateTotalCost(listPoD);
        int totalPrice = calculateTotalPrice(listPoD);

        poH.setTotalCost(totalCost);
        poH.setTotalPrice(totalPrice);

        return poH;
    }

    private List<PoD> storedPoDList(PoH poH, List<PoDRequest> poDRequestList) {
        List<PoD> listPoD = new ArrayList<>();

        for (PoDRequest request : poDRequestList) {
            PoD newPoD = new PoD();

            newPoD.setItem(itemRepository.findById(request.getItemId())
                    .orElseThrow(() -> new DataNotFoundException(request.getItemId(), "Item")));

            newPoD.setItemQty(request.getItemQty());
            newPoD.setItemCost(priceAndCostAccumulation(newPoD.getItem().getCost(), request.getItemQty()));
            newPoD.setItemPrice(priceAndCostAccumulation(newPoD.getItem().getPrice(), request.getItemQty()));
            newPoD.setPoH(poH);

            listPoD.add(newPoD);
        }

        return listPoD;
    }

    private int priceAndCostAccumulation(int value, int itemQty) {
        return value * itemQty;
    }

    private int calculateTotalCost(List<PoD> poDList) {
        return poDList.stream().mapToInt(PoD::getItemCost).sum();
    }

    private int calculateTotalPrice(List<PoD> poDList) {
        return poDList.stream().mapToInt(PoD::getItemPrice).sum();
    }

}
