package com.warehouse.inventory.extensions.mappers;

import com.warehouse.inventory.api.waybill.WaybillResponseItem;
import com.warehouse.inventory.infrastructure.database.entity.WaybillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
    componentModel = "spring",
    uses = {
        WaybillCommodityMapper.class,
        CounterpartyMapper.class,
        CommodityMapper.class
    })
public interface WaybillMapper {
    @Mappings({
        @Mapping(source = "commodityEntities", target = "commodities"),
        @Mapping(source = "counterpartyEntity.name", target = "counterparty"),

    })
    WaybillResponseItem toResponseItem(WaybillEntity entity);
}
