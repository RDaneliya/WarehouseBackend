package com.warehouse.inventory.api.commodity;

import com.warehouse.inventory.extensions.mappers.CommodityMapper;
import com.warehouse.inventory.infrastructure.database.repositories.CommodityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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
    public CommodityResponseItem getCommodityByCode(@PathVariable Long vendorCode) {
        var commodity = repository.getByVendorCode(vendorCode);
        return mapper.toResponseItem(commodity);
    }

    @PostMapping()
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCommodity(@RequestBody CommodityRequest request) {
        var id = request.getVendorCode();
        if(repository.existsById(id)){
            throw new EntityExistsException();
        }

        var entity = mapper.toCommodityEntity(request);
        repository.save(entity);

        log.debug("Added new entity to Commodities");
    }

    @PutMapping()
    @Transactional
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editCommodity(@RequestBody CommodityRequest request){
        if (!repository.existsById(request.getVendorCode())) {
            throw new EntityNotFoundException();
        }
        var entity = repository.getByVendorCode(request.getVendorCode());
        entity.setName(request.getName());
        repository.save(entity);
    }

    @DeleteMapping(value = "{vendorCode}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void deleteCommodity(@PathVariable Long vendorCode){
        if(!repository.existsById(vendorCode)){
            throw new EntityNotFoundException();
        }

        repository.deleteById(vendorCode);
    }
}
