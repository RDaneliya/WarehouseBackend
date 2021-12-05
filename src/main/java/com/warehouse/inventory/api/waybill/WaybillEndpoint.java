package com.warehouse.inventory.api.waybill;

import com.warehouse.inventory.extensions.mappers.WaybillMapper;
import com.warehouse.inventory.infrastructure.database.entity.WaybillCommodityEntity;
import com.warehouse.inventory.infrastructure.database.entity.WaybillEntity;
import com.warehouse.inventory.infrastructure.database.repositories.CommodityRepository;
import com.warehouse.inventory.infrastructure.database.repositories.CounterpartyRepository;
import com.warehouse.inventory.infrastructure.database.repositories.WaybillCommodityRepository;
import com.warehouse.inventory.infrastructure.database.repositories.WaybillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/waybill/")
@RestController
@RequiredArgsConstructor
public class WaybillEndpoint {
    private final WaybillRepository waybillRepository;
    private final WaybillCommodityRepository waybillCommodityRepository;
    private final CounterpartyRepository counterpartyRepository;
    private final CommodityRepository commodityRepository;
    private final WaybillMapper mapper;

    @GetMapping("/all")
    @Transactional
    @ResponseBody
    public List<WaybillResponseItem> getAll() {
        var entities = waybillRepository.findAll();
        return entities.stream()
            .map(mapper::toResponseItem)
            .collect(Collectors.toList());
    }

    @PostMapping()
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void createWaybill(@RequestBody WaybillRequest request) {
        checkCommodityExistence(request);
        checkCounterparty(request);
        var unmanagedWaybill = new WaybillEntity();
        var counterparty = counterpartyRepository.findCounterpartyEntityByName(request.getCounterparty());
        unmanagedWaybill.setCounterpartyEntity(counterparty);
        unmanagedWaybill.setCreatedAt(request.getCreatedAt());
        unmanagedWaybill.setType(request.getType());

        var commodities = request.getCommodities().stream()
            .map(commodityItem -> {
                var commodity = new WaybillCommodityEntity();
                var commodityType = commodityRepository.getByName(commodityItem.getName());
                var reference = commodityRepository.getByName(commodityItem.getName());
                commodity.setCommodity(reference);
                commodity.setAmount(commodityItem.getAmount());
                return commodity;
            })
            .collect(Collectors.toList());
        unmanagedWaybill.setCommodityEntities(commodities);
        waybillRepository.save(unmanagedWaybill);
    }


    private void checkCounterparty(WaybillRequest request) {
        if (!counterpartyRepository.existsByName(request.getCounterparty())) {
            throw new EntityNotFoundException("No such counterparty");
        }
    }

    private void checkCommodityExistence(WaybillRequest request) {
        request.getCommodities()
            .forEach(item -> {
                    if (!commodityRepository.existsByName(item.getName())) {
                        throw new EntityNotFoundException("No such commodity");
                    }
                }
            );
    }
}
