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
        if (input.getId() != null) {
            return updatePurchaseOrder(input);
        }
        return createPurchaseOrder(input);
    }

    private EmptyResponse createPurchaseOrder(PurchaseOrderRequest input) {
        PoH newPurchaseOrder = mapNewPurchaseOrder(new PoH(), input);
        poHRepository.save(newPurchaseOrder);
        return new EmptyResponse();
    }

    private EmptyResponse updatePurchaseOrder(PurchaseOrderRequest input) {
        PoH existingPurchaseOrder = findExistingPurchaseOrder(input.getId());
        updatePurchaseOrderDetails(existingPurchaseOrder, input);
        poHRepository.save(existingPurchaseOrder);
        return new EmptyResponse();
    }

    private PoH findExistingPurchaseOrder(Integer id) {
        return poHRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(id, "Purchase Order tidak ditemukan"));
    }

    private void updatePurchaseOrderDetails(PoH existingPurchaseOrder, PurchaseOrderRequest input) {
        existingPurchaseOrder.setDescription(input.getDescription());
        List<PoD> updatedPoDList = createPoDList(existingPurchaseOrder, input.getPoDRequestList());
        existingPurchaseOrder.setPoDList(updatedPoDList);
        updateTotalCosts(existingPurchaseOrder, updatedPoDList);
    }

    private PoH mapNewPurchaseOrder(PoH poH, PurchaseOrderRequest input) {
        poH.setDescription(input.getDescription());
        List<PoD> poDList = createPoDList(poH, input.getPoDRequestList());
        poH.setPoDList(poDList);
        updateTotalCosts(poH, poDList);
        return poH;
    }

    private List<PoD> createPoDList(PoH poH, List<PoDRequest> poDRequestList) {
        List<PoD> listPoD = new ArrayList<>();
        for (PoDRequest request : poDRequestList) {
            PoD newPoD = createPoDFromRequest(request, poH);
            listPoD.add(newPoD);
        }
        return listPoD;
    }

    private PoD createPoDFromRequest(PoDRequest request, PoH poH) {
        PoD newPoD = new PoD();
        newPoD.setItem(getItemById(request.getItemId()));
        newPoD.setItemQty(request.getItemQty());
        newPoD.setItemCost(calculateAccumulatedCost(newPoD.getItem().getCost(), request.getItemQty()));
        newPoD.setItemPrice(calculateAccumulatedPrice(newPoD.getItem().getPrice(), request.getItemQty()));
        newPoD.setPoH(poH); // Set referensi ke PoH
        return newPoD;
    }

    private Item getItemById(Integer itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new DataNotFoundException(itemId, "Item tidak ditemukan"));
    }

    private int calculateAccumulatedCost(int costPerItem, int quantity) {
        return costPerItem * quantity;
    }

    private int calculateAccumulatedPrice(int pricePerItem, int quantity) {
        return pricePerItem * quantity;
    }

    private void updateTotalCosts(PoH poH, List<PoD> poDList) {
        poH.setTotalCost(calculateTotalCost(poDList));
        poH.setTotalPrice(calculateTotalPrice(poDList));
    }

    private int calculateTotalCost(List<PoD> poDList) {
        return poDList.stream().mapToInt(PoD::getItemCost).sum();
    }

    private int calculateTotalPrice(List<PoD> poDList) {
        return poDList.stream().mapToInt(PoD::getItemPrice).sum();
    }
}
