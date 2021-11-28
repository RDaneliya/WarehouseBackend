package com.warehouse.inventory.extensions.mappers;

import com.warehouse.inventory.api.zone.ZoneResponseItem;
import com.warehouse.inventory.infrastructure.database.entity.ZoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommodityMapper.class)
public interface ZoneMapper {
    @Mapping(target = "commodity", source = "commodityEntityType")
    ZoneResponseItem toResponseItem(ZoneEntity entity);
}
