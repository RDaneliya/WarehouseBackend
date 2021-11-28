package com.warehouse.inventory.api.commodity;

import com.warehouse.inventory.extensions.mappers.CommodityMapper;
import com.warehouse.inventory.infrastructure.database.repositories.CommodityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/commodity/")
@Slf4j
public class CommodityEndpoint {
    private final CommodityRepository repository;
    private final CommodityMapper mapper;

    @GetMapping(produces = "application/json", value = "{vendorCode}")
    @ResponseBody
    @Transactional
    public CommodityResponseItem getCommodityByCode(@PathVariable String vendorCode) {
        var commodity = repository.getByVendorCode(vendorCode);
        return mapper.toResponseItem(commodity);
    }

    @GetMapping(produces = "application/json", value = "/all")
    @ResponseBody
    @Transactional
    public List<CommodityResponseItem> getAllCommodities() {
        return repository.findAll()
            .stream()
            .map(mapper::toResponseItem)
            .collect(Collectors.toList());
    }

    @GetMapping(produces = "application/json", value = "/search/{substring}")
    @ResponseBody
    @Transactional
    public List<CommodityResponseItem> findCommoditiesBySubstring(@PathVariable String substring) {
        return repository.findByNameContaining(substring)
            .stream()
            .map(mapper::toResponseItem)
            .collect(Collectors.toList());
    }

    @PostMapping()
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCommodity(@RequestBody CommodityRequest request) {
        var id = request.getVendorCode();
        if (repository.existsById(id)) {
            throw new EntityExistsException();
        }

        var entity = mapper.toCommodityEntity(request);
        repository.save(entity);

        log.debug("Added new entity to Commodities");
    }

    @PutMapping(value = "{vendorCode}")
    @Transactional
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editCommodity(@PathVariable String vendorCode, @RequestBody CommodityRequest request) {
        if (!repository.existsById(vendorCode)) {
            throw new EntityNotFoundException();
        }
        var entity = repository.getByVendorCode(request.getVendorCode());
        entity.setName(request.getName());
        repository.save(entity);
    }

    @DeleteMapping(value = "{vendorCode}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void deleteCommodity(@PathVariable String vendorCode) {
        if (!repository.existsById(vendorCode)) {
            throw new EntityNotFoundException();
        }

        repository.deleteById(vendorCode);
    }
}
