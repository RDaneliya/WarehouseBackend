package com.warehouse.inventory.api.zone;

import com.warehouse.inventory.extensions.mappers.ZoneMapper;
import com.warehouse.inventory.infrastructure.database.entity.ZoneEntity;
import com.warehouse.inventory.infrastructure.database.repositories.CommodityRepository;
import com.warehouse.inventory.infrastructure.database.repositories.ZoneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/zone/")
@Slf4j
public class ZoneEndpoint {
    private final CommodityRepository commodityRepository;
    private final ZoneRepository zoneRepository;
    private final ZoneMapper mapper;

    @GetMapping(produces = "application/json", value = "{id}")
    @ResponseBody
    @Transactional
    public ZoneResponseItem getZoneById(@PathVariable Long id) {
        var entity = zoneRepository.getZoneEntityById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return mapper.toResponseItem(entity);
    }

    @GetMapping(produces = "application/json", value = "{vendor-code}")

    @PostMapping()
    @Transactional
    public void createZone(@RequestBody ZoneRequest request) {
        var vendorCode = request.getCommodityVendorCode();
        if (vendorCode != null) {
            var commodity = commodityRepository.getByVendorCode(vendorCode);
            var zone = new ZoneEntity(
                null,
                commodity,
                request.getAmount(),
                request.getRow(),
                request.getColumn()
            );
            zoneRepository.save(zone);
        } else {
            var zone = new ZoneEntity(
                null,
                null,
                request.getAmount(),
                request.getRow(),
                request.getColumn()
            );

            zoneRepository.save(zone);
        }
    }

    @PutMapping(value = "{id}")
    @Transactional
    public void editZone(@PathVariable Long id, @RequestBody ZoneRequest request) {
        var zone = zoneRepository.getZoneEntityById(id);
        if (zone == null) {
            throw new EntityNotFoundException();
        }

        if (request.getCommodityVendorCode() == null) {
            zone.setCommodityEntityType(null);
        } else {
            var commodity = commodityRepository.getByVendorCode(request.getCommodityVendorCode());
            zone.setCommodityEntityType(commodity);
        }

        zone.setAmount(request.getAmount());
        zone.setRow(request.getRow());
        zone.setColumn(request.getColumn());

        zoneRepository.save(zone);
    }

    @DeleteMapping(value = "{id}")
    @Transactional
    public void deleteZone(@PathVariable Long id) {
        zoneRepository.deleteById(id);
    }
}
