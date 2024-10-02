package com.PurchaseOrder.PurchaseOrderApi.service;

import com.PurchaseOrder.PurchaseOrderApi.common.base.BaseService;
import com.PurchaseOrder.PurchaseOrderApi.model.dto.ItemDto;
import com.PurchaseOrder.PurchaseOrderApi.model.dto.PoDDto;
import com.PurchaseOrder.PurchaseOrderApi.model.dto.PoHDto;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.Item;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoD;
import com.PurchaseOrder.PurchaseOrderApi.model.entity.PoH;
import com.PurchaseOrder.PurchaseOrderApi.model.request.UniversalIdRequest;
import com.PurchaseOrder.PurchaseOrderApi.model.response.PurchaseOrderResponse;
import com.PurchaseOrder.PurchaseOrderApi.repository.ItemRepository;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoDRepository;
import com.PurchaseOrder.PurchaseOrderApi.repository.PoHRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetPurchaseOrderService implements BaseService<UniversalIdRequest, PurchaseOrderResponse> {

    private final PoHRepository poHRepository;
    private final PoDRepository poDRepository;
    private final ItemRepository itemRepository;

    public GetPurchaseOrderService(PoHRepository poHRepository,
                                   PoDRepository poDRepository,
                                   ItemRepository itemRepository) {
        this.poHRepository = poHRepository;
        this.poDRepository = poDRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public PurchaseOrderResponse execute(UniversalIdRequest input) {
        return input != null && input.getId() != 0
                ? getPurchaseOrderById(input.getId())
                : getAllPurchaseOrders();
    }

    private PurchaseOrderResponse getAllPurchaseOrders() {
        List<PoH> poHList = poHRepository.findAll();
        return buildPurchaseOrderResponse(poHList);
    }

    private PurchaseOrderResponse getPurchaseOrderById(Integer poId) {
        List<PoH> poHList = poHRepository.findAllById(Collections.singleton(poId));
        return buildPurchaseOrderResponse(poHList);
    }

    private PurchaseOrderResponse buildPurchaseOrderResponse(List<PoH> poHList) {
        List<PoHDto> poHDtos = mapPoHListToDto(poHList);
        return PurchaseOrderResponse.builder()
                .purchaseOrderList(poHDtos)
                .build();
    }

    private List<PoHDto> mapPoHListToDto(List<PoH> poHList) {
        return poHList.stream()
                .map(this::mapPoHToDto)
                .collect(Collectors.toList());
    }

    private PoHDto mapPoHToDto(PoH poH) {
        return PoHDto.builder()
                .id(poH.getId())
                .datetime(poH.getDatetime())
                .description(poH.getDescription())
                .totalPrice(poH.getTotalPrice())
                .totalCost(poH.getTotalCost())
                .poDList(mapPoDListToDto(poH))
                .build();
    }

    private List<PoDDto> mapPoDListToDto(PoH poH) {
        return poDRepository.findAllByPoH(poH).stream()
                .map(this::mapPoDToDto)
                .collect(Collectors.toList());
    }

    private PoDDto mapPoDToDto(PoD poD) {
        return PoDDto.builder()
                .id(poD.getId())
                .item(mapItemToDto(poD.getItem()))
                .itemId(poD.getItem().getId())
                .itemQty(poD.getItemQty())
                .itemCost(poD.getItemCost())
                .itemPrice(poD.getItemPrice())
                .build();
    }

    private ItemDto mapItemToDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .cost(item.getCost())
                .price(item.getPrice())
                .description(item.getDescription())
                .build();
    }
}
