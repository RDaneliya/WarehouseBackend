package com.warehouse.inventory.extensions.mappers;

import com.warehouse.inventory.api.commodity.CommodityRequest;
import com.warehouse.inventory.api.commodity.CommodityResponseItem;
import com.warehouse.inventory.infrastructure.database.entity.CommodityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommodityMapper {
    CommodityResponseItem toResponseItem(CommodityEntity entity);

    CommodityEntity toCommodityEntity(CommodityRequest request);
}
