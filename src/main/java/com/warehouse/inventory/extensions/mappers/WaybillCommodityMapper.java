package com.warehouse.inventory.extensions.mappers;

import com.warehouse.inventory.api.waybill.WaybillCommodityResponseItem;
import com.warehouse.inventory.infrastructure.database.entity.WaybillCommodityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WaybillCommodityMapper {
    @Mappings(
        @Mapping(source = "commodity.vendorCode", target = "vendorCode")
    )
    WaybillCommodityResponseItem toResponse(WaybillCommodityEntity entity);

    List<WaybillCommodityResponseItem> toResponseItems(List<WaybillCommodityEntity> entitySet);
}
